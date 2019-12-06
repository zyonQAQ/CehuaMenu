package www.xyz.com.cehuamenu;

import android.app.Application;
import android.content.Context;
import android.os.Handler;



/**
 * Created by 我 on 2019/8/14.
 */

public class MyApplication extends Application {
    public static Context context;
    public static Handler handler;
    public static Thread thread;
    public static int threadId;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this.getApplicationContext();
        handler = new Handler();
        thread = Thread.currentThread();
        threadId = android.os.Process.myTid();
    }
}
