package com.dalian.sea.FileUpload;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *
 * @author 杨文波
 * @date 2018/3/2
 */
@Component
@ConfigurationProperties(prefix = "aliyun.oss")
@Getter
@Setter
public class OssConfig {
    private  String endpoint;

    // accessKeyId和accessKeySecret是OSS的访问密钥，您可以在控制台上创建和查看，
    // 创建和查看访问密钥的链接地址是：https://ak-console.aliyun.com/#/。
    // 注意：accessKeyId和accessKeySecret前后都没有空格，从控制台复制时请检查并去除多余的空格。
    private String accessKeyId ;
    private String accessKeySecret;

    // Bucket用来管理所存储Object的存储空间，详细描述请参看“开发人员指南 > 基本概念 > OSS基本概念介绍”。
    // Bucket命名规范如下：只能包括小写字母，数字和短横线（-），必须以小写字母或者数字开头，长度必须在3-63字节之间。
    private String bucketName;

    // Object是OSS存储数据的基本单元，称为OSS的对象，也被称为OSS的文件。详细描述请参看“开发人员指南 > 基本概念 > OSS基本概念介绍”。
    // Object命名规范如下：使用UTF-8编码，长度必须在1-1023字节之间，不能以“/”或者“\”字符开头。
//    private String firstKey;


    // endpoint以杭州为例，其它region请按实际情况填写
    // private String endpoint = "您的endpoint";
    // accessKey
    // private String accessKeyId = "您的accessKeyId";
    // private String accessKeySecret = "您的accessKeySecret";
    //空间
    //private String bucketName = "bcis";
    //文件存储目录
    private String filedir;
    private String filedirV;

}
