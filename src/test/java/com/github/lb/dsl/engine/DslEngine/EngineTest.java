package com.github.lb.dsl.engine.DslEngine;

import com.github.lb.dsl.engine.DslEngine.engine.executors.RuleEngineExecutors;
import com.github.lb.dsl.engine.DslEngine.knowledge.RuleEngineContext;
import com.github.lb.dsl.engine.DslEngine.knowledge.RuleEngineContextBuilder;
import com.github.lb.dsl.engine.DslEngine.output.ValidateContext;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 2018/8/14 下午7:50 xingliangbo Exp $
 */
public class EngineTest {

    @Test
    public void test() throws Exception {
        RuleEngineContext ruleEngineContext = RuleEngineContextBuilder.aRuleEngineContext().build();
        Map<String, Object> paramsPart = ruleEngineContext.getContext().getParams().get();
        BillDomain billDomain = new BillDomain();
        billDomain.setName("张三");
        billDomain.setBalance(2.01f);
        paramsPart.put("billdomain", billDomain);
        RuleEngineExecutors ruleEngineExecutors = new RuleEngineExecutors(ruleEngineContext);
        String io = IOUtils.toString(new FileInputStream(
            "/Users/xingliangbo/Documents/workspace_bill/DslEngine/src/main/resources/groovy.script/test2.groovy"));
        ValidateContext validateContext = ruleEngineExecutors.defination(io);
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        System.out.println(gson.toJson(validateContext));
    }
}
