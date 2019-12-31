package com.liandi.common.poitl.data;

import java.util.List;

import com.deepoove.poi.data.RowRenderData;

import lombok.Data;

/**
 * 表格详细数据
 * 
 * @author czg
 * @date 2019年7月4日
 *
 */
@Data
public class TableData {

    /**
     * 填充数据所在行数
     */
    private Integer startColumn;

    /**
     * 列数
     */
    private Integer columnSum;

    /**
     * 行数据
     */
    private List<RowRenderData> rowRenderDataList;

}
