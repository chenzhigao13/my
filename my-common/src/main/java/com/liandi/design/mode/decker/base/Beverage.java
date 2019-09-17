package com.liandi.design.mode.decker.base;

/**
 * 饮料抽象类，装饰者模式
 * 
 * @author czg
 * @date 2019/9/16 17:02
 */
public abstract class Beverage {

    protected String description = "unknown Beverage";

    public String getDescription() {
        return description;
    }

    public abstract double cost();

}
