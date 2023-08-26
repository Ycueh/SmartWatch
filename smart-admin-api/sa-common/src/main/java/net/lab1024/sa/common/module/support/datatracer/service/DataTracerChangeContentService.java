package net.lab1024.sa.common.module.support.datatracer.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.google.common.base.CaseFormat;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import net.lab1024.sa.common.common.util.SmartBigDecimalUtil;
import net.lab1024.sa.common.common.util.SmartEnumUtil;
import net.lab1024.sa.common.common.util.SmartStringUtil;
import net.lab1024.sa.common.module.support.datatracer.annoation.*;
import net.lab1024.sa.common.module.support.datatracer.constant.DataTracerConst;
import net.lab1024.sa.common.module.support.datatracer.domain.bo.DataTracerContentBO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Content of Data Change
 *
 */
@Slf4j
@Service
public class DataTracerChangeContentService {

    @Autowired
    private ApplicationContext applicationContext;
    /**
     * Field description cache
     */
    private ConcurrentHashMap<String, String> fieldDescCacheMap = new ConcurrentHashMap<>();

    /**
     * Class annotated field cache
     */
    private ConcurrentHashMap<Class, List<Field>> fieldMap = new ConcurrentHashMap<>();

    /**
     * Batch comparison of data
     *
     * @param oldObjectList
     * @param newObjectList
     * @param <T>
     * @return
     */
    public <T> String getChangeContent(List<T> oldObjectList, List<T> newObjectList) {
        boolean valid = this.valid(oldObjectList, newObjectList);
        if (!valid) {
            return "";
        }
        String operateType = this.getOperateType(oldObjectList, newObjectList);
        String operateContent = "";
        if (DataTracerConst.INSERT.equals(operateType) || DataTracerConst.DELETE.equals(operateType)) {
            operateContent = this.getObjectListContent(newObjectList);
            if (StringUtils.isEmpty(operateContent)) {
                return "";
            }
            return operateType + ":" + operateContent;
        }
        if (DataTracerConst.UPDATE.equals(operateType)) {
            return this.getUpdateContentList(oldObjectList, newObjectList);
        }
        return operateContent;
    }


    /**
     * Parse changes of multiple objects: addition, deletion, editing
     * If oldObject is null and newObject is not null, then it's an addition
     * If oldObject is not null and newObject is null, then it's a deletion
     * If both are not null, then it's an edit
     *
     * @param oldObject
     * @param newObject
     * @return
     */
    public String getChangeContent(Object oldObject, Object newObject) {
        boolean valid = this.valid(oldObject, newObject);
        if (!valid) {
            return "";
        }
        String operateType = this.getOperateType(oldObject, newObject);
        String operateContent = "";
        if (DataTracerConst.INSERT.equals(operateType) || DataTracerConst.DELETE.equals(operateType)) {
            operateContent = this.getAddDeleteContent(newObject);
        }
        if (DataTracerConst.UPDATE.equals(operateType)) {
            operateContent = this.getUpdateContent(oldObject, newObject);
        }
        if (StringUtils.isEmpty(operateContent)) {
            return "";
        }
        return operateContent;
    }

    /**
     * Parse the content of a single bean
     *
     * @param object
     * @return
     */
    public String getChangeContent(Object object) {
        return this.getAddDeleteContent(object);
    }

    // ---------------------------- Below are private methods ----------------------------

    /**
     * Get the content of add or delete operation
     *
     * @param object Object being added or deleted
     * @return
     */
    private String getAddDeleteContent(Object object) {
        List<Field> fields = this.getField(object);
        Map<String, DataTracerContentBO> beanParseMap = this.fieldParse(object, fields);
        return this.getAddDeleteContent(beanParseMap);
    }

    /**
     * Change content of a single object
     *
     * @param oldObjectList
     * @param newObjectList
     * @param <T>
     * @return
     */
    private <T> String getUpdateContentList(List<T> oldObjectList, List<T> newObjectList) {
        String oldContent = this.getObjectListContent(oldObjectList);
        String newContent = this.getObjectListContent(newObjectList);
        if (oldContent.equals(newContent)) {
            return "";
        }
        if (StringUtils.isEmpty(oldContent) && StringUtils.isEmpty(newContent)) {
            return "";
        }
        return "【Original Data】:<br/>" + oldContent + "<br/>" + "【New Data】:<br/>" + newContent;
    }

    /**
     * Get the content information of an object
     *
     * @param objectList
     * @param <T>
     * @return
     */
    private <T> String getObjectListContent(List<T> objectList) {
        if (CollectionUtils.isEmpty(objectList)) {
            return "";
        }
        List<Field> fields = this.getField(objectList.get(0));
        List<String> contentList = Lists.newArrayList();
        for (Object objItem : objectList) {
            Map<String, DataTracerContentBO> beanParseMap = this.fieldParse(objItem, fields);
            contentList.add(this.getAddDeleteContent(beanParseMap));
        }
        return StringUtils.join(contentList, "<br/>");
    }



    private String getAddDeleteContent(Map<String, DataTracerContentBO> beanParseMap) {
        List<String> contentList = new ArrayList<>();
        for (Entry<String, DataTracerContentBO> entry : beanParseMap.entrySet()) {
            DataTracerContentBO dataTracerContentBO = entry.getValue();
            Boolean jsonFlag = JSONUtil.isTypeJSON(dataTracerContentBO.getFieldContent());
            String filedDesc = dataTracerContentBO.getFieldDesc();
            if (jsonFlag) {
                contentList.add(filedDesc + "(请进入详情查看)");
            } else {
                contentList.add(dataTracerContentBO.getFieldDesc() + ":" + dataTracerContentBO.getFieldContent());
            }
        }
        String operateContent = StringUtils.join(contentList, "<br/>");
        if (StringUtils.isEmpty(operateContent)) {
            return "";
        }
        return operateContent;
    }


    /**
     * Acquire update contents
     *
     * @param oldObject
     * @param newObject
     * @return
     */
    private <T> String getUpdateContent(T oldObject, T newObject) {
        List<Field> fields = this.getField(oldObject);
        List<String> contentList = new ArrayList<>();
        Map<String, DataTracerContentBO> oldBeanParseMap = this.fieldParse(oldObject, fields);
        Map<String, DataTracerContentBO> newBeanParseMap = this.fieldParse(newObject, fields);
        for (Entry<String, DataTracerContentBO> entry : oldBeanParseMap.entrySet()) {
            String fieldName = entry.getKey();
            // Old and new are the same
            DataTracerContentBO oldContentBO = entry.getValue();
            DataTracerContentBO newContentBO = newBeanParseMap.get(fieldName);
            // fieldContent
            String oldContent = oldContentBO.getFieldContent() == null ? "" : oldContentBO.getFieldContent();
            String newContent = newContentBO.getFieldContent() == null ? "" : newContentBO.getFieldContent();

            if (oldContent.equals(newContent)) {
                continue;
            }
            String fieldDesc = oldContentBO.getFieldDesc();
            Boolean jsonFlag = JSONUtil.isTypeJSON(oldContent) || JSONUtil.isTypeJSON(newContent);
            if (jsonFlag) {
                String content = fieldDesc + "【Check details】";
                contentList.add(content);
                continue;
            }
            String content = fieldDesc + ":" + "Change from【" + oldContent + "】to【" + newContent + "】";
            contentList.add(content);
        }
        if (CollectionUtils.isEmpty(contentList)) {
            return "";
        }
        String operateContent = StringUtils.join(contentList, "<br/>");
        if (StringUtils.isEmpty(operateContent)) {
            return "";
        }
        return operateContent;
    }


    /**
     * bean
     *
     * @param object
     * @param fields
     * @return <desc,value></>
     */
    private Map<String, DataTracerContentBO> fieldParse(Object object, List<Field> fields) {
        if (fields == null || fields.size() == 0) {
            return new HashMap<>();
        }
        Map<String, DataTracerContentBO> objectParse = new HashMap<>(16);
        for (Field field : fields) {
            field.setAccessible(true);
            String desc = this.getFieldDesc(field);
            if (StringUtils.isEmpty(desc)) {
                continue;
            }
            DataTracerContentBO dataTracerContentBO = this.getFieldValue(field, object);
            if (dataTracerContentBO != null) {
                dataTracerContentBO.setFieldDesc(desc);
                objectParse.put(field.getName(), dataTracerContentBO);
            }
        }
        return objectParse;
    }

    /**
     * Acquire field value
     *
     * @param field
     * @param object
     * @return
     */
    private DataTracerContentBO getFieldValue(Field field, Object object) {
        Object fieldValue = "";
        Class clazz = object.getClass();
        try {
            PropertyDescriptor pd = new PropertyDescriptor((String)field.getName(), clazz);
            Method get = pd.getReadMethod();
            fieldValue = get.invoke(object);
        } catch (Exception e) {
            log.error("bean operate log: reflect field value error " + field.getName());
            return null;
        }
        if (fieldValue == null) {
            return null;
        }

        String fieldContent = "";
        DataTracerFieldEnum dataTracerFieldEnum = field.getAnnotation(DataTracerFieldEnum.class);
        DataTracerFieldSql dataTracerFieldSql = field.getAnnotation(DataTracerFieldSql.class);
        DataTracerFieldDict dataTracerFieldDict = field.getAnnotation(DataTracerFieldDict.class);
        if (dataTracerFieldEnum != null) {
            if (fieldValue instanceof Collection) {
                fieldContent = SmartEnumUtil.getEnumDescByValueList((Collection) fieldValue, dataTracerFieldEnum.enumClass());
            } else {
                fieldContent = SmartEnumUtil.getEnumDescByValue(fieldValue, dataTracerFieldEnum.enumClass());
            }
        }  else if (dataTracerFieldSql != null) {
            fieldContent = this.getRelateDisplayValue(fieldValue, dataTracerFieldSql);
        } else if (fieldValue instanceof Date) {
            fieldContent = DateUtil.formatDateTime((Date) fieldValue);
        } else if (fieldValue instanceof LocalDateTime) {
            fieldContent = LocalDateTimeUtil.formatNormal((LocalDateTime) fieldValue);
        } else if (fieldValue instanceof LocalDate) {
            fieldContent = LocalDateTimeUtil.formatNormal((LocalDate) fieldValue);
        } else if (fieldValue instanceof BigDecimal) {
            DataTracerFieldBigDecimal dataTracerFieldBigDecimal = field.getAnnotation(DataTracerFieldBigDecimal.class);
            if (dataTracerFieldBigDecimal != null) {
                BigDecimal value = SmartBigDecimalUtil.setScale((BigDecimal) fieldValue, dataTracerFieldBigDecimal.scale());
                fieldContent = value.toString();
            }
        } else {
            fieldContent = JSON.toJSONString(fieldValue);
        }
        DataTracerContentBO dataTracerContentBO = new DataTracerContentBO();
        dataTracerContentBO.setField(field);
        dataTracerContentBO.setFieldValue(fieldValue);
        dataTracerContentBO.setFieldContent(fieldContent);
        return dataTracerContentBO;
    }

    /**
     * Get the display value of the associated field
     *
     * @param fieldValue
     * @return
     */
    private String getRelateDisplayValue(Object fieldValue, DataTracerFieldSql dataTracerFieldSql) {
        Class<? extends BaseMapper> relateMapper = dataTracerFieldSql.relateMapper();
        BaseMapper mapper = applicationContext.getBean(relateMapper);
        if (mapper == null) {
            return "";
        }
        String relateFieldValue = fieldValue.toString();
        QueryWrapper qw = new QueryWrapper();
        qw.select(CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, dataTracerFieldSql.relateDisplayColumn()));
        qw.in(CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, dataTracerFieldSql.relateColumn()), relateFieldValue);
        List<Object> displayValue = mapper.selectObjs(qw);
        if (CollectionUtils.isEmpty(displayValue)) {
            return "";
        }
        return SmartStringUtil.join(",", displayValue);
    }

    /**
     * Get field description information. Prioritize OperateField, otherwise use swagger judgment
     *
     * @param field
     * @return
     */
    private String getFieldDesc(Field field) {
        // Query based on field name from cache
        String fieldName = field.toGenericString();
        String desc = fieldDescCacheMap.get(fieldName);
        if (null != desc) {
            return desc;
        }
        DataTracerFieldLabel operateField = field.getAnnotation(DataTracerFieldLabel.class);
        if (operateField != null) {
            return operateField.value();
        }
        fieldDescCacheMap.put(fieldName, desc);
        return desc;
    }

    /**
     * Get operation type
     *
     * @param oldObject
     * @param newObject
     * @return
     */
    private String getOperateType(Object oldObject, Object newObject) {
        if (oldObject == null && newObject != null) {
            return DataTracerConst.INSERT;
        }
        if (oldObject != null && newObject == null) {
            return DataTracerConst.DELETE;
        }
        return DataTracerConst.UPDATE;
    }

    /**
     * Check whether to perform comparison
     *
     * @param oldObject
     * @param newObject
     * @return
     */
    private boolean valid(Object oldObject, Object newObject) {
        if (oldObject == null && newObject == null) {
            return false;
        }
        if (oldObject == null && newObject != null) {
            return true;
        }
        if (oldObject != null && newObject == null) {
            return true;
        }
        if (oldObject != null && newObject != null) {
            String oldClass = oldObject.getClass().getName();
            String newClass = newObject.getClass().getName();
            return oldClass.equals(newClass);
        }
        return true;
    }

    /**
     * Check
     *
     * @param oldObjectList
     * @param newObjectList
     * @param <T>
     * @return
     */
    private <T> boolean valid(List<T> oldObjectList, List<T> newObjectList) {
        if (CollectionUtils.isEmpty(oldObjectList) && CollectionUtils.isEmpty(newObjectList)) {
            return false;
        }
        if (CollectionUtils.isEmpty(oldObjectList) && CollectionUtils.isNotEmpty(newObjectList)) {
            return true;
        }
        if (CollectionUtils.isNotEmpty(oldObjectList) && CollectionUtils.isEmpty(newObjectList)) {
            return true;
        }
        if (CollectionUtils.isNotEmpty(oldObjectList) && CollectionUtils.isNotEmpty(newObjectList)) {
            T oldObject = oldObjectList.get(0);
            T newObject = newObjectList.get(0);
            String oldClass = oldObject.getClass().getName();
            String newClass = newObject.getClass().getName();
            return oldClass.equals(newClass);
        }
        return true;
    }

    /**
     * Query for fields with file key annotations
     * Use cache
     *
     * @param obj
     * @return
     */
    private List<Field> getField(Object obj) {
        // Query from cache
        Class tClass = obj.getClass();
        List<Field> fieldList = fieldMap.get(tClass);
        if (null != fieldList) {
            return fieldList;
        }

        // This recursive code is to get properties from the superclass
        Class tempClass = tClass;
        fieldList = new ArrayList<>();
        while (tempClass != null) {
            Field[] declaredFields = tempClass.getDeclaredFields();
            for (Field field : declaredFields) {
                // Filter fields with annotations
                if (!field.isAnnotationPresent(DataTracerFieldLabel.class)) {
                    continue;
                }
                field.setAccessible(true);
                fieldList.add(field);
            }
            tempClass = tempClass.getSuperclass();
        }
        fieldMap.put(tClass, fieldList);
        return fieldList;
    }



}
