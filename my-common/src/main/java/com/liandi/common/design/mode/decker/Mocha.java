package com.liandi.common.design.mode.decker;

/**
 * Mocha
 * 
 * @author czg
 * @date 2019/9/16 19:00
 */
public class Mocha extends CondimentDecorator {

    Beverage beverage;

    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Mocha";
    }

    @Override
    public double cost() {
        return beverage.cost() + 20;
    }

}
