package com.jemmy.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import org.junit.Test;

/**
 * @author zhujiang.cheng
 * @since 2023/2/17
 */
public class Base64Test {

    public static void main(String[] args) {
        String str = "LS0tLS1CRUdJTiBSU0EgUFJJVkFURSBLRVktLS0tLQpNSUlFb2dJQkFBS0NBUUVBdjljS1luNmcwL0wvK0FLL2Z2cEpiN3lFWWQ1MWNweUtiMWQ3eFpmNWhzV0RnbHZnCjI0LzVVYW5uUmNWcGJ1Uk5QSVphMG9ETkdCTHNqRjBDOXVsYmVnMVA2VERTOENiOUdNcWg0RlB1TzdQWnZ4eUoKL0RVSjlMTXZhU3Q4SGQ0azloNFdzMytIT1dNTXFGckxBRHptNGw1YXFUSVErQmhGRXFLaThvelZ2VFB3dklVWgpDdnA5NC8raGUrem1ZRUtscERXUDh4VWZSS0hCMDBFQndESmwxZjVheXA1c1pTVXhQMTE2dGZQZjdDZTdhUk5sCkFOTW0wNXRqdjgva3E4ajJOK2NhL1pjTTYyL0VSVDcrUE5TZWF0UHpmUWpiRjVCUDE3VzNDTDlQY2ZUN0JlQUMKbllLVDZidDFoRFYrbnRSalJBaENUTG5vTXVVa2tRQkVwUURLR3dJREFRQUJBb0lCQUVpQ2ZDOWNVMmVwS216UQpoUmIyZlRsWmxPUDFGSWxjZDhxNzAxR05Mc2hzOFZjZGFURTBDdlY5OWFkZWhZcGZpbjM4L21rZ08rVUVpaHBICnNhNG41K2FMRDVCQlh3bGNneitTbjNyTjNuYjhkS29TbkdrU2x3L0Job01HNTZQV2Fqd0loV1lWSnNSZkZ0S3YKZHBsQmE3ZFdHSWhwSHhiVy9ZeWNvbUJ4TVB5UTlLaGhZd0J6dXVrMTdxOXVaT1NWU2xReTR6alA1SGZFQnJsWgptbUw4K0RpaGxYbmw5Q3N3bHI0eVRmUnJ4L3AvV2RkYVhETDBqd1AyTm43MUVRM1N6OFcvSkJIREtjaTZLeG80ClltQzl3eEE4QUdEUGZmR1kxaE1tZkE4OFVZbUFORWwzNC9qVDJmems1cjN5VU1EMi96UHdBQXFrdEdGTU41eW8KbXlwK1hya0NnWUVBNVB3ZlFlQmFWY0gxU2xqb0FMSmtyOFZSamxIZkYwT2g1VG9RZmtXNWdERHFvY1hjVTE1cApoSW1BbnUrNTJ1YXlpL3NJYUJzY2JsTkVxelFvLzgrcWFZblZ5L2FFOGVKYXZ6OEFTejhrTU5lMDd6b1Jxei90CnVWVFBYQ2l3aEhkY1lrRzV3S3JIRnhFcThSZlpRK1FlT1l2Wmo5c1NKTEFuM1ZSSm9RNlUzOWNDZ1lFQTFua08Kb2ZSNWlFbU5Ld0VIRUduS2F5SVFVU3NkbTRqbjBYYk5mVks3VmlHbk1vSUgwemJTbzVPbmpQeFUwdGY0czJMTAo5aDFSRndSTXU1ejNqbEdJYVdSQ0lhNGJobkhkbzA1ZGtxNzZtaGl5TDdDV010YWxMM0dmOC94MndHc3g1MExwCm9VSGpMTGxTNzgvM0ZrNmRFWlFQaHlVNUJ3dzNHcStLSm14bEwxMENnWUF0cmlaR0xBMlpZaEllL05GN29ocjMKL2JNaFl5L1FJa3Zmd00rZGdTZGFiV2ZCVWdsOEVDK1lveDJubDBkS1pnOWtZWFh4M3Nhek02VlZTaGlMdjgvTgpNaFFXbGwwN0s5N1NuQ3pnMWYzUVNKZnR5WFhRNWZYMHJQaVk5LzBPMEFqOENRU1hiUG8rbmN3bFFJNjFQb0xSCkZCY1kzYWttN0lWblgvTTRVQUpmRFFLQmdFenFKeXcvcjZJSHRwKzRnNThJUVhhQVJkZkdIWjBjMFA0V25BVVkKdVdSZk4wWHV5ZjJlU0pZdktzUnQ3Y0diZ0c1Y3ZRYXJadHhaUjJkMUdIYXFtTUFyRzJoV3R6cFU5WVpPWVZKcwpmQ1R0UEdITDgyR1NCclZvQzFRRnMxUzRYTEl5RmZVbDJ1RzhLSmttUE5kOEkrb2M3aUNxWWJROFZqcTB2MWVlClFmOWxBb0dBQkdINUNNTFZYUmxncWgxa3N5d2NtL20rQjdDeU56NnNCT1BsTE1EREl2RlQ0dUNiYTZ2a2o0d3cKVitjbUdBY3hhNkoxSVhwUE9tQzNYN0hhcGRCc0xuK2ptWG1FSDVaMVFxRElVMGwwcCtmTmhzTCt3YmNoR25yYwpKSzJrOXNaUDFoL0J0dERTMnFJUmFZN2N2aWJIZkpnUU93LytLaUkwS1VLT3B5UGxmcDQ9Ci0tLS0tRU5EIFJTQSBQUklWQVRFIEtFWS0tLS0tCg==";
        String plain = new String(Base64.getDecoder().decode(str), StandardCharsets.UTF_8);
        System.out.println("plain = " + plain);
    }

    @Test
    public void test() {
        String str = "2HkwgHbWs2Vi6BSCdOysmwCt9tHaVZCfNCBd17gYaKQFDfT52hrjMrvhDJXLnqQ1XWD7mmfFaBIgtzU6JFVLMKqshF4kQqJKcKc81fPx4qDU9UGI6HTQgCKE8pQK7OLktbMvS8ESBeOBTuJGeXOLEvTv1OoKHiPXa/kYLcAFPGUYej+sQbZEnwA==";
        String plain = new String(Base64.getDecoder().decode(str), StandardCharsets.UTF_8);
        System.out.println("plain = " + plain);
    }
}
