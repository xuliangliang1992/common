package com.iwhalecloud.common.receiver;

import android.content.Context;

import com.alibaba.sdk.android.push.MessageReceiver;
import com.alibaba.sdk.android.push.notification.CPushMessage;

import java.util.Map;

import timber.log.Timber;

/**
 * 用于接收收到的通知
 * @author xuliangliang
 * @date 2019-10-30
 * copyright(c) 浩鲸云计算科技股份有限公司
 */
public class MyMessageReceiver extends MessageReceiver {
    public static final String REC_TAG = "receiver";

    @Override
    public void onNotification(Context context, String title, String summary, Map<String, String> extraMap) {
        // TODO 收到推送通知
        Timber.tag(REC_TAG).e("Receive notification, title: " + title + ", summary: " + summary + ", extraMap: " + extraMap);
    }

    @Override
    public void onMessage(Context context, CPushMessage cPushMessage) {
        Timber.tag(REC_TAG).e("onMessage, messageId: " + cPushMessage.getMessageId() + ", title: " + cPushMessage.getTitle() + ", content:" + cPushMessage.getContent());
        // TODO 收到推送消息
    }

    @Override
    public void onNotificationOpened(Context context, String title, String summary, String extraMap) {
        Timber.tag(REC_TAG).e("onNotificationOpened, title: " + title + ", summary: " + summary + ", extraMap:" + extraMap);
        // TODO 点击通知

    }

    @Override
    protected void onNotificationClickedWithNoAction(Context context, String title, String summary, String extraMap) {
        Timber.tag(REC_TAG).e("onNotificationClickedWithNoAction, title: " + title + ", summary: " + summary + ", extraMap:" + extraMap);
    }

    @Override
    protected void onNotificationReceivedInApp(Context context, String title, String summary, Map<String, String> extraMap, int openType, String openActivity, String openUrl) {
        Timber.tag(REC_TAG).e("onNotificationReceivedInApp, title: " + title + ", summary: " + summary + ", extraMap:" + extraMap + ", openType:" + openType + ", openActivity:" + openActivity + ", openUrl:" + openUrl);
    }

    @Override
    protected void onNotificationRemoved(Context context, String messageId) {
        Timber.tag(REC_TAG).e("onNotificationRemoved");
    }
}
