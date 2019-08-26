package com.liandi.poitl.policy;

import java.util.List;
import java.util.Objects;

import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import com.deepoove.poi.data.CellRenderData;
import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.policy.DynamicTableRenderPolicy;
import com.deepoove.poi.policy.MiniTableRenderPolicy;
import com.liandi.poitl.data.TableDetailData;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author czg
 * @date 2019年7月4日
 *
 */
@Slf4j
public class DetailTablePolicy extends DynamicTableRenderPolicy {

    @Override
    public void render(XWPFTable table, Object data) {

        if (Objects.isNull(data)) {
            log.info("data is null");
            return;
        }

        if (!(data instanceof TableDetailData)) {
            log.info("data is not instanceof TableDetailData");
            return;
        }

        TableDetailData detailData = (TableDetailData)data;
        if (Objects.isNull(detailData.getColumnSum()) || Objects.isNull(detailData.getStartRow())) {
            log.info("TableDetailData.columnSum or TableDetailData.startRow is null");
            return;
        }

        if (detailData.getColumnSum() < 1 || detailData.getStartRow() < 0) {
            log.info("TableDetailData.columnSum < 1 or TableDetailData.startRow < 0");
            return;
        }

        List<RowRenderData> rowRenderDataList = detailData.getRowRenderDataList();
        if (Objects.isNull(rowRenderDataList) || rowRenderDataList.isEmpty()) {
            log.info("TableDetailData.rowRenderDataList is null");
            return;
        }

        int startRow = detailData.getStartRow();
        // 数据循环渲染
        table.removeRow(startRow);
        // 循环插入行
        for (RowRenderData rowRenderData : rowRenderDataList) {
            List<CellRenderData> cellDataList = rowRenderData.getCellDatas();
            String renderData = cellDataList.get(0).getRenderData().getText();

            XWPFTableRow row = table.getRow(startRow);
            row.getCell(0).setText(renderData);

            XWPFTableRow insertNewTableRow = table.insertNewTableRow(startRow);
            for (int j = 0, columnSum = detailData.getColumnSum(); j < columnSum; j++) {
                insertNewTableRow.createCell();
            }

            // 合并单元格
            // TableTools.mergeCellsHorizonal(table, startRow, 0, 3);

            // 渲染单行数据
            MiniTableRenderPolicy.Helper.renderRow(table, startRow, rowRenderData);
        }

    }

}
