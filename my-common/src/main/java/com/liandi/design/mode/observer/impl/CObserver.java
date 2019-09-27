package com.liandi.design.mode.observer.impl;

import java.util.Observable;
import java.util.Observer;

/**
 * C 观察者，使用java内置观察者模式
 * 
 * @author czg
 * @date 2019/9/16 15:48
 */
public class CObserver implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof WeatherObservable) {
            WeatherObservable weatherObservable = (WeatherObservable)o;
            System.out.println(
                "C 观察者显示。温度：" + weatherObservable.getTemperature() + ", 湿度：" + weatherObservable.getHumidity());
        }
    }

}
