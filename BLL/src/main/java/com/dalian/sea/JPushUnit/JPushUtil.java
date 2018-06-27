package com.dalian.sea.JPushUnit;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.Notification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JPushUtil {
    private final static Logger logger = LoggerFactory.getLogger(JPushUtil.class);
    private static String masterSecret = "0dd847a79299822a9622d840";
    private static String appKey = "2af907a39a302acfe500fc07";
    private  static ClientConfig clientConfig = ClientConfig.getInstance();
    private static JPushClient jpushClient =new JPushClient(masterSecret, appKey, null, clientConfig);

    public static JPushClient instance() {
        return jpushClient;
    }
    /**
     * 向所有平台，所有设备推送消息
     */
    public static PushResult pushMessageToAll(String message) {
        PushPayload payload = PushPayload.alertAll(message);
        PushResult result = null;
        try {
            result = jpushClient.sendPush(payload);
        } catch (APIConnectionException e) {
            logger.error("连接错误，稍后重试", e);
        } catch (APIRequestException e) {
            logger.error("发送错误", e);
        }
        return result;
    }
    /**
     * 向所有平台，所有设备推送消息
     */
    public static PushResult pushMyMessageToAll(String message) {
        PushPayload payload = PushPayload.messageAll(message);
        PushResult result = null;
        try {
            result = jpushClient.sendPush(payload);
        } catch (APIConnectionException e) {
            logger.error("连接错误，稍后重试", e);
        } catch (APIRequestException e) {
            logger.error("发送错误", e);
        }

        return result;
    }

    /**
     * 向某个平台，某个别名，推送一条消息，单推时用此方法
     *
     * platformString的值为:all ios android android_ios(android和ios)
     *
     * alias:用户别名，每个用户设置不同的别名(同账号的意思)，则可进行单推；若若干用户同一个别名，则为群推
     */
    public static PushResult pushMessageToAlias(String platformString, String alias, String message) {

        Platform platform = null;
        if("android".equals(platformString)) {
            platform = Platform.android();
        } else if("ios".equals(platformString)) {
            platform = Platform.ios();
        } else if("android_ios".equals(platformString)) {
            platform = Platform.android_ios();
        } else {
            platform = Platform.all();
        }
        PushPayload payload = PushPayload.newBuilder()
                .setPlatform(platform)
                .setAudience(Audience.alias(alias))
                .setNotification(Notification.alert(message))
                .build();
        PushResult result = null;
        try {
            result = jpushClient.sendPush(payload);
        } catch (APIConnectionException e) {
            logger.error("连接错误，稍后重试", e);
        } catch (APIRequestException e) {
            logger.error("发送错误", e);
        }

        return result;
    }

    /**
     * 向某个平台，某个别名，推送一条消息，单推时用此方法
     *
     * platformString的值为:all ios android android_ios(android和ios)
     *
     * alias:用户别名，每个用户设置不同的别名(同账号的意思)，则可进行单推；若若干用户同一个别名，则为群推
     */
    public static PushResult pushMyMessageToAlias(String platformString, String alias, String message) {

        Platform platform = null;
        if("android".equals(platformString)) {
            platform = Platform.android();
        } else if("ios".equals(platformString)) {
            platform = Platform.ios();
        } else if("android_ios".equals(platformString)) {
            platform = Platform.android_ios();
        } else {
            platform = Platform.all();
        }
        PushPayload payload = PushPayload.newBuilder()
                .setPlatform(platform)
                .setAudience(Audience.alias(alias))
                .setMessage(Message.content(message))
                .build();
        PushResult result = null;
        try {
            result = jpushClient.sendPush(payload);
        } catch (APIConnectionException e) {
            logger.error("连接错误，稍后重试", e);
        } catch (APIRequestException e) {
            logger.error("发送错误", e);
        }

        return result;
    }

    /**
     * 向某个平台，某些标签，推送一条消息，群推时用此方法
     *
     * platformString的值为:all ios android android_ios(android和ios)
     *
     * tag:标签，多用户打上同一标签，可用于群推
     */
    public static PushResult pushMessageToTag(String platformString, String[] tag, String message) {

        Platform platform = null;
        if("android".equals(platformString)) {
            platform = Platform.android();
        } else if("ios".equals(platformString)) {
            platform = Platform.ios();
        } else if("android_ios".equals(platformString)) {
            platform = Platform.android_ios();
        } else {
            platform = Platform.all();
        }
        PushPayload payload = PushPayload.newBuilder()
                .setPlatform(platform)
                .setAudience(Audience.tag(tag))
                .setNotification(Notification.alert(message))
                .build();
        PushResult result = null;
        try {
            result = jpushClient.sendPush(payload);
        } catch (APIConnectionException e) {
            logger.error("连接错误，稍后重试", e);
        } catch (APIRequestException e) {
            logger.error("发送错误", e);
        }

        return result;
    }
    /**
     *
     *
     * platformString的值为:all ios android android_ios(android和ios)
     *
     * 向不同别名的用户的进行群发（多用户发送同一消息）
     */
    public static PushResult pushMessageToUsers(String platformString, String[] alias, String message) {

        Platform platform = null;
        if("android".equals(platformString)) {
            platform = Platform.android();
        } else if("ios".equals(platformString)) {
            platform = Platform.ios();
        } else if("android_ios".equals(platformString)) {
            platform = Platform.android_ios();
        } else {
            platform = Platform.all();
        }
        PushPayload payload = PushPayload.newBuilder()
                .setPlatform(platform)
                .setAudience(Audience.alias(alias))
                .setNotification(Notification.alert(message))
                .build();
        PushResult result = null;
        try {
            result = jpushClient.sendPush(payload);
        } catch (APIConnectionException e) {
            logger.error("连接错误，稍后重试", e);
        } catch (APIRequestException e) {
            logger.error("发送错误", e);
        }

        return result;
    }
}
