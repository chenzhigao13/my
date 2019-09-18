package com.liandi.shiro;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * shiro加密工具类
 *
 * @author czg
 * @date 2019/9/18 16:26
 */
public final class HashUtil {

    public static SimpleHash getSimpleHash(String algorithmName, String source, String salt, int hashIterations) {

        if (algorithmName == null || source == null) {
            return null;
        }

        return new SimpleHash(algorithmName, source, ByteSource.Util.bytes(salt), hashIterations);
    }

}
