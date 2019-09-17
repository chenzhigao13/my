package com.liandi.design.mode.observer.observer;

import com.liandi.design.mode.observer.observer.base.Observer;

/**
 * B 观察者
 * 
 * @author czg
 * @date 2019/9/16 11:16
 */
public class BObserver implements Observer {

    @Override
    public void update(float temperature, float humidity) {
        System.out.println("B 观察者显示。温度：" + temperature + ", 湿度：" + humidity);
    }

}
