package com.github.lb.dsl.engine.DslEngine.engine.dsl

import com.github.lb.dsl.engine.DslEngine.knowledge.RuleContext
import com.github.lb.dsl.engine.DslEngine.output.ValidateContext

/**
 *
 * @author xingliangbo
 * @version $Id: v 0.1 2018/8/14 下午7:18 xingliangbo Exp $
 */
class GroupDsl {
    RuleContext context
    ValidateContext output
    Map<String,String> groupParams = [:]

    List<RuleDsl> rules = []

    GroupDsl(context, output, rules) {
        this.context = context
        this.output = output
        this.rules = rules;
    }

    def groupName(String name) {
        groupParams.name = name
    }

    def groupDesc(String desc) {
        groupParams.desc  = desc
    }

    def groupVersion(String version) {
        groupParams.version = version
    }


    def rule(@DelegatesTo(RuleDsl) Closure cl) {
        def rule = new RuleDsl(context, output ,groupParams)
        rules << rule
        def code = cl.rehydrate(rule, this, this)
        code.call()
    }

}
