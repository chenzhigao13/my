package com.liandi.system.dao.param;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author czg
 * @date 2019/8/2 14:25
 * @description 分页查询用户参数
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class ListPageUserParam extends AbstractPageParam {

    private String userCode;

    private String userName;

    private Long organizationId;

    private String phone;

    private String email;

}
