package com.lfc.mysql.utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;

/**
 * Created by zst on 2017/4/11.
 */

public class FileUtil {
    private static String SDPATH = "";

    /**
     * 获取到sd卡的根目录，并以String形式返回
     *
     * @return
     */
    public static String getSDCardPath() {
        SDPATH = Environment.getExternalStorageDirectory() + "/";
        return SDPATH;
    }

    /**
     * 创建文件或文件夹
     *
     * @param fileName 文件名或问文件夹名
     */
    public static File createFile(String fileName) {

        System.out.println("File.separator:" + File.separator);
        File testFile = new File(Const.Data_FileDirPath + File.separator + fileName);
        try {
            File fileParent = testFile.getParentFile();//返回的是File类型,可以调用exsit()等方法
            String fileParentPath = testFile.getParent();//返回的是String类型
            System.out.println("fileParent:" + fileParent);
            System.out.println("fileParentPath:" + fileParentPath);
            if (!fileParent.exists()) {
                fileParent.mkdirs();// 能创建多级目录
            }
            if (!testFile.exists())

                testFile.createNewFile();//有路径才能创建文件
        } catch (IOException e) {
            e.printStackTrace();
        }


        return testFile;
    }


    /**
     * 创建文件或文件夹
     *
     * @param fileName 文件名或问文件夹名
     * @param type     1  单文件名  没有完整路径  2 完整路径
     */
    public static void createFile(String fileName, int type) {

        File file = new File(type == 1 ? getSDCardPath() + fileName : fileName);
        if (fileName.indexOf(".") != -1) {
            // 说明包含，即使创建文件, 返回值为-1就说明不包含.,即使文件
            try {
                file.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                LgU.e("createFile :" + e.getMessage().trim());
            }
            LgU.d("创建了文件");
        } else {
            // 创建文件夹
            file.mkdir();
            LgU.d("创建了文件夹");
        }

    }

    //file文件读取成byte[]
    public static byte[] readFile(File file) {
        RandomAccessFile rf = null;
        byte[] data = null;
        try {
            rf = new RandomAccessFile(file, "r");
            data = new byte[(int) rf.length()];
            rf.readFully(data);
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            closeQuietly(rf);
        }
        return data;
    }

    //关闭读取file
    public static void closeQuietly(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * 删除单个文件
     *
     * @param filePath 被删除文件的文件名
     * @return 文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.isFile() && file.exists()) {
            return file.delete();
        }
        return false;
    }


    //删除文件夹和文件夹里面的文件
    public static void deleteDir(final String pPath) {
        File dir = new File(pPath);
        deleteDirWihtFile(dir);
    }

    public static void deleteDirWihtFile(File dir) {
        if (dir == null || !dir.exists() || !dir.isDirectory())
            return;
        for (File file : dir.listFiles()) {
            if (file.isFile())
                file.delete(); // 删除所有文件
//            else if (file.isDirectory())
//                deleteDirWihtFile(file); // 递规的方式删除文件夹
        }
//        dir.delete();// 删除目录本身
    }

    public static File getSaveFile(Context context) {
        return new File(context.getFilesDir(), "pic.jpg");
    }

    /**
     * 复制单个文件
     *
     * @param oldPath String 原文件路径 如：c:/fqf.txt
     * @param newPath String 复制后路径 如：f:/fqf.txt
     * @return boolean
     */
    public static void copyFile(String oldPath, String newPath) {
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            if (oldfile.exists()) {
                //文件存在时
                if (!new File(newPath).exists()) {
                    int index = newPath.lastIndexOf(File.separator);
                    String strfiledir = newPath.substring(0, index);
                    String strfiles = newPath.substring(index, newPath.length());
                    LgU.d("all:" + newPath + " \n文件夹" + strfiledir + "\n 文件: " + strfiles);
                    FileUtil.makeFilePath(strfiledir, strfiles);
                }
                InputStream inStream = new FileInputStream(oldPath); //读入原文件
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                int length;
                while ((byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread; //字节数 文件大小
                    System.out.println(bytesum);
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            }
        } catch (Exception e) {
            System.out.println("复制单个文件操作出错");
            e.printStackTrace();

        }

    }

    /**
     * 复制整个文件夹内容
     *
     * @param oldPath String 原文件路径 如：c:/fqf
     * @param newPath String 复制后路径 如：f:/fqf/ff
     * @return boolean
     */
    public static void copyFolder(String oldPath, String newPath) {

        try {
            (new File(newPath)).mkdirs(); //如果文件夹不存在 则建立新文件夹
            File a = new File(oldPath);
            String[] file = a.list();
            File temp = null;
            for (int i = 0; i < file.length; i++) {
                if (oldPath.endsWith(File.separator)) {
                    temp = new File(oldPath + file[i]);
                } else {
                    temp = new File(oldPath + File.separator + file[i]);
                }

                if (temp.isFile()) {
                    FileInputStream input = new FileInputStream(temp);
                    FileOutputStream output = new FileOutputStream(newPath + "/" +
                            (temp.getName()).toString());
                    byte[] b = new byte[1024 * 5];
                    int len;
                    while ((len = input.read(b)) != -1) {
                        output.write(b, 0, len);
                    }
                    output.flush();
                    output.close();
                    input.close();
                }
                if (temp.isDirectory()) {//如果是子文件夹
                    copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
                }
            }
        } catch (Exception e) {
            System.out.println("复制整个文件夹内容操作出错");
            e.printStackTrace();

        }

    }

    // 判断文件是否存在
    public static boolean judeFileExists(File file) {

        if (file.exists()) {
            System.out.println("file exists");
            return true;
        } else {
            System.out.println("file not exists, create it ...");
            try {
                file.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return false;
    }

    public static void initFile() {
        try {
            File dir = new File(Const.Data_FilePath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File photoFile = new File(Const.Data_FilePath, Const.Data_PhotoIMG);
            File photoSmallFile = new File(Const.Data_FilePath, "small.jpg");
            if (!photoFile.exists()) {
                photoFile.createNewFile();
            }
            if (!photoSmallFile.exists()) {
                photoSmallFile.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
            LgU.e("initFile :" + e.getMessage().trim());

        }

    }

    // 生成文件
    public static File makeFilePath(String filePath, String fileName) {
        File file = null;
        makeRootDirectory(filePath);
        try {
            file = new File(filePath + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    // 生成文件夹
    public static void makeRootDirectory(String filePath) {
        File file = null;
        try {
            file = new File(filePath);
            if (!file.exists()) {
                file.mkdir();
            }
        } catch (Exception e) {
            Log.i("error:", e + "");
        }
    }


    /**
     * 根据地址获得数据的字节流并转换成大小
     *
     * @param strUrl 网络连接地址
     * @return
     */
    public static String getFileSizeByUrl(String strUrl) {
        InputStream inStream = null;
        ByteArrayOutputStream outStream = null;
        String size = "";
        try {
            URL url = new URL(strUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5 * 1000);
            inStream = conn.getInputStream();

            outStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, len);
            }
            byte[] bt = outStream.toByteArray();

            if (null != bt && bt.length > 0) {
                DecimalFormat df = new DecimalFormat("#.00");
                if (bt.length < 1024) {
                    size = df.format((double) bt.length) + "BT";
                } else if (bt.length < 1048576) {
                    size = df.format((double) bt.length / 1024) + "KB";
                } else if (bt.length < 1073741824) {
                    size = df.format((double) bt.length / 1048576) + "MB";
                } else {
                    size = df.format((double) bt.length / 1073741824) + "GB";
                }
                System.out.println("文件大小=：" + size);
            } else {
                System.out.println("没有从该连接获得内容");
            }
            inStream.close();
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inStream != null) {
                    inStream.close();
                }
                if (outStream != null) {
                    outStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return size;
    }

    /**
     * 返回byte的数据大小对应的文本
     *
     * @param size
     * @return
     */
    public static String getDataSize(long size) {
        DecimalFormat formater = new DecimalFormat("####.00");
        if (size < 1024) {
            return size + "B";
        } else if (size < 1024 * 1024) {
            float kbsize = size / 1024f;
            return formater.format(kbsize) + "K";
        } else if (size < 1024 * 1024 * 1024) {
            float mbsize = size / 1024f / 1024f;
            return formater.format(mbsize) + "M";
        } else if (size < 1024 * 1024 * 1024 * 1024) {
            float gbsize = size / 1024f / 1024f / 1024f;
            return formater.format(gbsize) + "G";
        } else {
            return "";
        }
    }
}
