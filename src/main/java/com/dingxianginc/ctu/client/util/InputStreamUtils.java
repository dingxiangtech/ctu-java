/*
 * Copyright: 2017 dingxiang-inc.com Inc. All rights reserved.
 */

package com.dingxianginc.ctu.client.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by dingxiang-inc on 2017/5/5.
 */
public class InputStreamUtils {
    private static final String UTF8_ENCODE = "UTF-8";

    public static ByteArrayOutputStream readToBytes(InputStream is) throws IOException {
        if (is == null) {
            return null;
        }

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];

            while (true) {
                int len = is.read(buf, 0, 1024);
                if (len == -1) {
                    break;
                }

                baos.write(buf, 0, len);
            }

            return baos;
        } finally {
            is.close();
        }
    }

    public static String readToString(InputStream is) throws IOException {
        return readToString(is, UTF8_ENCODE);
    }

    public static String readToString(InputStream is, String encode) throws IOException {
        ByteArrayOutputStream baos = readToBytes(is);
        if (baos == null) return null;

        return baos.toString(encode);
    }
}
