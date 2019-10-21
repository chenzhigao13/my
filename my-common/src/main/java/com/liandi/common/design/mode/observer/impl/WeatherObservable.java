package com.liandi.common.design.mode.observer.impl;

import java.util.Observable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 天气主题，使用java内置观察者模式
 * 
 * @author czg
 * @date 2019/9/16 15:45
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class WeatherObservable extends Observable {

    private float temperature;

    private float humidity;

    public void changed() {
        setChanged();
        notifyObservers();
    }

}
