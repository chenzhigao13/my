package com.liandi.common.design.mode.decker;

/**
 * Soy
 * 
 * @author czg
 * @date 2019/9/16 19:00
 */
public class Soy extends CondimentDecorator {

    Beverage beverage;

    public Soy(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Soy";
    }

    @Override
    public double cost() {
        return beverage.cost() + 21;
    }

}
