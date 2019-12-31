package com.liandi.poitl;

import java.io.File;
import java.io.IOException;
import java.util.*;

import org.junit.Before;
import org.junit.Test;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJc;

import com.deepoove.poi.data.PictureRenderData;
import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.data.style.Style;
import com.deepoove.poi.data.style.TableStyle;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import com.liandi.common.poitl.data.TableData;
import com.liandi.common.poitl.data.TableDetailData;
import com.liandi.common.poitl.util.PoiTlUtil;

import lombok.Data;

/**
 * WordUtil测试类
 * 
 * @author czg
 * @date 2019年7月3日
 *
 */
public class PoiTlUtilTest {

    private Style headTextStyle = new Style();
    private TableStyle headStyle = new TableStyle();
    private TableStyle rowStyle = new TableStyle();
    private TableDetailData detailTable = new TableDetailData();

    @Before
    public void init() {
        headTextStyle.setFontFamily("Hei");
        headTextStyle.setFontSize(9);
        headTextStyle.setColor("7F7F7F");

        headStyle.setBackgroundColor("F2F2F2");
        headStyle.setAlign(STJc.CENTER);

        rowStyle.setAlign(STJc.CENTER);

        // RowRenderData header = RowRenderData.build(new TextRenderData("日期",
        // headTextStyle),
        // new TextRenderData("订单编号", headTextStyle), new TextRenderData("销售代表",
        // headTextStyle),
        // new TextRenderData("离岸价", headTextStyle), new TextRenderData("发货方式",
        // headTextStyle),
        // new TextRenderData("条款", headTextStyle), new TextRenderData("税号",
        // headTextStyle));
        // header.setRowStyle(headStyle);
        //
        // RowRenderData row = RowRenderData.build("2018-06-12", "SN18090", "李四",
        // "5000元", "快递", "附录A", "T11090");
        // row.setRowStyle(rowStyle);
        // MiniTableRenderData miniTableRenderData = new MiniTableRenderData(header,
        // Arrays.asList(row),
        // MiniTableRenderData.WIDTH_A4_MEDIUM_FULL);
        // miniTableRenderData.setStyle(headStyle);

        RowRenderData good = RowRenderData.build("4", "墙纸", "书房+卧室", "1500", "/", "400", "1600");
        good.setRowStyle(rowStyle);
        List<RowRenderData> rowRenderDataList = Arrays.asList(good, good, good);
        detailTable.setColumnSum(7);
        detailTable.setStartRow(2);
        detailTable.setRowRenderDataList(rowRenderDataList);
    }

    @Test
    public void testGenerateWord() throws IOException {

        String template = "/poitl/template.docx";
        Model model = new Model();
        model.setDetailTable(detailTable);
        PictureRenderData pictureRenderData =
            new PictureRenderData(300, 300, ".png", Files.toByteArray(new File("D://test.jpg")));
        model.setImageKey(Arrays.asList(pictureRenderData, pictureRenderData));
        model.setNowDate(new Date());
        model.setSex(true);
        Map<String, String> m = Maps.newHashMap();
        m.put("k01", "测试map");
        model.setM(m);
        model.setTable(new TableData());

        Files.write(Objects.requireNonNull(PoiTlUtil.generateWord(template, model)), new File("D://poi-tl.docx"));

    }

    @Data
    private static class Model {
        private List<PictureRenderData> imageKey;
        private Boolean sex;
        private Date nowDate;
        private TableDetailData detailTable;
        private TableData table;
        // 使用 el 表达式时，不能用Map
        private Map<String, String> m;
    }

}
