package com.github.lb.dsl.engine.DslEngine.knowledge;

import java.util.Map;
import java.util.Optional;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 2018/8/16 上午10:12 xingliangbo Exp $
 */
public interface TmpVarPart extends Document {

    final String PROPERTY = "tmpvar";

    default Optional<Map<String,Object>> getTmpVar() {
        return Optional.ofNullable((Map<String,Object>) get(PROPERTY));
    }


}
