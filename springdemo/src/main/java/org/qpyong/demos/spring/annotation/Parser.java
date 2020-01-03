package org.qpyong.demos.spring.annotation;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class Parser {

    public String getId(Object obj){
        return ObjectUtils.getIdentityHexString(obj).toUpperCase();
    }

}
