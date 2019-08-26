package com.liandi.design.mode.strategy;

/**
 * 不会叫
 * 
 * @author czg
 * @date 2019年7月1日
 *
 */
public final class MuteQuack implements QuackBehavior {

    @Override
    public void quack() {

        System.out.println("不会叫。。。");

    }

}
