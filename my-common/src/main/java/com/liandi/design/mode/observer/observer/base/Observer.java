package com.liandi.design.mode.observer.observer.base;

/**
 * 观察者接口
 * 
 * @author czg
 * @date 2019/9/16 10:16
 */
public interface Observer {

    /**
     * 跟新
     */
    void update(float temperature, float humidity);

}
