package com.liandi.common.easypoi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author czg
 * @date 2019/8/16 10:54
 * @description 附加分
 */
@Data
@Accessors(chain = true)
public class Extra {

    @Excel(name = "附加分", isImportField = "true_st")
    private String grade;

}
