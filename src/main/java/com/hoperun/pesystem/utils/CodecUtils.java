package com.hoperun.pesystem.utils;

import org.apache.commons.codec.digest.DigestUtils;


public class CodecUtils {
    public static String md5Hex(String data) {
        return DigestUtils.md5Hex(data.hashCode() + "" + DigestUtils.md5Hex(data));
    }

    public static String shaHex(String data) {

        return DigestUtils.sha512Hex(data.hashCode() + "" + DigestUtils.md5Hex(data));
    }
}
