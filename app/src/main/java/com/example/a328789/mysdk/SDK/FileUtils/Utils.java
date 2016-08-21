package com.example.a328789.mysdk.SDK.FileUtils;

import android.text.TextUtils;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yuyongxiang-134019-yuyongxiang@deppon.com
 * @ClassName: FileUtils
 * @Description: 所有的下载文件的路径都在存储文件的目录都在这里管理
 * @date 2014年4月14日 上午10:06:32
 */
public final class Utils {
    private static final String TAG = Utils.class.getSimpleName();
    public static String INSTALL = "install";
    public static String DELETE = "delete";
    public static String SPLIT = "\\|";

    /**
     * @param @param Path
     * @param @param Extension
     * @param @param IsIterative 设定文件
     * @return void 返回类型
     * @throws
     * @Title: GetFiles
     * @Description: TODO(这里用一句话描述这个方法的作用)
     * @author yuyongxiang-134019-yuyongxiang@deppon.com
     * @date 2014年4月15日 下午3:14:38
     */
    public static Map<String, Object> getFiles(String path) {
        // 搜索目录，扩展名，是否进入子文件夹
        Map<String, Object> map = new HashMap<String, Object>(2);

        List<String> installList = new ArrayList<String>();
        List<String> deleteList = new ArrayList<String>();

        //Log.v("FileUtils", Path);

        File[] files = new File(path).listFiles();

        for (File f : files) {
            // File f = files[i];
            if (f.isFile()) {
                String[] strFiles = f.getPath().split("/");

                if (strFiles[strFiles.length - 1].startsWith("_")) {
                    deleteList.add(f.getPath());
                } else {
                    installList.add(f.getPath());
                }
            } else if (f.isDirectory() && f.getPath().indexOf("/.") == -1) {
                // 忽略点文件（隐藏文件/文件夹）
                getFiles(f.getPath());
            }
        }

        map.put(Utils.DELETE, deleteList);
        map.put(Utils.INSTALL, installList);

        return map;
    }

    /**
     * @param @param  path
     * @param @return 设定文件
     * @return boolean 返回类型
     * @throws
     * @Title: delete
     * @Description: 删除当前文件或者当前文件夹下面的所有的文件
     * @author yuyongxiang-134019-yuyongxiang@deppon.com
     * @date 2014年4月22日 上午9:55:27
     */
    public static boolean delete(String path) {
        File file = new File(path);
        if (file.isDirectory()) {
            // delete(file.)
            File[] files = file.listFiles();

            for (File f : files) {
                delete(f.getPath());
            }

        } else if (file.exists() && file.isFile()) {
            Log.v("FileUtils", "删除文件 -> " + file.getPath());
            file.delete();
        } else if (!file.exists()) {
            Log.v("FileUtils", "该目录下无任何文件(或 该文件不存在) -> " + file.getPath());
        }

        return true;
    }

    /**
     * @param @param  file
     * @param @return 设定文件
     * @return boolean    返回类型
     * @throws
     * @Title: mkdirs
     * @Description: mkdirs
     * @author wanggang077@deppon.com/200939
     */
    public static boolean mkdirs(File file) {

        if (!file.exists()) {
            file.mkdirs();
        }

        // file=null;

        return true;
    }


    /**
     * 复制单个文件
     *
     * @param oldPath String 原文件路径 如：c:/fqf.txt
     * @param newPath String 复制后路径 如：f:/fqf.txt
     * @return boolean
     */
    public static boolean copyFile(String oldPath, String newPath) {
        File oldfile = new File(oldPath);
        if (oldfile.exists()) { // 文件存在时
            try {
                return copyFile(new FileInputStream(oldPath), newPath);
            } catch (FileNotFoundException e) {
                Log.e(TAG, "文件拷贝失败!  源文件 => " + oldPath + " 拷贝的目录 => " + newPath + " =>>>>>>> " + e.getMessage());
                e.printStackTrace();
                return false;
            }
        } else {
            Log.e("FileUtils", "源文件不存在无法copy");
            return false;
        }
    }


    /**
     * 把一个目录拷贝到另一个目录或者把一个文件拷贝到文件 或者把文件拷贝到目录里面
     *
     * @param oldPath : 目录
     * @param newPath : 新的目录
     * @return
     */
    public static boolean copyDirectory(String oldPath, String newPath) {
        //
        if (TextUtils.isEmpty(oldPath) || TextUtils.isEmpty(newPath)) {
            Log.i(TAG, "拷贝的两个目录都不能为空!");
            return false;
        }
        // 两个要么都是file 或者都是目录
        File oldFile = new File(oldPath);
        if (!oldFile.exists()) {
            Log.i(TAG, "被拷贝的文件或者目录不存在!");
            return false;
        }

        if (oldFile.isDirectory()) {
            File[] files = oldFile.listFiles();
            for (File file : files) {

                String newFile = file.getPath().replaceAll(oldPath, "");

                if (newFile.startsWith("/")) {
                    newFile = newFile.replaceFirst("/", "");
                }
                if (newPath.endsWith("/")) {
                    newPath = newPath + "/";
                }
                if (newFile.endsWith("/")) {
                    newFile = newFile + "/";
                }

                if (file.isDirectory() && file.getPath().indexOf("/.") == -1) {

                    copyDirectory(file.getPath(), newPath + newFile);
                } else if (file.isFile()) {

                    copyFile(file.getPath(), newPath + newFile);
                }
            }
        } else if (oldFile.isFile()) {
            if (newPath.endsWith(oldFile.getName())) {
                copyFile(oldPath, newPath);
            } else {
                if (!newPath.endsWith("/")) {
                    newPath = newPath + "/";
                }
                copyFile(oldPath, newPath + oldFile.getName());
            }


        }

        return true;

    }

    /**
     * 复制单个文件
     *
     * @param inStream String 原文件路径 如：c:/fqf.txt
     * @param newPath  String 复制后路径 如：f:/fqf.txt
     * @return boolean
     */
    public static boolean copyFile(InputStream inStream, String newPath) {
        FileOutputStream fs = null;
        try {
            int bytesum = 0;
            int byteread = 0;

            File file = new File(newPath);
            file.deleteOnExit();
            mkdirs(file.getParentFile());
            fs = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            while ((byteread = inStream.read(buffer)) != -1) {
                bytesum += byteread; // 字节数 文件大小
                fs.write(buffer, 0, byteread);
            }
            inStream.close();
            fs.close();
            Log.v("FileUtils", "文件copy成功 总共拷贝" + bytesum
                    / 1024 + " KB");
        } catch (Exception e) {
            e.printStackTrace();
            return false;

        } finally {
            try {

                if (fs != null) {
                    fs.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;

    }

    /**
     * @param @param  fpath
     * @param @return 设定文件
     * @return boolean 返回类型
     * @throws
     * @Title: fileExist
     * @Description: 判断一个文件是否存在
     * @author 092039/dpyuanjb@deppon.com
     */
    public static boolean fileExist(String fpath) {
        File file = null;
        try {
            file = new File(fpath);
            if (!file.exists()) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            file = null;
        }
        return true;
    }


}
