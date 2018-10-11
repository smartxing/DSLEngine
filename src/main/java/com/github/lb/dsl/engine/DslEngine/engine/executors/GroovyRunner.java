package com.github.lb.dsl.engine.DslEngine.engine.executors;

import com.github.lb.dsl.engine.DslEngine.utils.MD5Utils;
import com.google.common.collect.Maps;
import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.lang.Script;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.codehaus.groovy.runtime.InvokerHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class GroovyRunner {

    public static final Logger LOG = LoggerFactory.getLogger(GroovyRunner.class);

    /**
     * groovy 的脚本对象缓存
     */
    private static final Map<String, Script> scriptCache = Maps.newConcurrentMap();

    /**
     * 获取groovy对象
     */
    public static Object runScript(String script, Binding binding) {
        String newMd5 = MD5Utils.getMD5(script);
        final Script finalScript = scriptCache.computeIfAbsent(newMd5, x -> {
            final GroovyShell shell = new GroovyShell();
            try {
                return shell.parse(script);
            } catch (Exception e) {
                LOG.error("can't load script:{}", script, e);
                return null;
            }
        });
        if (finalScript == null) {
            return null;
        }
        try {
            return InvokerHelper.createScript(finalScript.getClass(), binding).run();
        } catch (Exception e) {
            LOG.error("can't load script:{}", script, e);
            return null;
        }
    }


    public static int getCachedScripts() {
        return scriptCache.size();
    }
}
