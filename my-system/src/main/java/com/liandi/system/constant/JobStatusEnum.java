package com.liandi.system.constant;

import lombok.Getter;

/**
 * job状态
 * 
 * @author czg
 * @date 2019/10/28 11:11
 */
public enum JobStatusEnum {

    /**
     * 正常
     */
    NORMAL("01"),
    /**
     * 暂停
     */
    PAUSE("02");

    @Getter
    private String status;

    JobStatusEnum(String status) {
        this.status = status;
    }

}
