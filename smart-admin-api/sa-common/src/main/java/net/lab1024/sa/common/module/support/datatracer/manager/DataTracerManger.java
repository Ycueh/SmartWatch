package net.lab1024.sa.common.module.support.datatracer.manager;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.lab1024.sa.common.module.dao.DataTracerDao;
import net.lab1024.sa.common.module.support.datatracer.domain.entity.DataTracerEntity;
import org.springframework.stereotype.Service;

/**
 * manager layer
 *
 */
@Service
public class DataTracerManger extends ServiceImpl<DataTracerDao, DataTracerEntity> {
}
