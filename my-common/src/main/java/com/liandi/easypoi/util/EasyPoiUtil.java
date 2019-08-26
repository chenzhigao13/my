package com.liandi.easypoi.util;

import java.io.File;
import java.util.List;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;

/**
 * @author czg
 * @date 2019/8/7 14:11
 * @description EasyPoi工具类
 */
public class EasyPoiUtil {

    private static final String[] FIELD = new String[] {"name", "sex", "course", "teacher", "score", "grade", "count"};

    private EasyPoiUtil() {}

    public static <T> List<T> importExcel(String file, Class<T> clazz, ImportParams params) {

        return ExcelImportUtil.importExcel(new File(file), clazz, params);

    }

    public static ImportParams getImportParams() {
        ImportParams params = new ImportParams();
        // params.setConcurrentTask(true);
        // params.setDataHandler(new MapImportHandler());
        return params;
    }

}
