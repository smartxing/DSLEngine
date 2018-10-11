#### 规则引擎

```ftl
规则数据校验 日常开发中必不可少的  开源的drool确实比较强大，但是针对简单的规则校验确用不着这么复杂的系统，
所以用groovy 来实现一个实现一个mini版本的规则引擎，可以灵活的定制，适合自己的需求
``` 

#### 实现思想

                                      groovy
                                        |
                                        |  load(加载对应的goovy脚本）
                                        | 
    输入源（mq,kafka http）   -->     [规则引擎]      -->    输出接口(mq, kafka ,http) --> 监控告警 报表展示 
 

##### 规则语法
```groovy
@BaseScript DslEngine baseScript
rules {
    groupName 'rule engin'  
    groupDesc 'rule desc'
    groupVersion '1'

    rule {
        name "N0001"    //规则名称
        desc "name"     //规则用途
        order 1      
        mapper {        //前置操作， 比如对输入的数据做一些计算
            context.params.get().put("zhangsan", "123")
        }

        when {         //规则 LHS
            context.params.get().get("zhangsan").equals("123")
        }

        then {        // 规则 RHS
            success()
        }

    }
    
    rule {
            name "N0002"
            desc "name"
            ...  
          
    
        }

}

```

##### 执行

```java
        /**
        * 构造输入参数， 参数会默认帮你构造好 其实就是一个 Map<Map<>> 结构
        * ruleEngineContext.getContext() 获取规则执行的上下文，上下文分3块区域    
        * ruleEngineContext.getContext().getParams()  //参数区域
        * ruleEngineContext.getContext().getRequest() //请求的数据
        * ruleEngineContext.getContext().getTmpVar()  //临时变量存放的区域
        * 这3块区域 可随便使用 一般的放在一块 request 区域就行了  
        */
        RuleEngineContext ruleEngineContext = RuleEngineContextBuilder.aRuleEngineContext().build();
        Map<String, Object> paramsPart = ruleEngineContext.getContext().getParams().get();
        //设置需要校验的对象
        BillDomain billDomain = new BillDomain();
        billDomain.setName("张三");
        billDomain.setBalance(2.01f);
        paramsPart.put("billdomain", billDomain);
        //构造执行器
        RuleEngineExecutors ruleEngineExecutors = new RuleEngineExecutors(ruleEngineContext);
        String io = IOUtils.toString(new FileInputStream("路径自行修改resources/groovy.script/test2.groovy"));
        //执行产生结果
        ValidateContext validateContext = ruleEngineExecutors.defination(io);
        //后续就可以对结果 报表展示啊 或者数据补救措施
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        System.out.println(gson.toJson(validateContext));
```
##### 规则编写
```groovy
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

```

##### 输出结果
```properties

执行结果:true

ValidateContext 对象内容
{
	"success": true,
	"ruleValidateContexts": [{
		"isSuccess": true,
		"desc": "billdomain.name =  张三",
		"ruleName": "N0001",
		"groupDesc": {},
		"errors": []
	}],
	"extraData": {}
}


```
#####  提供了一些常用的 方法  比如 success() fail() 标识本次结果校验是否成功 
#### 对执行的结果解释一下
```properties
{
	"success": true,  最终的结果是否成功
	"ruleValidateContexts": [{
		"isSuccess": true,   当前规则是否执行成功
		"desc": "billdomain.name =  张三",  //规则的描述信息
		"ruleName": "N0001",    //规则名称
		"groupDesc": {},  //规则group 信息 可以不填 没乱用
		"errors": []   // 执行错误信息 可以自定义添加
	}],
	"extraData": {}
}

```

#####  本工程只是实现了规则执行模块  可以灵活扩展自由话定制
```properties
实现参考：http://docs.groovy-lang.org/docs/latest/html/documentation/core-domain-specific-languages.html#section-delegatesto
```