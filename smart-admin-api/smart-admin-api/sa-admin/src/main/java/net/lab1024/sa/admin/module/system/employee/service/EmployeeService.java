package net.lab1024.sa.admin.module.system.employee.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import net.lab1024.sa.admin.module.system.employee.dao.EmployeeDao;
import net.lab1024.sa.admin.module.system.employee.domain.entity.EmployeeEntity;
import net.lab1024.sa.admin.module.system.employee.domain.form.*;
import net.lab1024.sa.admin.module.system.employee.domain.vo.EmployeeVO;
import net.lab1024.sa.admin.module.system.employee.manager.EmployeeManager;
import net.lab1024.sa.common.common.code.UserErrorCode;
import net.lab1024.sa.common.common.domain.PageResult;
import net.lab1024.sa.common.common.domain.ResponseDTO;
import net.lab1024.sa.common.common.enumeration.UserTypeEnum;
import net.lab1024.sa.common.common.util.SmartBeanUtil;
import net.lab1024.sa.common.common.util.SmartPageUtil;
import net.lab1024.sa.common.module.support.token.TokenService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 员工 service
 *
 * @Author 1024创新实验室-主任: 卓大
 * @Date 2021-12-29 21:52:46
 * @Wechat zhuoda1024
 * @Email lab1024@163.com
 * @Copyright 1024创新实验室 （ https://1024lab.net ）
 */
@Service
public class EmployeeService {

    private static final String PASSWORD_SALT_FORMAT = "smart_%s_admin_$^&*";

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private EmployeeManager employeeManager;

    @Autowired
    private TokenService tokenService;


    public EmployeeEntity getById(Long employeeId) {
        return employeeDao.selectById(employeeId);
    }


    /**
     * 查询员工列表
     *
     * @param employeeQueryForm
     * @return
     */
    public ResponseDTO<PageResult<EmployeeVO>> queryEmployee(EmployeeQueryForm employeeQueryForm) {
        employeeQueryForm.setDeletedFlag(false);
        Page pageParam = SmartPageUtil.convert2PageQuery(employeeQueryForm);

        List<Long> departmentIdList = new ArrayList<>();

        List<EmployeeVO> employeeList = employeeDao.queryEmployee(pageParam, employeeQueryForm, departmentIdList);
        if (CollectionUtils.isEmpty(employeeList)) {
            PageResult<EmployeeVO> PageResult = SmartPageUtil.convert2PageResult(pageParam, employeeList);
            return ResponseDTO.ok(PageResult);
        }

         PageResult<EmployeeVO> PageResult = SmartPageUtil.convert2PageResult(pageParam, employeeList);
        return ResponseDTO.ok(PageResult);
    }

    /**
     * 新增员工
     *
     * @param employeeAddForm
     * @return
     */
    public synchronized ResponseDTO<String> addEmployee(EmployeeAddForm employeeAddForm) {
        // 校验名称是否重复
        EmployeeEntity employeeEntity = employeeDao.getByLoginName(employeeAddForm.getLoginName(), null);
        if (null != employeeEntity) {
            return ResponseDTO.userErrorParam("登录名重复");
        }
        // 校验姓名是否重复
        employeeEntity = employeeDao.getByActualName(employeeAddForm.getActualName(), null);
        if (null != employeeEntity) {
            return ResponseDTO.userErrorParam("姓名重复");
        }
        // 校验电话是否存在
        employeeEntity = employeeDao.getByPhone(employeeAddForm.getPhone(), null);
        if (null != employeeEntity) {
            return ResponseDTO.userErrorParam("手机号已存在");
        }

        EmployeeEntity entity = SmartBeanUtil.copy(employeeAddForm, EmployeeEntity.class);
        // 设置密码 默认密码
        String password = randomPassword();
        entity.setLoginPwd(getEncryptPwd(password));

        // 保存数据
        entity.setDeletedFlag(Boolean.FALSE);
        employeeManager.saveEmployee(entity);

        return ResponseDTO.ok(password);
    }

    /**
     * 更新员工
     *
     * @param employeeUpdateForm
     * @return
     */
    public synchronized ResponseDTO<String> updateEmployee(EmployeeUpdateForm employeeUpdateForm) {

        Long employeeId = employeeUpdateForm.getEmployeeId();
        EmployeeEntity employeeEntity = employeeDao.selectById(employeeId);
        if (null == employeeEntity) {
            return ResponseDTO.error(UserErrorCode.DATA_NOT_EXIST);
        }

        EmployeeEntity existEntity = employeeDao.getByLoginName(employeeUpdateForm.getLoginName(), null);
        if (null != existEntity && !Objects.equals(existEntity.getEmployeeId(), employeeId)) {
            return ResponseDTO.userErrorParam("登录名重复");
        }

        existEntity = employeeDao.getByPhone(employeeUpdateForm.getPhone(), null);
        if (null != existEntity && !Objects.equals(existEntity.getEmployeeId(), employeeId)) {
            return ResponseDTO.userErrorParam("手机号已存在");
        }

        existEntity = employeeDao.getByActualName(employeeUpdateForm.getActualName(), null);
        if (null != existEntity && !Objects.equals(existEntity.getEmployeeId(), employeeId)) {
            return ResponseDTO.userErrorParam("姓名重复");
        }

        // 不更新密码
        EmployeeEntity entity = SmartBeanUtil.copy(employeeUpdateForm, EmployeeEntity.class);
        entity.setLoginPwd(null);

        // 更新数据
        employeeManager.updateEmployee(entity);

        return ResponseDTO.ok();
    }

    /**
     * 更新禁用/启用状态
     *
     * @param employeeId
     * @return
     */
    public ResponseDTO<String> updateDisableFlag(Long employeeId) {
        if (null == employeeId) {
            return ResponseDTO.error(UserErrorCode.DATA_NOT_EXIST);
        }
        EmployeeEntity employeeEntity = employeeDao.selectById(employeeId);
        if (null == employeeEntity) {
            return ResponseDTO.error(UserErrorCode.DATA_NOT_EXIST);
        }

        boolean disableFlag=!employeeEntity.getDisabledFlag();
        employeeEntity.setDisabledFlag(disableFlag);
        employeeDao.updateDisableFlag(employeeId, disableFlag);

        if (employeeEntity.getDisabledFlag()) {
            tokenService.batchRemoveRedisToken(employeeId, UserTypeEnum.ADMIN_EMPLOYEE);
        }

        return ResponseDTO.ok();
    }

    /**
     * 批量删除员工
     *
     * @param employeeIdList 员工ID列表
     * @return
     */
    public ResponseDTO<String> batchUpdateDeleteFlag(List<Long> employeeIdList) {
        if (CollectionUtils.isEmpty(employeeIdList)) {
            return ResponseDTO.ok();
        }
        List<EmployeeEntity> employeeEntityList = employeeManager.listByIds(employeeIdList);
        if (CollectionUtils.isEmpty(employeeEntityList)) {
            return ResponseDTO.ok();
        }
        // 更新删除
        List<EmployeeEntity> deleteList = employeeIdList.stream().map(e -> {
            EmployeeEntity updateEmployee = new EmployeeEntity();
            updateEmployee.setEmployeeId(e);
            updateEmployee.setDeletedFlag(true);
            return updateEmployee;
        }).collect(Collectors.toList());
        employeeManager.updateBatchById(deleteList);

        for (Long employeeId : employeeIdList) {
            tokenService.batchRemoveRedisToken(employeeId, UserTypeEnum.ADMIN_EMPLOYEE);
        }
        return ResponseDTO.ok();
    }


    /**
     * 批量更新部门
     *
     * @param batchUpdateDepartmentForm
     * @return
     */
    public ResponseDTO<String> batchUpdateDepartment(EmployeeBatchUpdateDepartmentForm batchUpdateDepartmentForm) {
        List<Long> employeeIdList = batchUpdateDepartmentForm.getEmployeeIdList();
        List<EmployeeEntity> employeeEntityList = employeeDao.selectBatchIds(employeeIdList);
        if (employeeIdList.size() != employeeEntityList.size()) {
            return ResponseDTO.error(UserErrorCode.DATA_NOT_EXIST);
        }
        // 更新
        List<EmployeeEntity> updateList = employeeIdList.stream().map(e -> {
            EmployeeEntity updateEmployee = new EmployeeEntity();
            updateEmployee.setEmployeeId(e);
            updateEmployee.setDepartmentId(batchUpdateDepartmentForm.getDepartmentId());
            return updateEmployee;
        }).collect(Collectors.toList());
        employeeManager.updateBatchById(updateList);

        return ResponseDTO.ok();
    }


    /**
     * 更新密码
     *
     * @param updatePasswordForm
     * @return
     */
    public ResponseDTO<String> updatePassword(EmployeeUpdatePasswordForm updatePasswordForm) {
        Long employeeId = updatePasswordForm.getEmployeeId();
        EmployeeEntity employeeEntity = employeeDao.selectById(employeeId);
        if (employeeEntity == null) {
            return ResponseDTO.error(UserErrorCode.DATA_NOT_EXIST);
        }
        // 校验原始密码
        String encryptPwd = getEncryptPwd(updatePasswordForm.getOldPassword());
        if (!Objects.equals(encryptPwd, employeeEntity.getLoginPwd())) {
            return ResponseDTO.userErrorParam("原密码有误，请重新输入");
        }

        // 新旧密码相同
        String newPassword = updatePasswordForm.getNewPassword();
        if (Objects.equals(updatePasswordForm.getOldPassword(), newPassword)) {
            return ResponseDTO.ok();
        }

        // 更新密码
        EmployeeEntity updateEntity = new EmployeeEntity();
        updateEntity.setEmployeeId(employeeId);
        updateEntity.setLoginPwd(getEncryptPwd(newPassword));
        employeeDao.updateById(updateEntity);

        return ResponseDTO.ok();
    }

    /**
     * 重置密码
     *
     * @param employeeId
     * @return
     */
    public ResponseDTO<String> resetPassword(Integer employeeId) {
        String password = randomPassword();
        employeeDao.updatePassword(employeeId, getEncryptPwd(password));
        return ResponseDTO.ok(password);
    }

    private String randomPassword() {
        return RandomStringUtils.randomNumeric(6) + RandomStringUtils.randomAlphabetic(2).toLowerCase();
    }

    /**
     * 获取 加密后 的密码
     *
     * @param password
     * @return
     */
    public static String getEncryptPwd(String password) {
        return DigestUtils.md5Hex(String.format(PASSWORD_SALT_FORMAT, password));
    }


    /**
     * 查询全部员工
     *
     * @return
     */
    public ResponseDTO<List<EmployeeVO>> queryAllEmployee(Boolean disabledFlag) {
        List<EmployeeVO> employeeList = employeeDao.selectEmployeeByDisabledAndDeleted(disabledFlag, Boolean.FALSE);
        return ResponseDTO.ok(employeeList);
    }

    /**
     * 根据登录名获取员工
     *
     * @param loginName
     * @return
     */
    public EmployeeEntity getByLoginName(String loginName) {
        return employeeDao.getByLoginName(loginName, null);
    }

    public static void main(String[] args) {
        System.out.println(getEncryptPwd("123456"));
    }
}
