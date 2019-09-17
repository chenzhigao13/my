package com.liandi.design.mode.decker;

import com.liandi.design.mode.decker.base.Beverage;
import com.liandi.design.mode.decker.base.CondimentDecorator;

/**
 * Whip
 * 
 * @author czg
 * @date 2019/9/16 19:00
 */
public class Whip extends CondimentDecorator {

    Beverage beverage;

    public Whip(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Whip";
    }

    @Override
    public double cost() {
        return beverage.cost() + 22;
    }

}
