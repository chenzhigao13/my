package com.liandi.system.job;

/**
 * 定时任务接口，所有定时任务都要实现该接口
 * 
 * @author czg
 * @date 2019/10/28 16:07
 */
public interface ITask {

    /**
     * 执行方法
     */
    void run();

}
