package com.liandi;

import java.io.Closeable;
import java.io.IOException;
import java.util.Objects;

import lombok.extern.slf4j.Slf4j;

/**
 * @author czg
 * @date 2019/8/19 11:18
 * @description Closeable 工具类
 */
@Slf4j
public class CloseableUtil {

    private CloseableUtil() {}

    public static void close(Closeable... cc) {

        if (Objects.isNull(cc)) {
            return;
        }

        for (Closeable closeable : cc) {
            close(closeable);
        }

    }

    public static void close(Closeable c) {

        if (Objects.isNull(c)) {
            return;
        }

        try {
            c.close();
        } catch (IOException e) {
            log.error("IO流关闭失败：", e);
        }

    }

}
