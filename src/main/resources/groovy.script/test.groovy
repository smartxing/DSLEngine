package com.github.lb.dsl.engine.DslEngine.engine.script

import com.github.lb.dsl.engine.DslEngine.engine.dsl.DslEngine
import com.google.gson.Gson
import groovy.transform.BaseScript

@BaseScript DslEngine baseScript
rules {

    groupName 'simple rule'
    groupDesc 'simple rule'
    groupVersion '1'

    rule {
        name "N0003"
        desc "name"
        order 3
        when {
            1 == 1
        }
        then {
            fail("李四 == 1234")
        }
    }



    rule {
        name "N0001"
        desc "name"
        order 2 //如果规则由先后顺序的话 配置order
        mapper {
            context.params.get().put("zhangsan", "123")
        }
        when {
            context.params.get().get("zhangsan").equals("123")
        }
        then {
            success()
        }
    }

    rule {
        name "N0002"
        desc "name"
        order 1
        mapper {
            context.params.get().put("lisi", "1234")
        }

        when {
            context.params.get().get("lisi").equals("1234")
        }

        then {
            fail("李四 == 1234")
        }

    }


}

