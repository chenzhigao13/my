package com.liandi.design.mode.strategy;

/**
 * 鸭子超类
 * 
 * @author czg
 * @date 2019年7月1日
 *
 */
public abstract class AbstractDuck {

    private FlyBehavior flyBehavior;
    private QuackBehavior quackBehavior;

    public AbstractDuck(FlyBehavior flyBehavior, QuackBehavior quackBehavior) {
        this.flyBehavior = flyBehavior;
        this.quackBehavior = quackBehavior;
    }

    public abstract void disaplay();

    public void fly() {
        flyBehavior.fly();
    }

    public void quack() {
        quackBehavior.quack();
    }

    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    public void setQuackBehavior(QuackBehavior quackBehavior) {
        this.quackBehavior = quackBehavior;
    }

}
