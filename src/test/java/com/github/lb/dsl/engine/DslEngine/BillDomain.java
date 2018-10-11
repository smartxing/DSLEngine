package com.github.lb.dsl.engine.DslEngine;

/**
 * @author xingliangbo
 * @version $Id: v 0.1 2018/8/16 上午9:38 xingliangbo Exp $
 */
public class BillDomain {

    private String name;
    private Float balance;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "BillDomain{" +
            "name='" + name + '\'' +
            ", balance=" + balance +
            '}';
    }
}
