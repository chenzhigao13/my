package com.liandi.common.design.mode.strategy.impl;

import com.liandi.common.design.mode.strategy.QuackBehavior;

/**
 * 呱呱叫
 * 
 * @author czg
 * @date 2019年7月1日
 *
 */
public class Quack implements QuackBehavior {

    @Override
    public void quack() {

        System.out.println("呱呱叫。。。");

    }

}
