package com.yibin.nanxi.scandir2json.controller;

import com.yibin.nanxi.scandir2json.service.DealFilesService;
import com.yibin.nanxi.scandir2json.util.CommonUtils;
import com.yibin.nanxi.scandir2json.vo.ResultVO;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by LongFF on 2018/9/9
 */

@RestController
public class DealFilesController {

    @Autowired
    private DealFilesService dealFilesService;

    @Autowired
    private HttpServletRequest request;
    @RequestMapping("/zip")
    public ResultVO zipFiles(@RequestParam String orginDir , @RequestParam String destDir,@RequestParam String zipName){
        Boolean aBoolean = dealFilesService.ZipFiles(orginDir,destDir,zipName);

        return aBoolean ? CommonUtils.success(true) : CommonUtils.error("false");
    }

    @RequestMapping(value = "/download" ,method = RequestMethod.GET, produces = "application/json")
    public ResultVO download() throws IOException {
        OutputStream os = null;
        FileInputStream fis = null;
        BufferedInputStream bi = null;
        try {
            HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
            // path是指欲下载的文件的路径。
            String path = "F:/test/test.zip";
            File file = new File(path);
            String fileName = file.getName();
            // 设置response的Header
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.setContentLength((int) file.length());
            response.setContentType("application/zip");
            response.setCharacterEncoding("UTF-8");

            fis = new FileInputStream(file);
            bi = new BufferedInputStream(fis);
            byte[] b = new byte[1024];
            long i = 0;
            os = response.getOutputStream();
            while (i< file.length()){
                int j = bi.read(b,0,1024);
                i += j;
                os.write(b,0,1024);
            }
            os.flush();


        } catch (IOException ex) {
            ex.printStackTrace();
        }finally {
            if(fis != null){
                fis.close();
            }

            if(bi != null){
                bi.close();
            }

            if(os != null){
                os.close();
            }
        }
        return CommonUtils.success("ok");
    }


    @RequestMapping("/download2")
    public HttpServletResponse download(@RequestParam String path, HttpServletResponse response) {
        try {
            // path是指欲下载的文件的路径。
            File file = new File(path);
            // 取得文件名。
            String filename = file.getName();
            // 取得文件的后缀名。
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return response;
    }

    @RequestMapping("/a.zip")
    public byte[] down3() throws IOException {
        String path = "F:/test/a.jpg";
        File file = new File(path);
        String fileName = file.getName();
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ZipOutputStream zipOut= new ZipOutputStream(bo);
        ZipEntry zipEntry = new ZipEntry(file.getName());
        zipOut.putNextEntry(zipEntry);
        zipOut.write(IOUtils.toByteArray(new FileInputStream(file)));
        zipOut.closeEntry();
        zipOut.close();
        return bo.toByteArray();
    }

    }
