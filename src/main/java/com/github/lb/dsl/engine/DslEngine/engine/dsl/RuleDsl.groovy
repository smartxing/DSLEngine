package com.github.lb.dsl.engine.DslEngine.engine.dsl

import com.github.lb.dsl.engine.DslEngine.knowledge.RuleContext
import com.github.lb.dsl.engine.DslEngine.output.RuleValidateContext
import com.github.lb.dsl.engine.DslEngine.output.ValidateContext
import com.google.gson.annotations.Expose

/**
 *
 * @author xingliangbo
 * @version $Id: v 0.1 2018/8/14 下午8:20 xingliangbo Exp $
 */
class RuleDsl {
    //当前规则信息
    RuleContext context
    ValidateContext output
    def description
    def name
    def ruleOrder = Integer.MIN_VALUE;
    //规则组信息
    Map<String,String> groupParams


    RuleDsl(context, output, params) {
        this.context = context
        this.output = output
        this.groupParams = params
    }

    Closure mapperExpression
    Closure whenExpression
    Closure thenExpression

    def success(RuleValidateContext ruleValidateContext) {
        output.addRuleValidateContext(ruleValidateContext)
    }

    def success(String desc) {
        RuleValidateContext ruleValidateContext = new RuleValidateContext()
        ruleValidateContext.success = true
        ruleValidateContext.ruleName = name
        ruleValidateContext.groupDesc = groupParams
        ruleValidateContext.desc = desc
        output.addRuleValidateContext(ruleValidateContext)
    }

    def success() {
        RuleValidateContext ruleValidateContext = new RuleValidateContext()
        ruleValidateContext.success = true
        ruleValidateContext.ruleName = name
        ruleValidateContext.groupDesc = groupParams
        output.addRuleValidateContext(ruleValidateContext)
    }

    def fail(String desc, Object obj) {
        RuleValidateContext ruleValidateContext = new RuleValidateContext()
        ruleValidateContext.success = false
        ruleValidateContext.ruleName = name
        ruleValidateContext.groupDesc = groupParams
        ruleValidateContext.desc = desc
        output.addRuleValidateContext(ruleValidateContext)
    }


    def fail(String desc) {
        RuleValidateContext ruleValidateContext = new RuleValidateContext()
        ruleValidateContext.success = false
        ruleValidateContext.ruleName = name
        ruleValidateContext.groupDesc = groupParams
        ruleValidateContext.desc = desc
        output.addRuleValidateContext(ruleValidateContext)
    }

    def name(String name) {
        this.name = name
    }

    def order(Integer ruleOrder){
        this.ruleOrder = ruleOrder
    }

    def desc(String description) {
        this.description = description
    }

    def mapper(Closure closure) {
        this.mapperExpression = closure
    }


    def when(Closure closure) {
        this.whenExpression = closure
    }

    def then(Closure closure) {
        this.thenExpression = closure
    }

}
