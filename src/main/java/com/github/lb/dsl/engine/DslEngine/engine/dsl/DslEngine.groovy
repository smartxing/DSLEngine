package com.github.lb.dsl.engine.DslEngine.engine.dsl

import com.github.lb.dsl.engine.DslEngine.knowledge.RuleContext
import com.github.lb.dsl.engine.DslEngine.output.ValidateContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 *
 * @author xingliangbo
 * @version $Id: v 0.1 2018/8/14 下午7:14 xingliangbo Exp $
 */
abstract class DslEngine extends Script {

    private Logger LOGGER = LoggerFactory.getLogger(DslEngine);

    List<RuleDsl> rules = []

    def rules(@DelegatesTo(GroupDsl) Closure cl) {
        RuleContext context = this.binding.getVariable('context')
        ValidateContext output = this.binding.getVariable('output')
        def groupDsl = new GroupDsl(context, output,rules)
        def code = cl.rehydrate(groupDsl, this, this)
        code.resolveStrategy = Closure.DELEGATE_ONLY
        code.call();
    }



    def run() {

        try {
            final result = runCode()
        } finally {
        }
        LOGGER.info("request context : {} ",context)
        return rules
    }

    abstract def runCode()

}
