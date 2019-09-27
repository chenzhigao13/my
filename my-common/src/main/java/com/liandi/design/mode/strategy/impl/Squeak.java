package com.liandi.design.mode.strategy.impl;

import com.liandi.design.mode.strategy.QuackBehavior;

/**
 * 吱吱叫
 * 
 * @author czg
 * @date 2019年7月1日
 *
 */
public class Squeak implements QuackBehavior {

    @Override
    public void quack() {

        System.out.println("吱吱叫。。。");

    }

}
