package com.awx.moxu.utils.blade;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.BeanProperty;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.cglib.beans.BeanGenerator;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.cglib.core.CodeGenerationException;
import org.springframework.cglib.core.Converter;
import org.springframework.util.Assert;

public class BeanUtil extends BeanUtils {
    public BeanUtil() {
    }

    public static <T> T newInstance(Class<?> clazz) {
        return (T) instantiateClass(clazz);
    }

    public static <T> T newInstance(String clazzStr) {
        try {
            Class<?> clazz = Class.forName(clazzStr);
            return newInstance(clazz);
        } catch (ClassNotFoundException var2) {
            throw new RuntimeException(var2);
        }
    }

    public static Object getProperty(Object bean, String propertyName) {
        Assert.notNull(bean, "bean Could not null");
        return BeanMap.create(bean).get(propertyName);
    }

    public static void setProperty(Object bean, String propertyName, Object value) {
        Assert.notNull(bean, "bean Could not null");
        BeanMap.create(bean).put(propertyName, value);
    }

    public static <T> T copyProperties(Object source, Class<T> target) throws BeansException {
        T to = newInstance(target);
        copyProperties(source, to);
        return to;
    }

    public static Map<String, Object> toMap(Object bean) {
        return BeanMap.create(bean);
    }

    public static <T> T toBean(Map<String, Object> beanMap, Class<T> valueType) {
        T bean = newInstance(valueType);
        BeanMap.create(bean).putAll(beanMap);
        return bean;
    }

    public static PropertyDescriptor[] getBeanGetters(Class type) {
        return getPropertiesHelper(type, true, false);
    }

    public static PropertyDescriptor[] getBeanSetters(Class type) {
        return getPropertiesHelper(type, false, true);
    }

    private static PropertyDescriptor[] getPropertiesHelper(Class type, boolean read, boolean write) {
        try {
            PropertyDescriptor[] all = getPropertyDescriptors(type);
            if (read && write) {
                return all;
            } else {
                List<PropertyDescriptor> properties = new ArrayList(all.length);
                PropertyDescriptor[] var5 = all;
                int var6 = all.length;

                for(int var7 = 0; var7 < var6; ++var7) {
                    PropertyDescriptor pd = var5[var7];
                    if (read && pd.getReadMethod() != null) {
                        properties.add(pd);
                    } else if (write && pd.getWriteMethod() != null) {
                        properties.add(pd);
                    }
                }

                return (PropertyDescriptor[])properties.toArray(new PropertyDescriptor[0]);
            }
        } catch (BeansException var9) {
            throw new CodeGenerationException(var9);
        }
    }
}
