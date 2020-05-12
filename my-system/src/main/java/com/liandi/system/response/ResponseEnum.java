package com.liandi.system.response;

/**
 * @author czg
 * @date 2019/7/16 8:23
 * @description 响应常量
 */
public enum ResponseEnum {

    /**
     * 处理成功
     */
    OK("处理成功", 200),
    /**
     * 处理错误
     */
    ERROR("处理异常", 500),
    /**
     * 请求体缺失
     */
    HTTP_MESSAGE_NOT_READABLE_ERROR("请求体缺失", 422),
    /**
     * 获取定时任务CronTrigger异常
     */
    GET_CRON_TRIGGER_ERROR("获取定时任务CronTrigger异常", 2001),
    /**
     * 创建定时任务异常
     */
    CREATE_SCHEDULE_JOB_ERROR("创建定时任务异常", 2002),
    /**
     * 更新定时任务异常
     */
    UPDATE_SCHEDULE_JOB_ERROR("创建定时任务异常", 2003),
    /**
     * 立即执行定时任务异常
     */
    RUN_SCHEDULE_JOB_AT_ONCE_ERROR("立即执行定时任务异常", 2004),
    /**
     * 立即执行定时任务异常
     */
    PAUSE_SCHEDULE_JOB_ERROR("暂停定时任务异常", 2005),
    /**
     * 恢复定时任务异常
     */
    RESUME_SCHEDULE_JOB_ERROR("恢复定时任务异常", 2006),
    /**
     * 删除定时任务异常
     */
    DELETE_SCHEDULE_JOB_ERROR("删除定时任务异常", 2007),
    /**
     * 账号或密码不正确
     */
    ACCOUNT_OR_PASSWORD_ERROR("账号或密码不正确", 2996),
    /**
     * 未登录
     */
    SHIRO_NOT_LOGIN_ERROR("未登录", 2997),
    /**
     * 没有操作权限
     */
    SHIRO_UNAUTHC_ERROR("没有操作权限", 2998),
    /**
     * 鉴权或授权过程出错
     */
    SHIRO_ERROR("鉴权或授权过程异常", 2999),
    /**
     * 参数校验错误
     */
    PARAM_VALID_ERROR(306),
    /**
     * 业务错误码
     */
    BUSINESS_ERROR_CODE(2001);

    private String message;
    private int code;

    ResponseEnum(String message, int code) {
        this.message = message;
        this.code = code;
    }

    ResponseEnum(int code) {
        this(null, code);
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
