package com.xfs.bill.dto;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.fasterxml.jackson.annotation.JacksonAnnotation;
import com.xfs.enums.AreaInsuranceType;

/**
 * Created by konglc on 2016-09-05.
 */
@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotation
public @interface PropertyMapping {

    /**
     * 地区 社保 字段 枚举
     * @return
     */
    AreaInsuranceType value();
}
