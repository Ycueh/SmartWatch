package net.lab1024.sa.admin.module.system.multiuser.service;

import lombok.extern.slf4j.Slf4j;
import net.lab1024.sa.admin.module.system.dao.multiuser.MultiUserMapper;
import net.lab1024.sa.admin.module.system.dao.user.UserDao;
import net.lab1024.sa.admin.module.system.multiuser.domain.MultiUserAddForm;
import net.lab1024.sa.admin.module.system.multiuser.domain.MultiUserEntity;
import net.lab1024.sa.admin.module.system.multiuser.domain.MultiUserVO;
import net.lab1024.sa.admin.module.system.user.domain.entity.UserEntity;
import net.lab1024.sa.admin.module.system.user.service.UserService;
import net.lab1024.sa.common.common.code.SystemErrorCode;
import net.lab1024.sa.common.common.domain.ResponseDTO;
import net.lab1024.sa.common.common.util.SmartBeanUtil;
import net.lab1024.sa.common.module.support.datatracer.constant.DataTracerTypeEnum;
import net.lab1024.sa.common.module.support.datatracer.service.DataTracerService;
import org.apache.catalina.User;
import org.apache.poi.hssf.record.FilePassRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
@Slf4j
public class MultiUserService {
    @Autowired
    private MultiUserMapper multiUserMapper;
    @Autowired
    private DataTracerService dataTracerService;

    @Autowired
    private UserDao userDao;
    private static final String DBPATH = "."+ File.separator +"database" + File.separator +"smart_admin_v2.db";

    /**
     * Add new user
     *
     * @param addForm
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> add(MultiUserAddForm addForm) {
        MultiUserEntity multiUserEntity = SmartBeanUtil.copy(addForm, MultiUserEntity.class);
        multiUserEntity.setFileData();
        multiUserMapper.insert(multiUserEntity);
        dataTracerService.insert(multiUserEntity.getId(), DataTracerTypeEnum.RESPONSE);
        return ResponseDTO.ok();
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> delete(Long id) {
        multiUserMapper.deleteById(id);
        dataTracerService.delete(id, DataTracerTypeEnum.RESPONSE);
        return ResponseDTO.ok();
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> choose(Long userid) {
        UserEntity user =  userDao.selectById(userid);
        if(user == null){
            return ResponseDTO.error(SystemErrorCode.SYSTEM_ERROR,"User not found");
        }
        MultiUserAddForm multiUserAddForm = new MultiUserAddForm();
        multiUserAddForm.setUser_id(user.getUserId());
        multiUserAddForm.setFile(user.getLoginName());
        add(multiUserAddForm);
        MultiUserVO multiUserVO = multiUserMapper.getFileByUserId(userid);
        File targetFile = new File(DBPATH);
        try {
            if (!targetFile.exists()){
                targetFile.createNewFile();
            }
            try(FileOutputStream output = new FileOutputStream(DBPATH)){
                byte[] fileData = multiUserVO.getFile_data();
                if(fileData == null){
                    return ResponseDTO.error(SystemErrorCode.SYSTEM_ERROR,"File data is null");
                }
                output.write(fileData);
            }

        }catch (IOException e){
            e.printStackTrace();
        }
        dataTracerService.insert(userid, DataTracerTypeEnum.RESPONSE);
        return ResponseDTO.ok();
    }

    //TODO Update the database file
    public ResponseDTO<String> updateFile(Long user_id) {
        try{
            String filePath = "." + File.separator + "database" + File.separator + "smart_admin_v2.db";
            byte[] localData = Files.readAllBytes(Paths.get(filePath));
            multiUserMapper.updateFile(user_id, localData);
            dataTracerService.insert(multiUserMapper.getIdByUserId(user_id), DataTracerTypeEnum.RESPONSE);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Error in reading file");
        }
        return ResponseDTO.ok();
    }





}
