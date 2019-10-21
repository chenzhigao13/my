package com.liandi.common.poitl.data;

import java.util.List;

import com.deepoove.poi.data.RowRenderData;

/**
 * 表格详细数据
 * 
 * @author czg
 * @date 2019年7月4日
 *
 */
public class TableDetailData {

    /**
     * 填充数据所在行数
     */
    private Integer startRow;

    /**
     * 列数
     */
    private Integer columnSum;

    /**
     * 行数据
     */
    private List<RowRenderData> rowRenderDataList;

    public Integer getStartRow() {
        return startRow;
    }

    public void setStartRow(Integer startRow) {
        this.startRow = startRow;
    }

    public Integer getColumnSum() {
        return columnSum;
    }

    public void setColumnSum(Integer columnSum) {
        this.columnSum = columnSum;
    }

    public List<RowRenderData> getRowRenderDataList() {
        return rowRenderDataList;
    }

    public void setRowRenderDataList(List<RowRenderData> rowRenderDataList) {
        this.rowRenderDataList = rowRenderDataList;
    }

    @Override
    public String toString() {
        return "TableDetailData [startRow=" + startRow + ", columnSum=" + columnSum + ", rowRenderDataList="
            + rowRenderDataList + "]";
    }

}
