package com.github.lb.dsl.engine.DslEngine.engine.executors

import com.github.lb.dsl.engine.DslEngine.engine.dsl.RuleDsl
import com.github.lb.dsl.engine.DslEngine.exception.RuleExecutionException
import com.github.lb.dsl.engine.DslEngine.exception.RuleValidationException
import com.github.lb.dsl.engine.DslEngine.knowledge.RuleEngineContext
import com.github.lb.dsl.engine.DslEngine.output.ValidateContext
import groovy.util.logging.Slf4j
import org.apache.commons.lang3.StringUtils

/**
 *
 * @author xingliangbo
 * @version $Id: v 0.1 2018/10/11 下午12:56 xingliangbo Exp $
 */
@Slf4j
class RuleEngineExecutors {


    private RuleEngineContext ruleEngineContext;

    public RuleEngineExecutors(RuleEngineContext ruleEngineContext) {
        this.ruleEngineContext = ruleEngineContext;
    }

    public ValidateContext defination(String scriptName) {
        Binding binding = ruleEngineContext.bindContext();
        Object result = GroovyRunner.runScript(scriptName, binding);
        def ruleDsls = (List<RuleDsl>) result
        validate(scriptName, ruleDsls)//校验规则
        // 对规则进行排序
        Collections.sort(ruleDsls, new Comparator<RuleDsl>() {
            @Override
            int compare(RuleDsl o1, RuleDsl o2) {
                if (o1.getRuleOrder() > o2.getRuleOrder()) {
                    return 1
                } else if (o1.getRuleOrder() > o2.getRuleOrder()) {
                    return 0
                } else {
                    return -1
                }

            }
        })

        for (RuleDsl rule : ruleDsls) {
            try {
                log.debug("")
                if (rule.mapperExpression != null) {
                    rule.mapperExpression()
                }
                if (rule.whenExpression != null && rule.whenExpression()) {
                    rule.thenExpression();
                }
            } catch (Exception e) {
                throw new RuleExecutionException(rule?.name, "Exception executing rule clause", e)
            }
        }

        return binding.getVariable("output")
    }


    private void validate(String scriptName, List<RuleDsl> sourceRules) {

        for (RuleDsl rule : sourceRules) {
            def errors = []
            if (StringUtils.isBlank(rule.name)) {
                errors << "rule field 'name' is not defined"
            }

            if (rule.whenExpression == null) {
                errors << "rule clause 'when' is not defined"
            }

            if (rule.thenExpression == null) {
                errors << "rule clause 'then' is not defined"
            }
            if (!errors.empty) {
                throw new RuleValidationException(rule.name(), scriptName, errors)
            }
        }
    }
}
