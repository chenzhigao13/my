package com.liandi.easypoi.handler;

import java.util.Map;

import cn.afterturn.easypoi.handler.impl.ExcelDataHandlerDefaultImpl;
import cn.afterturn.easypoi.util.PoiPublicUtil;

/**
 * @author czg
 * @date 2019/8/7 14:25
 * @description Map导入处理
 */
public class MapImportHandler extends ExcelDataHandlerDefaultImpl<Map<String, Object>> {

    @Override
    public void setMapValue(Map<String, Object> map, String originKey, Object value) {
        if (value instanceof Double) {
            map.put(getRealKey(originKey), PoiPublicUtil.doubleToString((Double)value));
        } else {
            map.put(getRealKey(originKey), value != null ? value.toString() : null);
        }
    }

    private String getRealKey(String originKey) {
        if ("姓名".equals(originKey)) {
            return "name";
        }
        if ("身份证".equals(originKey)) {
            return "sfz";
        }
        if ("班主任寄语".equals(originKey)) {
            return "content";
        }
        if ("学科".equals(originKey)) {
            return "subjectName";
        }
        if ("学科成绩".equals(originKey)) {
            return "subjectScore";
        }
        if ("子学科".equals(originKey)) {
            return "childSubjectName";
        }
        if ("子学科成绩".equals(originKey)) {
            return "childSubjectScore";
        }
        return originKey;
    }

}
