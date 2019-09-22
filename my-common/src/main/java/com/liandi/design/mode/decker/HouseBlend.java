package com.liandi.design.mode.decker;

/**
 * HouseBlend
 * 
 * @author czg
 * @date 2019/9/16 17:08
 */
public class HouseBlend extends Beverage {

    public HouseBlend() {
        description = "HouseBlend";
    }

    @Override
    public double cost() {
        return 89;
    }

}
