package com.liandi.system.dao.param;

import lombok.Data;

/**
 * @author czg
 * @date 2019/7/25 8:58
 * @description 查询用户参数
 */
@Data
public class ListUserParam {

    private String userCode;

    private String userName;

    private String loginName;

    private Long organizationId;

}
