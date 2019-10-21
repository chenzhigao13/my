package com.liandi.system.dao.domain;

import com.baomidou.mybatisplus.annotation.*;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author czg
 * @date 2019/7/25 9:25
 * @description 权限DO
 */
@Data
@TableName("sys_power")
@Accessors(chain = true)
public class PowerDO {

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @TableField("power_name")
    private String powerName;

    @TableField("power_url")
    private String powerUrl;

    @TableField("power_type")
    private String powerType;

    @TableField(value = "parent_power_id", updateStrategy = FieldStrategy.IGNORED)
    private Long parentPowerId;

    private Integer sort;

    @TableField(exist = false)
    private PowerDO parentPower;

}
