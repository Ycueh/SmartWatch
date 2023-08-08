package net.lab1024.sa.admin.module.smartWatch.multiuser.service;

import lombok.extern.slf4j.Slf4j;
import net.lab1024.sa.admin.module.smartWatch.dao.multiuser.MultiUserMapper;
import net.lab1024.sa.admin.module.smartWatch.multiuser.domain.MultiUserAddForm;
import net.lab1024.sa.admin.module.smartWatch.multiuser.domain.MultiUserEntity;
import net.lab1024.sa.common.common.domain.ResponseDTO;
import net.lab1024.sa.common.common.util.SmartBeanUtil;
import net.lab1024.sa.common.module.support.datatracer.constant.DataTracerTypeEnum;
import net.lab1024.sa.common.module.support.datatracer.service.DataTracerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Service
@Slf4j
public class MultiUserService {
    @Autowired
    private MultiUserMapper multiUserMapper;
    @Autowired
    private DataTracerService dataTracerService;

    /**
     * Add new user
     *
     * @param addForm
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> add(MultiUserAddForm addForm) {
        MultiUserEntity multiUserEntity = SmartBeanUtil.copy(addForm, MultiUserEntity.class);
        multiUserMapper.insert(multiUserEntity);
        CreateNewFile(multiUserEntity.getFileName());
        dataTracerService.insert(multiUserEntity.getId(), DataTracerTypeEnum.RESPONSE);
        return ResponseDTO.ok();
    }

    private void CreateNewFile(String fileName) {
        String userFileName = fileName + ".db";
        String relativePath = "src\\main\\java\\net\\lab1024\\sa\\admin\\module\\smartWatch\\multiuser\\UserFiles\\" + userFileName;
        String url = "jdbc:sqlite:" + relativePath;

        try {
            Connection conn = DriverManager.getConnection(url);
            if (conn != null) {
                System.out.println("Connected to the database.");
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}
