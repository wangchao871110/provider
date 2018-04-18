package com.xfs.sps.utils;

import java.util.Map;

import com.fasterxml.jackson.core.Versioned;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.xfs.bill.dto.PropertyMapping;
import com.xfs.common.util.StringUtils;
import com.xfs.enums.AreaInsuranceType;

/**
 * Created by konglc on 2016-09-05.
 */
public class JacksonAnnotationFieldUtil extends JacksonAnnotationIntrospector implements Versioned {

    private Map<String,String> fileMap;

    public JacksonAnnotationFieldUtil(Map<String, String> fileMap){
        this.fileMap = fileMap;
    }

    @Override
    public PropertyName findNameForDeserialization(Annotated af) {
        return deserializablePropertyName(af);
    }

    private PropertyName deserializablePropertyName(Annotated af) {
        PropertyMapping annotation = af.getAnnotation(PropertyMapping.class);
        if (annotation != null) {
            AreaInsuranceType value = annotation.value();
            if (null != value.getFileName() && value.getFileName().length > 0) {
                String use_key = null;
                for(String key : value.getFileName()){
                    for(Map.Entry<String,String> entry :fileMap.entrySet()){
                        String file_key = entry.getKey().trim();
                        if(file_key.equals(key)){
                            use_key = key;
                            break;
                        }
                    }
                }
                if(!StringUtils.isBlank(use_key))
                    return new PropertyName(use_key);
            }
        }
        return super.findNameForDeserialization(af);
    }

}
