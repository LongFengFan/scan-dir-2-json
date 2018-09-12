package com.yibin.nanxi.scandir2json.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.util.StringUtils;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

public class ZipUtil {
    /**
     * 根据给定密码压缩文件(s)到指定目录
     *
     * @param destFileName 压缩文件存放绝对路径 e.g.:D:/upload/zip/demo.zip
     * @param passwd 密码(可为空)
     * @param files 单个文件或文件数组
     * @return 最终的压缩文件存放的绝对路径,如果为null则说明压缩失败.
     */
    public static String compress(String destFileName, String passwd, List<File> files) {
        ZipParameters parameters = new ZipParameters();
        parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE); // 压缩方式
        parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL); // 压缩级别
        if (!StringUtils.isEmpty(passwd)) {
            parameters.setEncryptFiles(true);
            parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_STANDARD); // 加密方式
            parameters.setPassword(passwd.toCharArray());
        }
        try {
            ZipFile zipFile = new ZipFile(destFileName);
            for (File file : files) {
                zipFile.addFile(file, parameters);
            }
            return destFileName;
        } catch (ZipException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 根据给定密码压缩文件(s)到指定位置
     *
     * @param destFileName 压缩文件存放绝对路径 e.g.:D:/upload/zip/demo.zip
     * @param passwd 密码(可为空)
     * @param filePaths 单个文件路径或文件路径数组
     * @return 最终的压缩文件存放的绝对路径,如果为null则说明压缩失败.
     */
    public static String compress(String destFileName, String passwd, String... filePaths) {
        int size = filePaths.length;
        File[] files = new File[size];
        for (int i = 0; i < size; i++) {
            files[i] = new File(filePaths[i]);
        }
        List<File> filesList = Arrays.asList(files);
        return compress(destFileName, passwd, filesList);
    }

//连目标文件夹一起压缩
    public static Boolean compressFolderWithTargetFolder(String destFileName, String passwd, String folderName) {
        File folder = new File(folderName);
        if(!folder.exists()){
            return false;
        }
        File destFile = new File(destFileName);
        if (!destFile.getParentFile().exists()){
            return false;
        }
        ZipParameters parameters = new ZipParameters();
        parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE); // 压缩方式
        parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL); // 压缩级别
        if (!StringUtils.isEmpty(passwd)) {
            parameters.setEncryptFiles(true);
            parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_STANDARD); // 加密方式
            parameters.setPassword(passwd.toCharArray());
        }
        try {
            ZipFile zipFile = new ZipFile(destFileName);
            zipFile.addFolder(folder,parameters);
            return true;
        } catch (ZipException e) {
            e.printStackTrace();
            return false;
        }
    }

    //连目标文件夹一起压缩
    public static Boolean compressFolderWithoutTargetFolder(String destFileName, String passwd, String folderName) {
        File folder = new File(folderName);
        if(!folder.exists()){
            return false;
        }
        File destFile = new File(destFileName);
        if (!destFile.getParentFile().exists()){
            return false;
        }
        ZipParameters parameters = new ZipParameters();
        parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE); // 压缩方式
        parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL); // 压缩级别
        if (!StringUtils.isEmpty(passwd)) {
            parameters.setEncryptFiles(true);
            parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_STANDARD); // 加密方式
            parameters.setPassword(passwd.toCharArray());
        }
        try {
            ZipFile zipFile = new ZipFile(destFileName);
            File[] files = folder.listFiles();
            for (File file : files) {
                if(file.isFile()){
                    zipFile.addFile(file,parameters);
                }else if(file.isDirectory()){
                    zipFile.addFolder(file,parameters);
                }
            }
            return true;
        } catch (ZipException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据所给密码解压zip压缩包到指定目录
     * <p>
     * 如果指定目录不存在,可以自动创建,不合法的路径将导致异常被抛出
     *
     * @param zipFile zip压缩包绝对路径
     * @param dest 指定解压文件夹位置
     * @param passwd 密码(可为空)
     * @return 解压后的文件数组
     * @throws ZipException
     */
//    @SuppressWarnings("unchecked")
    public static File[] deCompress(File zipFile, String dest, String passwd) throws ZipException {
        if (!zipFile.exists()){
            throw new ZipException("zip File is not exist");
        }
        //1.判断指定目录是否存在
        File destDir = new File(dest);
        if (destDir.isDirectory() && !destDir.exists()) {
            throw new ZipException("dest folder is not exist");
        }
        if (destDir.isFile()) {
            throw new ZipException("dest is not a directory");
        }

        //2.初始化zip工具
        ZipFile zFile = new ZipFile(zipFile);
        zFile.setFileNameCharset("UTF-8");
        if (!zFile.isValidZipFile()) {
            throw new ZipException("压缩文件不合法,可能被损坏.");
        }
        //3.判断是否已加密
        if (zFile.isEncrypted()) {
            zFile.setPassword(passwd.toCharArray());
        }
        //4.解压所有文件
        zFile.extractAll(dest);
        List<FileHeader> headerList = zFile.getFileHeaders();
        List<File> extractedFileList = new ArrayList<>();
        for(FileHeader fileHeader : headerList) {
            if (!fileHeader.isDirectory()) {
                extractedFileList.add(new File(destDir,fileHeader.getFileName()));
            }
        }
        File [] extractedFiles = new File[extractedFileList.size()];
        extractedFileList.toArray(extractedFiles);
        return extractedFiles;
    }
    /**
     * 解压无密码的zip压缩包到指定目录
     * @param zipFile zip压缩包
     * @param dest 指定解压文件夹位置
     * @return 解压后的文件数组
     * @throws ZipException
     */
    public static File[] deCompress(File zipFile, String dest){
        try {
            return deCompress(zipFile, dest, null);
        } catch (ZipException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 根据所给密码解压zip压缩包到指定目录
     * @param zipFilePath zip压缩包绝对路径
     * @param dest 指定解压文件夹位置
     * @param passwd 压缩包密码
     * @return 解压后的所有文件数组
     */
    public static File[] deCompress(String zipFilePath, String dest, String passwd) throws ZipException {
            return deCompress(new File(zipFilePath), dest, passwd);
    }
    /**
     * 无密码解压压缩包到指定目录
     * @param zipFilePath zip压缩包绝对路径
     * @param dest 指定解压文件夹位置
     * @return 解压后的所有文件数组
     */
    public static File[] deCompress(String zipFilePath, String dest){
        try {
            return deCompress(new File(zipFilePath), dest, null);
        } catch (ZipException e) {
            e.printStackTrace();
        }
        return null;
    }

}