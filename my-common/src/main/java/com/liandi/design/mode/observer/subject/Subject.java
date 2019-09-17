package com.liandi.design.mode.observer.subject;

import com.liandi.design.mode.observer.observer.Observer;

/**
 * 主题接口
 * 
 * @author czg
 * @date 2019/9/16 10:12
 */
public interface Subject {

    /**
     * 注册观察者
     * 
     * @param observer
     */
    void registerObserver(Observer observer);

    /**
     * 删除观察者
     * 
     * @param observer
     */
    void removeObserver(Observer observer);

    /**
     * 通知观察者
     */
    void notifyObservers();

}
