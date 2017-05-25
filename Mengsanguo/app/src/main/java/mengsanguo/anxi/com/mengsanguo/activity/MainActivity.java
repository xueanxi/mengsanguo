package mengsanguo.anxi.com.mengsanguo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;

import mengsanguo.anxi.com.mengsanguo.Log.BattleLog;
import mengsanguo.anxi.com.mengsanguo.R;
import mengsanguo.anxi.com.mengsanguo.model.PlayerModel;
import mengsanguo.anxi.com.mengsanguo.model.TeamModel;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "anxi_MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(new Runnable() {
            @Override
            public void run() {
                init();
            }
        }).start();
    }

    private void init() {
        PlayerModel play1 = new PlayerModel(175, 80, 80, 80, 80, 80, 60, true);
        PlayerModel play2 = new PlayerModel(60, 60, 70, 60, 60, 70, 90, true);
        PlayerModel play3 = new PlayerModel(80, 60, 50, 60, 60, 60, 60, true);
        PlayerModel play4 = new PlayerModel(90, 60, 40, 60, 60, 80, 50, true);

        play1.setName("张飞");
        play2.setName("关羽");
        ArrayList<PlayerModel> ourPlayer = new ArrayList<PlayerModel>();
        ourPlayer.add(play1);
        ourPlayer.add(play2);
        TeamModel t1 = new TeamModel(TeamModel.CAMP_LEFT, ourPlayer);

        play3.setName("颜良");
        play4.setName("文丑");
        ArrayList<PlayerModel> enemyPlayer = new ArrayList<PlayerModel>();
        enemyPlayer.add(play3);
        enemyPlayer.add(play4);
        TeamModel t2 = new TeamModel(TeamModel.CAMP_RIGHT, enemyPlayer);

    }


    public static boolean isBattle = true;

}
