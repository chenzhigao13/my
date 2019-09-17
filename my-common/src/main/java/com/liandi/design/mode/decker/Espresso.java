package com.liandi.design.mode.decker;

import com.liandi.design.mode.decker.base.Beverage;

/**
 * Espresso
 * 
 * @author czg
 * @date 2019/9/16 17:08
 */
public class Espresso extends Beverage {

    public Espresso() {
        description = "Espresso";
    }

    @Override
    public double cost() {
        return 1.99;
    }

}
