package com.github.lb.dsl.engine.DslEngine.engine.script

import com.github.lb.dsl.engine.DslEngine.engine.dsl.DslEngine
import com.google.common.collect.Maps
import com.google.gson.Gson
import groovy.transform.BaseScript

@BaseScript DslEngine baseScript
rules {

    rule {
        name "N0001"
        when {
            println("执行结果:" + context.params.orElse(Maps.newHashMap()).billdomain.name.equals("张三"))
            context.params.orElse(Maps.newHashMap()).billdomain.name.equals("张三")
        }
        then {
            success("billdomain.name =  张三")
        }
    }

}

