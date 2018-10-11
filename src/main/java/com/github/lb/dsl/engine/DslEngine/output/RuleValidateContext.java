package com.github.lb.dsl.engine.DslEngine.output;

import com.github.lb.dsl.engine.DslEngine.engine.dsl.RuleDsl;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.annotations.Expose;
import java.util.List;
import java.util.Map;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 2018/10/11 下午2:29 xingliangbo Exp $
 */
public class RuleValidateContext {
    //是否成功
    private boolean isSuccess;
    //错误码
    private String errorCode;
    //执行结果
    private String desc;
    //规则
    private String ruleName;
    //规则组信息
    private Map<String,String> groupDesc = Maps.newHashMap();
    //数据集合
    private List<Object> errors = Lists.newArrayList();

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public List<Object> getErrors() {
        return errors;
    }

    public void setErrors(List<Object> errors) {
        this.errors = errors;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void addObject(Object object){
        errors.add(object);
    }

    public Map<String, String> getGroupDesc() {
        return groupDesc;
    }

    public void setGroupDesc(Map<String, String> groupDesc) {
        this.groupDesc = groupDesc;
    }
}
