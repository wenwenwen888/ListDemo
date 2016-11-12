package com.wyt.list.util;

import android.text.format.DateFormat;
import android.util.Base64;
import android.util.Log;

import com.file.zip.ZipFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;

/**
 * Created by Won on 2016/9/23.
 */
public class Util {


    private static final int BUFF_SIZE = 1024 * 1024; // 1M Byte

    /**
     * 获取MD5 32位签名秘钥
     */
    public static String getMD5Sign(String sigh) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(sigh.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e);
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10) hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString();
    }

    /**
     * 获取系统时间戳
     */
    public static String getTime() {
        return Long.toString(System.currentTimeMillis());
    }

    /**
     * 获取base64编码
     */
    public static String getBase64(String string) {
        return Base64.encodeToString(string.getBytes(), Base64.DEFAULT);
    }

    /**
     * 获取2016-09-30 10:24:10格式的时间
     */
    public static String getDate() {
        return DateFormat.format("yyyy-MM-dd HH:mm:ss", System.currentTimeMillis()).toString();
    }

    /**
     * 递归删除文件和文件夹
     * @param file    要删除的根目录
     */
    public static void RecursionDeleteFile(File file){
        if(file.isFile()){
            file.delete();
            return;
        }
        if(file.isDirectory()){
            File[] childFile = file.listFiles();
            if(childFile == null || childFile.length == 0){
                file.delete();
                return;
            }
            for(File f : childFile){
                RecursionDeleteFile(f);
            }
            file.delete();
        }
    }

    /**
     * 解压缩一个文件
     *
     * @param zipFile    压缩文件
     * @param folderPath 解压缩的目标目录
     * @return 返回解压根目录
     * @throws IOException 当解压缩过程出错时抛出
     */
    public static String upZipFile(File zipFile, String folderPath) throws IOException {
        String FolderPath = null;
        File desDir = new File(folderPath);
        if (!desDir.exists()) {
            desDir.mkdirs();
        }
        ZipFile zf = new ZipFile(zipFile, "GBK");
        for (Enumeration<?> entries = zf.getEntries(); entries.hasMoreElements(); ) {
            ZipEntry entry = ((ZipEntry) entries.nextElement());
            if (entry.isDirectory()) {
                continue;
            }
            InputStream in = zf.getInputStream((com.file.zip.ZipEntry) entry);
            String str = folderPath + File.separator + entry.getName();
            FolderPath = folderPath + File.separator + entry.getName().substring(0, entry.getName().indexOf("/"));//返回的根目录
            Log.e("upZipFileToTFcard", "FolderPath:" + entry.getName());
            str = new String(str.getBytes(), "utf-8");
            File desFile = new File(str);
            if (!desFile.exists()) {
                File fileParentDir = desFile.getParentFile();
                if (!fileParentDir.exists()) {
                    Log.e("upZipFileToTFcard", "fileParentDir:" + fileParentDir.getPath());
                    fileParentDir.mkdirs();
                }
                Log.e("upZipFileToTFcard" , "desFile:"+desFile.getPath());
                desFile.createNewFile();
            }
            OutputStream out = new FileOutputStream(desFile);
            byte buffer[] = new byte[BUFF_SIZE];
            int realLength;
            while ((realLength = in.read(buffer)) > 0) {
                out.write(buffer, 0, realLength);
            }
            in.close();
            out.close();
        }
        return FolderPath;
    }



}
