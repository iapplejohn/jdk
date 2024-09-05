package com.jemmy.http;

import com.jemmy.compress.GZipUtil;
import com.jemmy.compress.ZipUtil;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.zip.GZIPInputStream;
import okhttp3.Headers;
import okhttp3.Headers.Builder;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.compress.compressors.gzip.GzipUtils;

/**
 * @author zhujiang.cheng
 * @since 2023/2/20
 */
public class HttpTest2 {

    public static void main(String[] args) throws IOException {
        Headers.Builder headerBuilder = new Builder();
//        headerBuilder.add("Content-Type", "application/json;charset=utf8");
        Headers headers = headerBuilder.build();

        StringBuilder builder = new StringBuilder(128);
        builder.append("https://test2-hk-business-inner.xxx.com/v1/inner/b2b/payout/payer/bill/getBillPaymentList?clientId=102112101013220563&txId=BI01202302201347486376900");

        Request request = new Request.Builder()
            .url(builder.toString())
//            .addHeader("Content-Type", "application/json;charset=utf8")
            .build();

        long start = System.currentTimeMillis();
        Response response = OkHttpClientUtil.getWithResponse(request);
        System.out.println("cost " + (System.currentTimeMillis() - start) + " ms");
        byte[] bytes = response.body().bytes();

//        if (GZipUtil.isGzipped(bytes)) {
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            GZIPInputStream inputStream = new GZIPInputStream(bais);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            int count;
            byte[] buf = new byte[1024];
            while ((count = inputStream.read(buf)) != -1) {
                baos.write(buf, 0, count);
            }

            String result = baos.toString();
            System.out.println(result);
//        }

        final int GZIP_MAGIC = 0x8b1f;


//        String result = ZipUtil.decompress(bytes);
//        String result = GZipUtil.decompress(bytes);
//        byte[] plain = Base64.getDecoder().decode(bytes);
//        String result = new String(plain);


    }
}
