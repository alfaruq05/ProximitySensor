package android.example.proximitysensor.DatabaseHelpers;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.example.proximitysensor.DataFragment;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class ProximityDatabaseHelper extends SQLiteOpenHelper{
    static final String DATABASE_NAME = "Proximity.db";
    static final String TABLE_NAME = "Proximity_Details";
    static final int VERSION_NUMBER = 3;
    static final String ID = "Id";
    static final String TIME = "Time";
    static final String VALUE = "Value";
    static final String CREATE_TABLE_COMMAND = "CREATE TABLE " + TABLE_NAME + "( " + ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + TIME + " VARCHAR(255), " + VALUE + " REAL);";
    static final String SHOW_ALL_DATA_COMMAND = "SELECT * FROM " + TABLE_NAME;
    DataFragment context;

    public ProximityDatabaseHelper(@Nullable DataFragment context) {
        super(context, DATABASE_NAME, null, VERSION_NUMBER);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try{
            sqLiteDatabase.execSQL(CREATE_TABLE_COMMAND);
        } catch (Exception e){
            Toast.makeText(context, "Exception: " + e, Toast.LENGTH_LONG).show();
        }
    }

    // Retrieve data from Proximity.db database
    public Cursor retrieveData(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.rawQuery(SHOW_ALL_DATA_COMMAND, null);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {}
}