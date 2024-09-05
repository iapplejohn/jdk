import java.io.IOException;
import java.net.InetAddress;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author zhujiang.cheng
 * @since 2021/5/26
 */
public class DecryptViaHttpTest {

    private static final OkHttpClient client = new OkHttpClient();

    private String accessToken = "xxx";

    private String endpoint = "https://cipher.xxx.com:443/decryptForJdbc";

    private String appName = "recon-core";

    public static void main(String[] args) {
        DecryptViaHttpTest inst = new DecryptViaHttpTest();
        inst.decrypt("dKjIBCDxm7qlOb9Wbka8Ug==");
    }

    public String decrypt(String cipherText) throws RuntimeException {
        try {
            URL endpointURI = new URL(endpoint);
            Request request = new Request.Builder()
                .url(new HttpUrl.Builder()
                    .scheme(endpointURI.toURI().getScheme())
                    .host(endpointURI.getHost())
                    .port(endpointURI.getPort())
                    .encodedPath(endpointURI.getPath())
                    .addQueryParameter("accessToken", accessToken)
                    .addEncodedQueryParameter("cipherText", URLEncoder.encode(cipherText, StandardCharsets.UTF_8.name()))
                    .addQueryParameter("host", InetAddress.getLocalHost().getHostAddress())
                    .addQueryParameter("app", appName)
                    .build())
                .build();
            System.out.println(request.url().toString());
            Response response = client.newCall(request).execute();
            String responseStr = response.body().string();
            System.out.println(responseStr);
            response.close();
            return responseStr;
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    static class Decrypted {
        private int code;
        private String message;
        private String data;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }
}
