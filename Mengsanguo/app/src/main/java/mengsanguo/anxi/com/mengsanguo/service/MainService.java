package mengsanguo.anxi.com.mengsanguo.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by user on 5/25/17.
 */
public class MainService extends Service {

    public static final int ACTION_START_BATTLE = 1;
    public static final int ACTION_PAUSE_BATTLE = 2;
    public static final int ACTION_STOP_BATTLE = 3;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
