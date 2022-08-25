package com.shitouren.core.controller;

import com.shitouren.core.annotation.Access;
import com.shitouren.core.bean.eums.ImgEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 照片上传工具类
 *
 * @author admin
 */


@RestController
@Api(value = "图片上传", tags = "图片上传 ")
public class UploaderController {

    //private static String basicPath = "/app/douyin/file/img";             //项目部署后请修改
    private static String basicPath = ImgEnum.img.getPath();

    @PostMapping("/upload")
    @Access(value = false)
    @ApiOperation("图片上传")
    public String uploadPhoto(@RequestParam("uploadFile") MultipartFile uploadFile) {
        //定义文件在上传路径中的文件夹名称
        File folder = new File(basicPath);
        //检测folder是否是文件夹，不是就创建
        if (!folder.isDirectory()) {
            folder.mkdirs();
        }
        //获取文件的原始名称
        String oldName = uploadFile.getOriginalFilename();
        oldName.substring(oldName.lastIndexOf("."));
        //生成新的文件名（下面根据自己需要决定是否使用）
        Long a = System.currentTimeMillis();
        String time = a.toString();
        String newName = a + oldName.substring(oldName.lastIndexOf("."));
        //文件保存操作
        try {
            uploadFile.transferTo(new File(folder, newName));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        部署应注意
//        return "/img/" + newName;
        return newName;
    }

    @PostMapping("/uploadForPhp")
    @Access(value = false)
    @ApiOperation("图片上传forPHP")
    public String uploadForPhp(@RequestParam("file") MultipartFile uploadFile) {
        File folder = new File(basicPath);
        //检测folder是否是文件夹，不是就创建
        if (!folder.isDirectory()) {
            folder.mkdirs();
        }
        //获取文件的原始名称
        String oldName = uploadFile.getOriginalFilename();
        oldName.substring(oldName.lastIndexOf("."));
        //生成新的文件名（下面根据自己需要决定是否使用）
        Long a = System.currentTimeMillis();
        String time = a.toString();
        String newName = a + oldName.substring(oldName.lastIndexOf("."));
        //文件保存操作
        try {
            uploadFile.transferTo(new File(folder, newName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String filePath = newName;
        System.out.println(filePath);
//        部署应注意
        return newName;
    }
/*
    @ResponseBody
    @PostMapping("/uploadVideo")
    public Result uploadVideo(@RequestParam("uploadFile") MultipartFile uploadFile, HttpServletRequest request) {
        //定义上传文件存放的路径
        String path = request.getSession().getServletContext().getRealPath("/uploadFile/");//此处为tomcat下的路径，服务重启路径会变化
        System.out.println(path);
        //定义文件在上传路径中的文件夹名称
        File folder = new File(path);
        //检测folder是否是文件夹，不是就创建
        if (!folder.isDirectory()) {
            folder.mkdirs();
        }
        //获取文件的原始名称
        String oldName = uploadFile.getOriginalFilename();
        oldName.substring(oldName.lastIndexOf("."));
        //生成新的文件名（下面根据自己需要决定是否使用）
        Long a = System.currentTimeMillis();
        String newName = a + oldName.substring(oldName.lastIndexOf("."));

        //文件保存操作
        try {
            uploadFile.transferTo(new File(folder, newName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String picName = Video2Img.getScreenshot(path+newName);
        //返回保存的url，根据url可以进行文件查看或者下载
        String videoUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/uploadFile/" + newName;
        String picUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/uploadFile/" +picName;
        Map<String,Object> res = new HashMap<>();
        res.put("videoUrl", videoUrl);
        res.put("picUrl", picUrl);

        return new Result(200, "上传成功", res);
    }*/
}



