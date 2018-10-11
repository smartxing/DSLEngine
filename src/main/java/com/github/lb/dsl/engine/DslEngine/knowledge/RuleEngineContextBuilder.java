package com.github.lb.dsl.engine.DslEngine.knowledge;

import com.google.common.collect.Maps;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 2018/8/16 上午9:10 xingliangbo Exp $
 */
public final class RuleEngineContextBuilder {

    private final RuleContext context;

    private RuleEngineContextBuilder() {
        Map<String, Object> contextProperties = Maps.newHashMap();
        context = new RuleContext(contextProperties);
    }

    public static RuleEngineContextBuilder aRuleEngineContext() {
        return new RuleEngineContextBuilder();
    }

    public RuleEngineContextBuilder withParamsPart(Map<String, Object> paramsPart) {
        if (paramsPart != null) {
            paramsPart = new HashMap<>();
        }
        context.put(ParamsPart.PROPERTY, paramsPart);
        return this;
    }

    public RuleEngineContextBuilder withRequestPart(Map<String, Object> requestPart) {
        if (requestPart == null) {
            requestPart = Maps.newHashMap();
        }
        context.put(RequestPart.PROPERTY, requestPart);
        return this;
    }

    public RuleEngineContextBuilder withTmpVarPart(Map<String, Object> tmpVarPart) {
        if (tmpVarPart == null) {
            tmpVarPart = Maps.newHashMap();
        }
        context.put(RequestPart.PROPERTY, tmpVarPart);
        return this;
    }


    public RuleEngineContext build() {

        RuleEngineContext ruleEngineContext = new RuleEngineContext();
        ruleEngineContext.setContext(context);
        if (!context.getParams().isPresent()) {
            context.put(ParamsPart.PROPERTY, new HashMap<String, Object>());
        }
        if (!context.getRequest().isPresent()) {
            context.put(RequestPart.PROPERTY, new HashMap<String, Object>());
        }
        if (!context.getTmpVar().isPresent()) {
            context.put(TmpVarPart.PROPERTY, new HashMap<String, Object>());
        }
        return ruleEngineContext;
    }
}
