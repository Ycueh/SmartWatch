package net.lab1024.sa.admin.module.system.multiuser.service;

import lombok.extern.slf4j.Slf4j;
import net.lab1024.sa.admin.module.system.dao.multiuser.MultiUserMapper;
import net.lab1024.sa.admin.module.system.multiuser.domain.MultiUserAddForm;
import net.lab1024.sa.admin.module.system.multiuser.domain.MultiUserEntity;
import net.lab1024.sa.common.common.domain.ResponseDTO;
import net.lab1024.sa.common.common.util.SmartBeanUtil;
import net.lab1024.sa.common.module.support.datatracer.constant.DataTracerTypeEnum;
import net.lab1024.sa.common.module.support.datatracer.service.DataTracerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;

@Service
@Slf4j
public class MultiUserService {
    @Autowired
    private MultiUserMapper multiUserMapper;
    @Autowired
    private DataTracerService dataTracerService;

    private static final String RELATIVEPATH = "." + File.separator + "sa-admin" + File.separator +"src" + File.separator +"main"
            + File.separator + "resources" + File.separator + "UserFiles";
    private static final String DBPATH = "smart-admin-api"+ File.separator +"smart-admin-api" + File.separator +"database";

    /**
     * Add new user
     *
     * @param addForm
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> add(MultiUserAddForm addForm) {
        MultiUserEntity multiUserEntity = SmartBeanUtil.copy(addForm, MultiUserEntity.class);
        CreateNewFile(multiUserEntity.getFileName());
        multiUserMapper.createNewTable();
        multiUserMapper.insert(multiUserEntity);
        dataTracerService.insert(multiUserEntity.getId(), DataTracerTypeEnum.RESPONSE);
        return ResponseDTO.ok();
    }

    private void CreateNewFile(String fileName) {
        String userFileName = fileName.trim() + ".db";
        File file = new File( RELATIVEPATH + File.separator+ userFileName);
        try {
            // 如果文件不存在，则创建新文件
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName() + " in " + file.getAbsolutePath());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }

    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> delete(Long id) {
        multiUserMapper.deleteById(id);
        dataTracerService.delete(id, DataTracerTypeEnum.RESPONSE);
        DeleteFile(multiUserMapper.getFileNameByUserId(id));
        return ResponseDTO.ok();
    }

    private void DeleteFile(String fileName) {
        String userFileName = fileName.trim() + ".db";
        File file = new File(RELATIVEPATH + File.separator + userFileName);
        try {
            if (file.delete()) {
                System.out.println(file.getName() + " is deleted!");
            } else {
                System.out.println("Delete operation is failed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> choose(Long userid) {
        ChooseFile(multiUserMapper.getFileNameByUserId(userid));
        dataTracerService.insert(userid, DataTracerTypeEnum.RESPONSE);
        return ResponseDTO.ok();
    }

    private void ChooseFile(String fileName) {
        String userFileName = fileName.trim() + ".db";
        File file = new File(RELATIVEPATH + File.separator + userFileName);
        try {
            if (file.delete()) {
                System.out.println(file.getName() + " is deleted!");
            } else {
                System.out.println("Delete operation is failed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //TODO Update the database file
    public ResponseDTO<String> update(Long userId) {

        return ResponseDTO.ok();
    }





}
