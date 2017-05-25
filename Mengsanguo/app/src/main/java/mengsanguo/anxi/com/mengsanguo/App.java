package mengsanguo.anxi.com.mengsanguo;

import android.app.Application;

/**
 * Created by user on 5/25/17.
 */
public class App extends Application{
    static final String TAG = "App";
    private static final Object mSyncObject = new Object();
    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    private App() {
        super();
        instance = this;
    }

    /**
     * Singleton
     *
     * @return singleton Application instance
     */
    public static App getInstance() {
        synchronized (mSyncObject) {
            if (null == instance) {
                instance = new App();
            }
        }
        return instance;
    }
}
