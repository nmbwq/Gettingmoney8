package money.com.gettingmoney.bai.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import butterknife.OnClick;
import money.com.gettingmoney.R;
import money.com.gettingmoney.util.MyXutils;
import money.com.gettingmoney.webutil.news.NewsUtil;

import static android.content.ContentValues.TAG;

public class SocketActivity extends Activity {
    Draft Draft_17 = new Draft_17();
    WebSocketClient webSocketClient;

    private Handler handler = new Handler();

    String messagele = "";

    Timer timer;
    //    true表示链接上  false表示没有链接上
    boolean falg;

    Thread thread;

    //    url=new URI("ws://192.168.0.202:8080/websocket?2");
//    ws://192.168.0.132:8070/websocket?id='+ userId +'&type=0

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.socket_layout);
        ButterKnife.inject(this);
//        connectServerWithTCPSocket();

        webSocketClient = new WebSocketClient(URI.create("ws://192.168.0.132:8070/websocket?id=1037&type=2"), Draft_17) {
            @Override
            public void onOpen(ServerHandshake handshakedata) {
                Log.d(TAG, "run() returned: " + "连接到服务器");
                //线程五秒执行一次（判断有没有进行连接上）
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        //
                       thread=new Thread(runnable);
                        thread.start();
                    }
                }, 1000, 5000);
                falg = true;
            }

            //后台返回的数据 可以是json
            @Override
            public void onMessage(String message) {
                Log.d(TAG, "run() returned: " + message);
                messagele = message;
                Log.d("Debug", "messagele.length()" + messagele.length());
                falg = true;
            }

            @Override
            public void onClose(int code, String reason, boolean remote) {
                Log.d("Debug", "未连接");
                Log.d(TAG, "onClose() returned: " + reason);
//                webSocketClient.connect();
                falg = false;
            }

            @Override
            public void onError(Exception ex) {
                Log.d(TAG, "onError() returned: " + ex);
                Log.d("Debug", "连接失败");
//                webSocketClient.connect();
                falg = false;
            }
        };
        initEvent();
    }

    private void initEvent() {
        webSocketClient.connect();
    }



    //心跳包的实现（webSocketClient.getReadyState()返回四种状态  OPEN表示正在连接状态 其余的状态重新连接就好）
    Runnable runnable = new Runnable() {
        public void run() {

            if (webSocketClient.getReadyState().toString().equals("OPEN")){
                Log.d("Debug","线程执行");
            }else {
                Log.d("Debug","重新连接");
                webSocketClient.connect();
            }

        }
    };
    //长连接成功以后  发送http请求
    Runnable addComment = new Runnable() {
        @Override
        public void run() {
            NewsUtil util = new NewsUtil();
            util.TextSocket("收到信息", "7", new MyXutils.XCallBack() {
                @Override
                public void onResponse(String result) {
                    try {
                        JSONObject object = new JSONObject(result);
                        Log.d("Debug", "消息发送成功");

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

        }
    };

    @OnClick({R.id.tv_end, R.id.tv_duankai})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_end:
                //通过http进行发送请求
//                new Thread(addComment).run();
                //websocket现在所处的状态
             Log.d("Debug","websocket现在所处的状态"+webSocketClient.getReadyState().toString());
                break;
            case R.id.tv_duankai:
                webSocketClient.close();
                timer.cancel();
                Log.d("Debug","websocket已经断开连接");
                break;
        }
    }
}







