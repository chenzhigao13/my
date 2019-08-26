package com.liandi.easypoi.handler;

import java.util.HashMap;
import java.util.Map;

import cn.afterturn.easypoi.handler.impl.ExcelDataHandlerDefaultImpl;
import cn.afterturn.easypoi.util.PoiPublicUtil;

/**
 * @author czg
 * @date 2019/8/7 14:25
 * @description Map导入处理
 */
public class MapImportHandler extends ExcelDataHandlerDefaultImpl<Map<String, Object>> {

    private static final Map<String, String> HEAD_FIELD = new HashMap<String, String>() {
        {
            put("姓名", "name");
            put("性别", "sex");
            put("课程", "courseName");
            put("代课老师", "teacher");
            put("分数", "score");
            put("附加分", "grade");
            put("总分", "count");
        }
    };

    @Override
    public void setMapValue(Map<String, Object> map, String originKey, Object value) {
        if (value instanceof Double) {
            map.put(HEAD_FIELD.get(originKey), PoiPublicUtil.doubleToString((Double)value));
        } else {
            map.put(HEAD_FIELD.get(originKey), value != null ? value.toString() : null);
        }
    }

}
