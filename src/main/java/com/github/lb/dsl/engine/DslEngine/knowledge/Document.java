package com.github.lb.dsl.engine.DslEngine.knowledge;

import java.util.Map;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 2018/8/16 上午8:54 xingliangbo Exp $
 */
public interface Document {

    void put(String key, Object value);


    Object get(String key);


    Map<String,Object> getProperties();

}
