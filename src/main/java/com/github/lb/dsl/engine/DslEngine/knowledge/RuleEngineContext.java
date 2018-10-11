package com.github.lb.dsl.engine.DslEngine.knowledge;

import com.github.lb.dsl.engine.DslEngine.output.ValidateContext;
import com.google.common.collect.Maps;
import groovy.lang.Binding;
import java.util.Map;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 2018/8/16 上午9:03 xingliangbo Exp $
 */
public class RuleEngineContext {

    private RuleContext ruleContext;

    private final String PROPERTIES = "context";

    private final String OUT_PUT_PROPERTIES = "output";

    public RuleContext getContext() {
        return ruleContext;
    }

    public void setContext(RuleContext context) {
        this.ruleContext = context;
    }

    public Binding bindContext(){
        Map<String,Object> binding = Maps.newHashMap();
        binding.put(PROPERTIES,ruleContext);
        binding.put(OUT_PUT_PROPERTIES,new ValidateContext());
        return new Binding(binding);
    }

}
