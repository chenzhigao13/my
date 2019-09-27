package com.liandi.design.mode.strategy.impl;

import com.liandi.design.mode.strategy.AbstractDuck;
import com.liandi.design.mode.strategy.FlyBehavior;
import com.liandi.design.mode.strategy.QuackBehavior;

/**
 * 野鸭
 * 
 * @author czg
 * @date 2019年7月2日
 *
 */
public class MallardDuck extends AbstractDuck {

    public MallardDuck(FlyBehavior flyBehavior, QuackBehavior quackBehavior) {
        super(flyBehavior, quackBehavior);
    }

    @Override
    public void disaplay() {

        System.out.println("我是黑色的野鸭。。。");

    }

}
