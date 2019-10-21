package com.liandi.system.dao.param;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author czg
 * @date 2019/8/13 20:18
 * @description 分页查询组织参数
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class ListPageOrganizationParam extends AbstractPageParam {

    private String organizationName;

    private Long parentOrganizationId;

}
