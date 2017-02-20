package money.com.gettingmoney.util;

import android.text.TextUtils;
import android.text.format.DateFormat;

import java.io.File;
import java.math.BigDecimal;

/**
 * Created by Administrator on 2017/2/18.
 */
public class FileUtil {
    public static long getFolderSize(java.io.File file){

        long size = 0;
        try {
            java.io.File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++)
            {
                if (fileList[i].isDirectory())
                {
                    size = size + getFolderSize(fileList[i]);

                }else{
                    size = size + fileList[i].length();

                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //return size/1048576;
        return size;
    }

    /**
     * 删除指定目录下文件及目录
     * @param deleteThisPath
     * @param
     * @return
     */
    public static void deleteFolderFile(String filePath, boolean deleteThisPath) {
        if (!TextUtils.isEmpty(filePath)) {
            try {
                File file = new File(filePath);
                if (file.isDirectory()) {// 处理目录
                    File files[] = file.listFiles();
                    for (int i = 0; i < files.length; i++) {
                        deleteFolderFile(files[i].getAbsolutePath(), true);
                    }
                }
                if (deleteThisPath) {
                    if (!file.isDirectory()) {// 如果是文件，删除
                        file.delete();
                    } else {// 目录
                        if (file.listFiles().length == 0) {// 目录下没有文件或者目录，删除
                            file.delete();
                        }
                    }
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    /**
     * 格式化单位
     * @param size
     * @return
     */
    public static String getFormatSize(double size) {
        double kiloByte = size/1024;
        if(kiloByte < 1) {
            return size + "Byte";
        }

        double megaByte = kiloByte/1024;
        if(megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB";
        }

        double gigaByte = megaByte/1024;
        if(gigaByte < 1) {
            BigDecimal result2  = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";
        }

        double teraBytes = gigaByte/1024;
        if(teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";
    }


    public static String gettime(long creatTime){
        String creattime = "";

        long dateTaken = System.currentTimeMillis();
        long temp = dateTaken-creatTime;
        int releas_time = 0;
        if(temp>=86400000&&temp<864000000){
            releas_time = (int)(temp/86400000);
            creattime = releas_time+"天前";
        }else if(temp<86400000&&temp>=3600000){
            releas_time = (int)(temp/3600000);
            creattime = releas_time+"小时前";
        }else if(temp<3600000&&temp>60000){
            releas_time = (int)(temp/60000);
            creattime = releas_time+"分钟前";

        }else if(temp<60000){
            creattime = "刚刚";
        }else if(temp>=864000000){
            creattime = DateFormat.format("yyyy年MM月dd日", creatTime).toString();
        }
        return creattime;
    }
}
