package com.github.lb.dsl.engine.DslEngine.knowledge;

import java.util.Map;
import java.util.Objects;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 2018/8/16 上午8:54 xingliangbo Exp $
 */
public abstract class AbstractDocument implements Document {

    private final Map<String, Object> properties;

    protected AbstractDocument(Map<String, Object> properties) {
        Objects.requireNonNull(properties, "properties map is required");
        this.properties = properties;
    }

    @Override
    public void put(String key, Object value) {
        properties.put(key, value);
    }

    @Override
    public Object get(String key) {
        return properties.get(key);
    }

    @Override
    public Map<String, Object> getProperties() {
        return properties;
    }
}
