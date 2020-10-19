package com.highlands.tax.fun;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

import androidx.annotation.Nullable;
import timber.log.Timber;

/**
 * @author xuliangliang
 * @date 2019-10-12
 * copyright(c) Highlands
 */
public class HelloService extends Service {

    public static void main(String[] arr) {
       /* int row = 7;
        int column = 5;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (i == 0 || i == row - 1) {
                    if (j == column / 2) {
                        System.out.print('*');
                    } else {
                        System.out.print(' ');
                    }
                } else if (i == 1 || i == row - 2) {
                    if (j % 2 == 0) {
                        System.out.print('*');
                    } else {
                        System.out.print(' ');
                    }
                } else if (i == 2 || i == row - 3) {
                    if (j == 0 || j == column - 1) {
                        System.out.print(' ');
                    } else {
                        System.out.print('*');
                    }
                } else {
                    System.out.print('*');
                }
            }
            System.out.println();
        }*/

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 5; j++) {
                switch (i) {
                    case 0:
                    case 6:
                        System.out.print(j == 2 ? '*' : ' ');
                        break;
                    case 1:
                    case 5:
                        System.out.print(j % 2 == 0 ? '*' : ' ');
                        break;
                    case 2:
                    case 4:
                        System.out.print(j == 0 || j == 4 ? ' ' : '*');
                        break;
                    case 3:
                        System.out.print('*');
                        break;
                    default:
                        break;
                }
            }
            System.out.print('\n');
        }
    }

    private Looper mServiceLooper;
    private ServiceHandler mServiceHandler;

    // 处理从线程接收的消息
    private final class ServiceHandler extends Handler {
        public ServiceHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            // 通常我们在这里执行一些工作，比如下载文件。
            // 作为例子，我们只是睡个5秒钟。
            long endTime = System.currentTimeMillis() + 5 * 1000;
            while (System.currentTimeMillis() < endTime) {
                synchronized (this) {
                    try {
                        wait(endTime - System.currentTimeMillis());
                    } catch (Exception e) {
                    }
                }
            }
            // 根据startId终止服务，这样我们就不会在处理其它工作的过程中再来终止服务
            stopSelf(msg.arg1);
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.tag("service").d("onCreate");
        // 启动运行服务的线程。
        // 请记住我们要创建一个单独的线程，因为服务通常运行于进程的主线程中，可我们不想阻塞主线程。
        // 我们还要赋予它后台运行的优先级，以便计算密集的工作不会干扰我们的UI。
        HandlerThread thread = new HandlerThread("ServiceStartArguments",
                1);
        thread.start();

        // 获得HandlerThread的Looper队列并用于Handler
        mServiceLooper = thread.getLooper();
        mServiceHandler = new ServiceHandler(mServiceLooper);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();

        // 对于每一个启动请求，都发送一个消息来启动一个处理
        // 同时传入启动ID，以便任务完成后我们知道该终止哪一个请求。
        Message msg = mServiceHandler.obtainMessage();
        msg.arg1 = startId;
        mServiceHandler.sendMessage(msg);

        // 如果我们被杀死了，那从这里返回之后被重启
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "service done", Toast.LENGTH_SHORT).show();
    }
}
