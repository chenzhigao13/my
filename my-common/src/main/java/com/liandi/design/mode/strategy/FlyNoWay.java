package com.liandi.design.mode.strategy;

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
