package com.liandi.design.mode.observer;

import com.liandi.common.design.mode.observer.Observer;
import com.liandi.common.design.mode.observer.Subject;
import com.liandi.common.design.mode.observer.impl.*;
import org.junit.Test;

/**
 * 观察者策略测试类
 * 
 * @author czg
 * @date 2019/9/16 14:42
 */
public class ObserverTest {

    @Test
    public void testObserver() {

        Subject subject = new Weather(30.1f, 10.9f);

        Observer aObserver = new AObserver();
        Observer bObserver = new BObserver();

        // 注册观察者
        subject.registerObserver(aObserver);
        subject.registerObserver(bObserver);

        subject.notifyObservers();

    }

    @Test
    public void testObservable() {

        WeatherObservable observable = new WeatherObservable(30.1f, 10.9f);

        java.util.Observer cObserver = new CObserver();
        java.util.Observer dObserver = new DObserver();

        observable.addObserver(cObserver);
        observable.addObserver(dObserver);

        observable.changed();

    }

}
