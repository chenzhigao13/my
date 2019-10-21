package com.liandi.common.design.mode.strategy.impl;

import com.liandi.common.design.mode.strategy.FlyBehavior;

/**
 * 不会飞行为
 * 
 * @author czg
 * @date 2019年7月1日
 *
 */
public class FlyNoWay implements FlyBehavior {

    @Override
    public void fly() {

        System.out.println("我不能飞行！");

    }

}
