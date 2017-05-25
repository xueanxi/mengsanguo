package mengsanguo.anxi.com.mengsanguo.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import mengsanguo.anxi.com.mengsanguo.constant.Constant;
import mengsanguo.anxi.com.mengsanguo.model.PlayerModel;

/**
 * Created by user on 5/25/17.
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = Constant.GAME_NAME + ".db";
    private static final int DB_VERSION = 1;
    private static DataBaseHelper sDatabaseHelper;

    public static synchronized DataBaseHelper getInstance(Context context) {
        if (sDatabaseHelper == null) {
            sDatabaseHelper = new DataBaseHelper(context);
        }
        return sDatabaseHelper;
    }

    private DataBaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        createPlayerTable(db);
    }

    private void createPlayerTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + PlayerModel.TABLE_NAME + " (" + UserInput.Column.ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + UserInput.Column.ISSUE_ID + " TEXT," + UserInput.Column.DESCRIPTION + " TEXT," + UserInput.Column.REPRODUCE
                + " TEXT," + UserInput.Column.EMAIL + " TEXT," + UserInput.Column.PHONE_STATUS_ID + " INTEGER,"
                + UserInput.Column.CATEGORY + " INTEGER," + UserInput.Column.TAG + " INTEGER," + UserInput.Column.STATUS
                + " SMALLINT," + UserInput.Column.ISSUE_STATUS + " INTEGER," + UserInput.Column.ROOT_DIR + " TEXT,"
                + UserInput.Column.CLIENT_ID + " TEXT," + UserInput.Column.ISSUS_FIXED_VERSION + " TEXT,"
                + UserInput.Column.BUG_ID + " TEXT," + UserInput.Column.APP_NAME + " TEXT," + UserInput.Column.APP_PACKAGE_NAME
                + " TEXT," + UserInput.Column.APP_VERSION_NAME + " TEXT," + UserInput.Column.ISSUE_TIME + " TEXT,"
                + UserInput.Column.FREQUENCY + " INTEGER," + UserInput.Column.SEND_LOG + " INTEGER,"
                + UserInput.Column.MODIFY_TIME + " TIMESTAMP," + UserInput.Column.CREATE_TIME + " TIMESTAMP,"
                + UserInput.Column.RECEIVED + " INTEGER," + UserInput.Column.SUB_CATEGORY + " INTEGER,"
                + UserInput.Column.SD_MODEL + " TEXT," + UserInput.Column.BT_MODEL + " TEXT,"
                + UserInput.Column.ZIP_FILE_SIZE + " INTEGER," + UserInput.Column.UPLOAD_SIZE + " INTEGER,"
                + UserInput.Column.BATTERY_TEMPERATURE + " INTEGER," + UserInput.Column.MEDIA_FILE_PATH + " TEXT, "
                + UserInput.Column.ISSUE_TIME_MILLIS + " INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}