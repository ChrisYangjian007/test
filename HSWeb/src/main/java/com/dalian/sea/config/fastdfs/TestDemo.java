package com.dalian.sea.config.fastdfs;

import com.alibaba.fastjson.JSONArray;
import lombok.extern.slf4j.Slf4j;
import org.csource.common.MyException;
import org.csource.fastdfs.*;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 *
 * @author 杨文波
 * @date 2018/2/26
 */
@Slf4j
public class TestDemo {
    private static final String FASTDFS_CONFIG = "fastdfs-client.properties";

    /**
     * 客户端
     */
    private static StorageClient1 storageClient1 = null;

    // 初始化客户端,加载类时候执行片段
    static {
        try {
            ClientGlobal.initByProperties(FASTDFS_CONFIG);
            //
            TrackerClient trackerClient = new TrackerClient(ClientGlobal.g_tracker_group);
            //
            TrackerServer trackerServer = trackerClient.getConnection();
            //
            StorageServer storageServer = trackerClient.getStoreStorage(trackerServer);
            //
            storageClient1 = new StorageClient1(trackerServer, storageServer);
            log.info("FastDFS Client Init Success!");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("FastDFS Client Init Fail!");
        }
    }

    /***
     * 文件上传
     *
     * @param fastDSFile
     * @return
     * @throws IOException
     * @throws MyException
     */
    public static JSONArray upload(FastDSFile fastDSFile) throws IOException, MyException {
        String[] uploadResult = storageClient1.upload_file(fastDSFile.getContent(), fastDSFile.getExt(), null);
        // String arr = JSONArray.toJSONString(uploadResult);
        JSONArray arr = (JSONArray) JSONArray.toJSON(uploadResult);
        return arr;
    }

    /**
     * 文件下载
     *
     * @param groupName
     * @param remoteFileName
     * @return
     * @throws IOException
     * @throws MyException
     */
    public static byte[] download(String groupName, String remoteFileName) throws IOException, MyException {
        return storageClient1.download_file(groupName, remoteFileName);
    }

    /**
     * 文件删除
     *
     * @param groupName
     * @param remoteFileName
     * @throws Exception
     * @return 返回0成功;非0失败.
     */
    public static int delete(String groupName, String remoteFileName) throws Exception {
        return storageClient1.delete_file(groupName, remoteFileName);
    }

}
