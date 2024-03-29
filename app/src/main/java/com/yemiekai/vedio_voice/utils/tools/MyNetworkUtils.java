package com.yemiekai.vedio_voice.utils.tools;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import com.yemiekai.vedio_voice.services.MyNetworkService;
import com.yemiekai.vedio_voice.utils.datas.Doctor.Doctor_p;

import java.util.ArrayList;

import static com.yemiekai.vedio_voice.utils.tools.StringUtils.debug_print;

/**
 * 用单例模式, 这个类只有1个实例.
 *
 * 这个类负责启动和绑定service, 通过Messenger与service通讯,
 * 把需要用到的网络功能封装成接口给被人调用, 最后把结果返回给调用者
 *
 */
public class MyNetworkUtils {
    private Messenger mRecevierReplyMsg= new Messenger(new ReceiverReplyMsgHandler());  // 用于接收
    private static Activity mActivity;
    private Messenger mService = null;  // 与服务端交互的Messenger
    private ServiceConnection mConnection;  // 与服务端链接的对象
    private boolean mBound;  // 是否绑定了

    // 单例
    private static MyNetworkUtils instance = new MyNetworkUtils();

    // 获取单例
    public static MyNetworkUtils getInstance(Activity activity){
        if(activity != null) {
            mActivity = activity;
        }
        return instance;
    }

    // 构造函数, 私有, 单例
    private MyNetworkUtils(){
        this.mConnection = new ServiceConnection() {
            public void onServiceConnected(ComponentName className, IBinder service) {
                /**
                 * 通过服务端传递的IBinder对象,创建相应的Messenger
                 * 通过该Messenger对象与服务端进行交互
                 */
                debug_print("on Service Connected");
                mService = new Messenger(service);
                mBound = true;
            }

            public void onServiceDisconnected(ComponentName className) {
                debug_print("on Service Disconnected");
                mService = null;
                mBound = false;
            }
        };
    }

    public void setActivity(Activity activity){
        this.mActivity = activity;
    }

    /**
     * 启动服务
     */
    public void startNetworkService(){
        Intent intent = new Intent(mActivity, MyNetworkService.class);
        mActivity.startService(intent);
        mActivity.bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    /**
     * 绑定服务
     * (调用完这个函数要等一等, 没这么快绑定成功)
     */
    public boolean bindService(){
        Intent intent = new Intent(mActivity, MyNetworkService.class);
        return mActivity.bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    /**
     * 解除绑定服务
     */
    public void unbindService(){
        if(!mBound) {
            return;
        }

        mActivity.unbindService(mConnection);
    }


    /**
     * 接收service发来的消息
     */
    private static class ReceiverReplyMsgHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                //接收服务端回复
                case MyNetworkService.MSG_SAY_HELLO:
                    Bundle bundle = msg.getData();
                    bundle.setClassLoader(Doctor_p.class.getClassLoader());

                    ArrayList<Doctor_p> doctorList = bundle.getParcelableArrayList("ppp");
                    String mmsg = bundle.getString("reply");
                    debug_print("receiver message from service:" + mmsg);
                    debug_print("receiver message from service:" + doctorList.get(0).name);
                    debug_print("receiver message from service:" + doctorList.get(1).name);
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    /*********** 以下函数供外部调用 ************/

    public void sayHello(int waitToSay) {
        if(!mBound){
            return;
        }
        // 创建与服务交互的消息实体Message
        Message msg = Message.obtain(null, MyNetworkService.MSG_SAY_HELLO, waitToSay, 0);
        msg.replyTo = mRecevierReplyMsg;
        try {
            //发送消息
            mService.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void testHTTP(){
        if(!mBound) {
            return;
        }

        Message msg = Message.obtain(null, MyNetworkService.MSG_SAY_HELLO2, 0, 0);
        msg.replyTo = mRecevierReplyMsg;
        try {
            //发送消息
            mService.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
