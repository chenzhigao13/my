package com.liandi.common.poitl.policy;

import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import com.deepoove.poi.policy.DynamicTableRenderPolicy;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author czg
 * @date 2019年7月4日
 *
 */
@Slf4j
public class TablePolicy extends DynamicTableRenderPolicy {

    @Override
    public void render(XWPFTable table, Object data) {

        // if (Objects.isNull(data)) {
        // log.info("data is null");
        // return;
        // }
        //
        // if (!(data instanceof TableData)) {
        // log.info("data is not instanceof TableData");
        // return;
        // }
        //
        // TableData tableData = (TableData)data;
        // if (Objects.isNull(tableData.getColumnSum()) || Objects.isNull(tableData.getStartColumn())) {
        // log.info("TableData.columnSum or TableData.startColumn is null");
        // return;
        // }
        //
        // if (tableData.getColumnSum() < 1 || tableData.getStartColumn() < 0) {
        // log.info("TableData.columnSum < 1 or TableData.startColumn < 0");
        // return;
        // }
        //
        // List<RowRenderData> rowRenderDataList = tableData.getRowRenderDataList();
        // if (Objects.isNull(rowRenderDataList) || rowRenderDataList.isEmpty()) {
        // log.info("TableDetailData.rowRenderDataList is null");
        // return;
        // }

        XWPFTableRow row1 = table.getRow(0);
        XWPFTableCell cell1 = new XWPFTableCell(row1.getCtRow().insertNewTc(3), row1, table.getBody());
        XWPFTableCell cell2 = new XWPFTableCell(row1.getCtRow().insertNewTc(3), row1, table.getBody());

        XWPFTableRow row2 = table.getRow(1);
        XWPFTableCell cell3 = new XWPFTableCell(row2.getCtRow().insertNewTc(3), row2, table.getBody());
        XWPFTableCell cell4 = new XWPFTableCell(row2.getCtRow().insertNewTc(3), row2, table.getBody());

        XWPFTableRow row3 = table.getRow(2);
        XWPFTableCell cell5 = new XWPFTableCell(row3.getCtRow().insertNewTc(3), row3, table.getBody());
        XWPFTableCell cell6 = new XWPFTableCell(row3.getCtRow().insertNewTc(3), row3, table.getBody());

    }

}
