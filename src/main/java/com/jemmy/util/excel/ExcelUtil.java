package com.jemmy.util.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.fastjson.JSON;
import java.math.BigDecimal;
import lombok.extern.slf4j.Slf4j;

/**
 * https://github.com/alibaba/easyexcel/blob/master/easyexcel-test/src/test/java/com/alibaba/easyexcel/test/demo/read/ReadTest.java
 *
 * @author zhujiang.cheng
 * @since 2023/11/2
 */
@Slf4j
public class ExcelUtil {

    public static void main(String[] args) {
        read();
    }

    public static void read() {
        // 写法1：JDK8+ ,不用额外写一个DemoDataListener
        // since: 3.0.0-beta1
        String fileName = "/Users/xxx/Downloads/demo.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        // 这里默认每次会读取100条数据 然后返回过来 直接调用使用数据就行
        // 具体需要返回多少行可以在`PageReadListener`的构造函数设置

        BigDecimal BD_1000 = new BigDecimal("1000");

        EasyExcel.read(fileName, DemoData.class, new PageReadListener<DemoData>(dataList -> {
            BigDecimal total = BigDecimal.ZERO;
            for (DemoData demoData : dataList) {
                String sizeStr = demoData.getStorageSize();
                if (sizeStr.endsWith("gb")) {
                    BigDecimal item = new BigDecimal(sizeStr.substring(0, sizeStr.length() - 2));
                    total = total.add(item);
                } else {
                    BigDecimal item = new BigDecimal(sizeStr.substring(0, sizeStr.length() - 2)).divide(BD_1000, BigDecimal.ROUND_HALF_DOWN);
                    total = total.add(item);
                }
                log.info("读取到一条数据{}", JSON.toJSONString(demoData));
            }

            log.info("total: {}", total);
        }, 350)).sheet().doRead();

        // 写法2：
        // 匿名内部类 不用额外写一个DemoDataListener
//        fileName = TestFileUtil.getPath() + "demo" + File.separator + "demo.xlsx";
//        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
//        EasyExcel.read(fileName, DemoData.class, new ReadListener<DemoData>() {
//            /**
//             * 单次缓存的数据量
//             */
//            public static final int BATCH_COUNT = 100;
//            /**
//             *临时存储
//             */
//            private List<DemoData> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
//
//            @Override
//            public void invoke(DemoData data, AnalysisContext context) {
//                cachedDataList.add(data);
//                if (cachedDataList.size() >= BATCH_COUNT) {
//                    saveData();
//                    // 存储完成清理 list
//                    cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
//                }
//            }
//
//            @Override
//            public void doAfterAllAnalysed(AnalysisContext context) {
//                saveData();
//            }
//
//            /**
//             * 加上存储数据库
//             */
//            private void saveData() {
//                log.info("{}条数据，开始存储数据库！", cachedDataList.size());
//                log.info("存储数据库成功！");
//            }
//        }).sheet().doRead();

        // 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
        // 写法3：
//        fileName = TestFileUtil.getPath() + "demo" + File.separator + "demo.xlsx";
//        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
//        EasyExcel.read(fileName, DemoData.class, new DemoDataListener()).sheet().doRead();

        // 写法4
//        fileName = TestFileUtil.getPath() + "demo" + File.separator + "demo.xlsx";
//        // 一个文件一个reader
//        try (ExcelReader excelReader = EasyExcel.read(fileName, DemoData.class, new DemoDataListener()).build()) {
//            // 构建一个sheet 这里可以指定名字或者no
//            ReadSheet readSheet = EasyExcel.readSheet(0).build();
//            // 读取一个sheet
//            excelReader.read(readSheet);
//        }
    }
}
