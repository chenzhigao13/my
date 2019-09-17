package com.liandi.design.mode.decker;

/**
 * 饮料抽象类，装饰者模式
 * 
 * @author czg
 * @date 2019/9/16 17:02
 */
public abstract class Beverage {

    String description = "unknown Beverage";

    public String getDescription() {
        return description;
    }

    public abstract double cost();

}
