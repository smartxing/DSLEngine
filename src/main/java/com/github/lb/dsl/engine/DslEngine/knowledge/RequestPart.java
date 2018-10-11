package com.github.lb.dsl.engine.DslEngine.knowledge;

import java.util.Map;
import java.util.Optional;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 2018/8/16 上午8:58 xingliangbo Exp $
 */
public interface RequestPart extends Document {

    String PROPERTY = "request";

    default Optional<Map<String,Object>> getRequest() {
        return Optional.ofNullable((Map<String,Object>) get(PROPERTY));
    }

}
