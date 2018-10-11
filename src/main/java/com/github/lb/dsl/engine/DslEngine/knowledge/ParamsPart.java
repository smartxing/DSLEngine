package com.github.lb.dsl.engine.DslEngine.knowledge;

import java.util.Map;
import java.util.Optional;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 2018/8/16 上午8:58 xingliangbo Exp $
 */
public interface ParamsPart extends Document {

    String PROPERTY = "params";

    default Optional<Map<String,Object>> getParams() {
        return Optional.ofNullable((Map<String,Object>) get(PROPERTY));
    }

}
