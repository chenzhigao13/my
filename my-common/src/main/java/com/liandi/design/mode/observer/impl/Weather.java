package com.liandi.design.mode.observer.impl;

import java.util.List;

import com.google.common.collect.Lists;
import com.liandi.design.mode.observer.Observer;
import com.liandi.design.mode.observer.Subject;

/**
 * 天气主题
 * 
 * @author czg
 * @date 2019/9/16 10:59
 */
public class Weather implements Subject {

    private float temperature;

    private float humidity;

    private List<Observer> observerList = Lists.newArrayList();

    public Weather(float temperature, float humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
    }

    @Override
    public void registerObserver(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        int indexOf = observerList.indexOf(observer);
        if (indexOf >= 0) {
            observerList.remove(indexOf);
        }
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observerList) {
            observer.update(temperature, humidity);
        }
    }

    public void setChanged(float temperature, float humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
        notifyObservers();
    }

}
