package com.liandi.design.mode.strategy;

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
