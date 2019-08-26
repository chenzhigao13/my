package com.liandi.poitl.policy;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.PictureRenderData;
import com.deepoove.poi.exception.RenderException;
import com.deepoove.poi.policy.AbstractRenderPolicy;
import com.deepoove.poi.render.RenderContext;
import com.deepoove.poi.template.run.RunTemplate;

/**
 * 图片集合渲染策列
 *
 * @author czg
 * @date 2019年7月6日
 */
public class PictureListRenderPolicy extends AbstractRenderPolicy<List<PictureRenderData>> {

    @Override
    protected boolean validate(List<PictureRenderData> dataList) {

        for (PictureRenderData data : dataList) {
            if (null == data.getData() && null == data.getPath()) {
                return false;
            }
        }

        return true;

    }

    @Override
    public void doRender(RunTemplate runTemplate, List<PictureRenderData> datas, XWPFTemplate template)
        throws Exception {
        XWPFRun run = runTemplate.getRun();
        for (PictureRenderData picture : datas) {
            Helper.renderPicture(run, picture);
        }
    }

    @Override
    protected void afterRender(RenderContext context) {
        clearPlaceholder(context, false);
    }

    // @Override
    // protected void doRenderException(RunTemplate runTemplate,
    // List<PictureRenderData> datas, Exception e) {
    // logger.info("Render picture " + runTemplate + " error: {}", e.getMessage());
    // runTemplate.getRun().setText(data.getAltMeta(), 0);
    // }

    public static class Helper {

        public static final int EMU = 9525;

        public static void renderPicture(XWPFRun run, PictureRenderData picture) throws Exception {
            int suggestFileType = suggestFileType(picture.getPath());

            InputStream ins = null == picture.getData() ? new FileInputStream(picture.getPath())
                : new ByteArrayInputStream(picture.getData());

            run.addPicture(ins, suggestFileType, "Generated", picture.getWidth() * EMU, picture.getHeight() * EMU);
        }

        public static int suggestFileType(String imgFile) {
            int format = 0;

            if (imgFile.endsWith(".emf"))
                format = XWPFDocument.PICTURE_TYPE_EMF;
            else if (imgFile.endsWith(".wmf"))
                format = XWPFDocument.PICTURE_TYPE_WMF;
            else if (imgFile.endsWith(".pict"))
                format = XWPFDocument.PICTURE_TYPE_PICT;
            else if (imgFile.endsWith(".jpeg") || imgFile.endsWith(".jpg"))
                format = XWPFDocument.PICTURE_TYPE_JPEG;
            else if (imgFile.endsWith(".png"))
                format = XWPFDocument.PICTURE_TYPE_PNG;
            else if (imgFile.endsWith(".dib"))
                format = XWPFDocument.PICTURE_TYPE_DIB;
            else if (imgFile.endsWith(".gif"))
                format = XWPFDocument.PICTURE_TYPE_GIF;
            else if (imgFile.endsWith(".tiff"))
                format = XWPFDocument.PICTURE_TYPE_TIFF;
            else if (imgFile.endsWith(".eps"))
                format = XWPFDocument.PICTURE_TYPE_EPS;
            else if (imgFile.endsWith(".bmp"))
                format = XWPFDocument.PICTURE_TYPE_BMP;
            else if (imgFile.endsWith(".wpg"))
                format = XWPFDocument.PICTURE_TYPE_WPG;
            else {
                throw new RenderException(
                    "Unsupported picture: " + imgFile + ". Expected emf|wmf|pict|jpeg|png|dib|gif|tiff|eps|bmp|wpg");
            }
            return format;
        }

    }

}
