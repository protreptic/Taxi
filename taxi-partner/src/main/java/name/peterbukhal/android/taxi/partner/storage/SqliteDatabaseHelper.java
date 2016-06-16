package name.peterbukhal.android.taxi.partner.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import name.peterbukhal.android.taxi.partner.model.Track;
import name.peterbukhal.android.taxi.partner.model.TrackPoint;

/**
 * Created by
 *      petronic on 08.06.16.
 */
public class SqliteDatabaseHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "taxi-partner";

    private Context mContext;

    public SqliteDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(schemaScript(DATABASE_VERSION));
    }

    private String schemaScript(int version) {
        StringBuilder stringBuilder = new StringBuilder();

        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(mContext.getAssets().open("schema_v" + version + ".sql")));

            String string;

            while ((string = reader.readLine()) != null) {
                stringBuilder.append(string);
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for (int version = oldVersion; oldVersion < newVersion; version++) {
            db.execSQL(schemaUpdateScript(version));
        }
    }

    private String schemaUpdateScript(int version) {
        StringBuilder stringBuilder = new StringBuilder();

        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(mContext.getAssets().open("schema_v" + version + "_upd.sql")));

            String string;

            while ((string = reader.readLine()) != null) {
                stringBuilder.append(string);
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }

    private static final String TABLE_TRACK_POINT = "track_point";

    public long addTrackPoint(TrackPoint trackPoint) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("track_id", trackPoint.getTrackId());
        contentValues.put("provider", trackPoint.getProvider());
        contentValues.put("latitude", trackPoint.getLatitude());
        contentValues.put("longitude", trackPoint.getLongitude());
        contentValues.put("speed", trackPoint.getSpeed());
        contentValues.put("accuracy", trackPoint.getAccuracy());

        SQLiteDatabase db = getWritableDatabase();

        //noinspection TryFinallyCanBeTryWithResources
        try {
            return db.insert(TABLE_TRACK_POINT, null, contentValues);
        } finally {
            db.close();
        }
    }

}
