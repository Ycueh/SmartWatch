package net.lab1024.sa.admin.module.system.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.sa.admin.module.system.dao.user.UserDao;
import net.lab1024.sa.admin.module.system.multiuser.domain.MultiUserAddForm;
import net.lab1024.sa.admin.module.system.multiuser.service.MultiUserService;
import net.lab1024.sa.admin.module.system.user.domain.entity.UserEntity;
import net.lab1024.sa.admin.module.system.user.domain.form.*;
import net.lab1024.sa.admin.module.system.user.domain.vo.UserVO;
import net.lab1024.sa.admin.module.system.user.manager.UserManager;
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
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * User service
 *
 */
@Service
public class UserService {

    private static final String PASSWORD_SALT_FORMAT = "smart_%s_admin_$^&*";

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserManager userManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private MultiUserService multiUserService;


    public UserEntity getById(Long userId) {
        return userDao.selectById(userId);
    }


    /**
     * 查询员工列表
     *
     * @param userQueryForm
     * @return
     */
    public ResponseDTO<PageResult<UserVO>> queryUser(UserQueryForm userQueryForm) {
        userQueryForm.setDeletedFlag(false);
        Page pageParam = SmartPageUtil.convert2PageQuery(userQueryForm);


        List<UserVO> userList = userDao.queryUser(pageParam, userQueryForm);
        if (CollectionUtils.isEmpty(userList)) {
            PageResult<UserVO> PageResult = SmartPageUtil.convert2PageResult(pageParam, userList);
            return ResponseDTO.ok(PageResult);
        }

         PageResult<UserVO> PageResult = SmartPageUtil.convert2PageResult(pageParam, userList);
        return ResponseDTO.ok(PageResult);
    }

    /**
     * Add user
     *
     * @param userAddForm
     * @return
     */
    public synchronized ResponseDTO<String> addUser(UserAddForm userAddForm) {
        // Check repeated login name
        UserEntity userEntity = userDao.getByLoginName(userAddForm.getLoginName(), null);
        if (null != userEntity) {
            return ResponseDTO.userErrorParam("Login name existed");
        }
        // Check repeated real name
        userEntity = userDao.getByActualName(userAddForm.getActualName(), null);
        if (null != userEntity) {
            return ResponseDTO.userErrorParam("Real name existed");
        }
        // Check repeated phone number
        userEntity = userDao.getByPhone(userAddForm.getPhone(), null);
        if (null != userEntity) {
            return ResponseDTO.userErrorParam("Phone number existed");
        }

        UserEntity entity = SmartBeanUtil.copy(userAddForm, UserEntity.class);
        // Set password
        String password = randomPassword();
        entity.setLoginPwd(getEncryptPwd(password));

        // Save the data
        entity.setDeletedFlag(Boolean.FALSE);
        userManager.saveUser(entity);
        //TODO Add a default database file
        MultiUserAddForm multiUserAddForm = new MultiUserAddForm();
        multiUserAddForm.setUser_id(null);
        multiUserAddForm.setFile(null);
        multiUserService.add(multiUserAddForm);

        return ResponseDTO.ok(password);
    }

    /**
     * Update user
     *
     * @param userUpdateForm
     * @return
     */
    public synchronized ResponseDTO<String> updateUser(UserUpdateForm userUpdateForm) {

        Long userId = userUpdateForm.getUserId();
        UserEntity userEntity = userDao.selectById(userId);
        if (null == userEntity) {
            return ResponseDTO.error(UserErrorCode.DATA_NOT_EXIST);
        }

        UserEntity existEntity = userDao.getByLoginName(userUpdateForm.getLoginName(), null);
        if (null != existEntity && !Objects.equals(existEntity.getUserId(), userId)) {
            return ResponseDTO.userErrorParam("Repeated login name");
        }

        existEntity = userDao.getByPhone(userUpdateForm.getPhone(), null);
        if (null != existEntity && !Objects.equals(existEntity.getUserId(), userId)) {
            return ResponseDTO.userErrorParam("Repeated phone number");
        }

        existEntity = userDao.getByActualName(userUpdateForm.getActualName(), null);
        if (null != existEntity && !Objects.equals(existEntity.getUserId(), userId)) {
            return ResponseDTO.userErrorParam("Repeated real name");
        }

        UserEntity entity = SmartBeanUtil.copy(userUpdateForm, UserEntity.class);
        entity.setLoginPwd(null);

        userManager.updateUser(entity);

        return ResponseDTO.ok();
    }

    /**
     * Set disable
     *
     * @param userId
     * @return
     */
    public ResponseDTO<String> updateDisableFlag(Long userId) {
        if (null == userId) {
            return ResponseDTO.error(UserErrorCode.DATA_NOT_EXIST);
        }
        UserEntity userEntity = userDao.selectById(userId);
        if (null == userEntity) {
            return ResponseDTO.error(UserErrorCode.DATA_NOT_EXIST);
        }

        boolean disableFlag=!userEntity.getDisabledFlag();
        userEntity.setDisabledFlag(disableFlag);
        userDao.updateDisableFlag(userId, disableFlag);

        if (userEntity.getDisabledFlag()) {
            tokenService.batchRemoveRedisToken(userId, UserTypeEnum.ADMIN_USER);
        }

        return ResponseDTO.ok();
    }

    /**
     * batchdelte user
     *
     * @param userIdList 员工ID列表
     * @return
     */
    public ResponseDTO<String> batchUpdateDeleteFlag(List<Long> userIdList) {
        if (CollectionUtils.isEmpty(userIdList)) {
            return ResponseDTO.ok();
        }
        List<UserEntity> userEntityList = userManager.listByIds(userIdList);
        if (CollectionUtils.isEmpty(userEntityList)) {
            return ResponseDTO.ok();
        }
        // 更新删除
        List<UserEntity> deleteList = userIdList.stream().map(e -> {
            UserEntity updateUser = new UserEntity();
            updateUser.setUserId(e);
            updateUser.setDeletedFlag(true);
            return updateUser;
        }).collect(Collectors.toList());
        userManager.updateBatchById(deleteList);

        for (Long userId : userIdList) {
            tokenService.batchRemoveRedisToken(userId, UserTypeEnum.ADMIN_USER);
        }
        return ResponseDTO.ok();
    }


    /**
     * Update password
     *
     * @param updatePasswordForm
     * @return
     */
    public ResponseDTO<String> updatePassword(UserUpdatePasswordForm updatePasswordForm) {
        Long userId = updatePasswordForm.getUserId();
        UserEntity userEntity = userDao.selectById(userId);
        if (userEntity == null) {
            return ResponseDTO.error(UserErrorCode.DATA_NOT_EXIST);
        }
        //Check original password
        String encryptPwd = getEncryptPwd(updatePasswordForm.getOldPassword());
        if (!Objects.equals(encryptPwd, userEntity.getLoginPwd())) {
            return ResponseDTO.userErrorParam("Original password is wrong");
        }

        // The old password and new password are the same
        String newPassword = updatePasswordForm.getNewPassword();
        if (Objects.equals(updatePasswordForm.getOldPassword(), newPassword)) {
            return ResponseDTO.ok();
        }

        // Update password
        UserEntity updateEntity = new UserEntity();
        updateEntity.setUserId(userId);
        updateEntity.setLoginPwd(getEncryptPwd(newPassword));
        userDao.updateById(updateEntity);

        return ResponseDTO.ok();
    }

    /**
     * Reset password
     *
     * @param userId
     * @return
     */
    public ResponseDTO<String> resetPassword(Integer userId) {
        String password = randomPassword();
        userDao.updatePassword(userId, getEncryptPwd(password));
        return ResponseDTO.ok(password);
    }

    private String randomPassword() {
        return RandomStringUtils.randomNumeric(6) + RandomStringUtils.randomAlphabetic(2).toLowerCase();
    }

    /**
     * Get encrypt password
     *
     * @param password
     * @return
     */
    public static String getEncryptPwd(String password) {
        return DigestUtils.md5Hex(String.format(PASSWORD_SALT_FORMAT, password));
    }


    /**
     * Acquire all users
     *
     * @return
     */
    public ResponseDTO<List<UserVO>> queryAllUser(Boolean disabledFlag) {
        List<UserVO> userList = userDao.selectUserByDisabledAndDeleted(disabledFlag, Boolean.FALSE);
        return ResponseDTO.ok(userList);
    }

    /**
     * Acquire user by login name
     *
     * @param loginName
     * @return
     */
    public UserEntity getByLoginName(String loginName) {
        return userDao.getByLoginName(loginName, null);
    }

    public static void main(String[] args) {
        System.out.println(getEncryptPwd("123456"));
    }
}
