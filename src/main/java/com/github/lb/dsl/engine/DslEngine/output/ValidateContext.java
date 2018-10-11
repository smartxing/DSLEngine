package com.github.lb.dsl.engine.DslEngine.output;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 2018/10/11 下午2:26 xingliangbo Exp $
 */
public class ValidateContext {

    private boolean success;

    public void setSuccess(boolean success) {
        this.success = success;
    }

    private List<RuleValidateContext> ruleValidateContexts = Lists.newArrayList();

    private Map<String, Object> extraData = Maps.newHashMap();


    public void addRuleValidateContext(RuleValidateContext ruleValidateContext) {
        ruleValidateContexts.add(ruleValidateContext);
        success = isSuccess();
    }


    public boolean isSuccess() {
        final Boolean[] success = {true};
        Optional.of(ruleValidateContexts).orElse(Lists.newArrayList()).stream().forEach(
            ruleValidateContext -> {
                if (!ruleValidateContext.isSuccess()) {
                    //其中有一条执行结束就结束
                    success[0] = false;
                }
            }
        );
        return success[0];
    }


    public Map<String, Object> getExtraData() {
        return extraData;
    }

    public void setExtraData(Map<String, Object> extraData) {
        this.extraData = extraData;
    }

    public List<RuleValidateContext> getRuleValidateContexts() {
        return ruleValidateContexts;
    }

    public void setRuleValidateContexts(
        List<RuleValidateContext> ruleValidateContexts) {
        this.ruleValidateContexts = ruleValidateContexts;
        success = isSuccess();
    }
}
