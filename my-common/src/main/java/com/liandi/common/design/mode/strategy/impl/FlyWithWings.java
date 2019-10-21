package com.liandi.common.design.mode.strategy.impl;

import com.liandi.common.design.mode.strategy.FlyBehavior;

/**
 * 会飞
 * 
 * @author czg
 * @date 2019年7月1日
 *
 */
public class FlyWithWings implements FlyBehavior {

    @Override
    public void fly() {

        System.out.println("我能飞行！");

    }

}
