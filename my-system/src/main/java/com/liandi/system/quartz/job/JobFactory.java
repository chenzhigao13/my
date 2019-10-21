package com.liandi.system.quartz.job;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Component;

/**
 * job工厂类
 * 
 * @author czg
 * @date 2019/10/18 17:15
 */
@Component
public class JobFactory extends AdaptableJobFactory {

    @Autowired
    private AutowireCapableBeanFactory capableBeanFactory;

    @Override
    protected Object createJobInstance(TriggerFiredBundle bundle) {
        // 调用父类的方法
        Object jobInstance = createJobInstance(bundle);
        // 进行注入
        capableBeanFactory.autowireBean(jobInstance);

        return jobInstance;
    }

}
