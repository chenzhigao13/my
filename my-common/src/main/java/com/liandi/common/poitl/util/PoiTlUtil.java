package com.liandi.common.poitl.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.config.ELMode;
import com.liandi.common.poitl.policy.DetailTablePolicy;
import com.liandi.common.poitl.policy.PictureListRenderPolicy;
import com.liandi.common.util.CloseableUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * Java word的模板引擎，对docx格式的文档增加模板语法，简化样式处理，支持对段落、页眉、页脚、表格等模板替换。 poi-tl是基于apache poi的一套拥有简洁API的跨平台的模板引擎。 所有的语法结构都是以 {{
 * 开始，以 }} 结束。 {{template}} 普通文本 {{@template}} 图片 {{#template}} 表格 {{*template}} 列表 {{+template}} 文档
 * 
 * @author czg
 *
 */
@Slf4j
public class PoiTlUtil {

    /**
     * 私有构造
     */
    private PoiTlUtil() {}

    /**
     * 自定义表格标签名
     */
    private static final String TAG_NAME = "detailTable";

    /**
     * 图片集合插件语法：{{%}}
     */
    private static final char PICTURE_LIST_PLUGIN_CHAR = '%';

    /**
     * 创建word
     * 
     * @param template
     * @param model
     * @return
     */
    public static byte[] generateWord(String template, Object model) {

        ByteArrayOutputStream os = null;
        XWPFTemplate tl = null;
        InputStream is = null;
        try {

            is = PoiTlUtil.class.getResourceAsStream(template);

            // 新增渲染策列
            Configure config = Configure.newBuilder().customPolicy(TAG_NAME, new DetailTablePolicy())
                .setElMode(ELMode.SPEL_MODE).addPlugin(PICTURE_LIST_PLUGIN_CHAR, new PictureListRenderPolicy()).build();

            tl = XWPFTemplate.compile(is, config).render(model);

            os = new ByteArrayOutputStream();
            tl.write(os);

            return os.toByteArray();
        } catch (Exception e) {
            log.error("poi-tl生成word错误：", e);
        } finally {

            CloseableUtil.close(is, os);

            try {
                if (Objects.nonNull(tl)) {
                    tl.close();
                }
            } catch (IOException e) {
                log.error("XWPFTemplate 关闭失败：", e);
            }
        }

        return null;
    }

}
