package net.lab1024.sa.common.module.support.redis;

import com.alibaba.fastjson.JSON;
import net.lab1024.sa.common.common.domain.SystemEnvironment;
import net.lab1024.sa.common.common.enumeration.SystemEnvironmentEnum;
import net.lab1024.sa.common.common.util.SmartStringUtil;
import net.lab1024.sa.common.constant.RedisKeyConst;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * redis
 *
 */
@Component
public class RedisService {


    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private ValueOperations<String, String> redisValueOperations;

    @Autowired
    private HashOperations<String, String, Object> redisHashOperations;

    @Autowired
    private SystemEnvironment systemEnvironment;


    /**
     * Generate redis key
     * @param prefix
     * @param key
     * @return
     */
    public String generateRedisKey(String prefix, String key) {
        SystemEnvironmentEnum currentEnvironment = systemEnvironment.getCurrentEnvironment();
        return systemEnvironment.getProjectName() + RedisKeyConst.SEPARATOR + currentEnvironment.getValue() +  RedisKeyConst.SEPARATOR + prefix + key;
    }

    /**
     * Delete cache
     *
     * @param key 可以传一个值 或多个
     */
    @SuppressWarnings("unchecked")
    public void delete(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete((Collection<String>) CollectionUtils.arrayToList(key));
            }
        }
    }

    /**
     * Delete cache
     *
     * @param keyList
     */
    public void delete(List<String> keyList) {
        if (CollectionUtils.isEmpty(keyList)) {
            return;
        }
        redisTemplate.delete(keyList);
    }

    //============================String=============================

    /**
     * Get normal cache
     *
     * @param key
     * @return value
     */
    public String get(String key) {
        return key == null ? null : redisValueOperations.get(key);
    }

    public <T> T getObject(String key, Class<T> clazz) {
        Object json = this.get(key);
        if (json == null) {
            return null;
        }
        T obj = JSON.parseObject(json.toString(), clazz);
        return obj;
    }


    /**
     * Set normal cache
     */
    public void set(String key, String value) {
        redisValueOperations.set(key, value);
    }
    public void set(Object key, Object value) {
        String jsonString = JSON.toJSONString(value);
        redisValueOperations.set(key.toString(), jsonString);
    }

    /**
     * Set normal cache
     */
    public void set(String key, String value, long second) {
        redisValueOperations.set(key, value, second, TimeUnit.SECONDS);
    }

    /**
     * Set normal cache and time
     */
    public void set(Object key, Object value, long time) {
        String jsonString = JSON.toJSONString(value);
        if (time > 0) {
            redisValueOperations.set(key.toString(), jsonString, time, TimeUnit.SECONDS);
        } else {
            set(key.toString(), jsonString);
        }
    }

    //============================ map =============================
    public void mset(String key, String hashKey, Object value) {
        redisHashOperations.put(key, hashKey, value);
    }

    public Object mget(String key, String hashKey) {
        return redisHashOperations.get(key, hashKey);
    }

}