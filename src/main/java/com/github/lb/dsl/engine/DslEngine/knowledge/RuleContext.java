package com.github.lb.dsl.engine.DslEngine.knowledge;

import java.util.Map;
import java.util.Optional;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 2018/8/16 上午9:00 xingliangbo Exp $
 */
public class RuleContext extends AbstractDocument implements ParamsPart,RequestPart,TmpVarPart {

    public RuleContext(Map<String, Object> properties) {
        super(properties);
    }

}
