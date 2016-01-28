package com.example.xd.lab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xd on 24.01.2016.
 */
public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "Medicine";

    private static final String TABLE_MEDICINE = "medicine";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_COUNTRY = "country";
    private static final String KEY_SHELF_LIFE = "shelf_life";
    private static final String KEY_INTERNATIONAL_NAME = "international_name";
    private static final String KEY_CATEGORY = "category";
    private static final String KEY_STATUS = "status";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MEDICINE_TABLE = "CREATE TABLE " + TABLE_MEDICINE + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT,"
                + KEY_COUNTRY + " TEXT," + KEY_SHELF_LIFE + " TEXT," + KEY_INTERNATIONAL_NAME + " TEXT,"
                + KEY_CATEGORY + " TEXT," + KEY_STATUS + " TEXT" + ");";
        db.execSQL(CREATE_MEDICINE_TABLE);
        initInsert(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEDICINE);
        onCreate(db);
    }

    public Medicine getMedicineById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_MEDICINE, new String[]{KEY_ID,
                        KEY_NAME, KEY_COUNTRY, KEY_SHELF_LIFE, KEY_INTERNATIONAL_NAME}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        return new Medicine(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2), cursor.getString(3),
                cursor.getString(4), cursor.getString(5));
    }

    public List<Medicine> getAllMedicine() {
        String selectQuery = "SELECT  * FROM " + TABLE_MEDICINE;
        return getMedicinesByQuery(selectQuery);
    }

    public int getMedicineCount() {
        String countQuery = "SELECT  * FROM " + TABLE_MEDICINE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        return cursor.getCount();
    }

    public int updateMedicine(Medicine medicine) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, medicine.getName());
        values.put(KEY_COUNTRY, medicine.getCountry());
        values.put(KEY_SHELF_LIFE, medicine.getShelfLife());
        values.put(KEY_INTERNATIONAL_NAME, medicine.getInternationalName());
        values.put(KEY_CATEGORY, medicine.getCategory());

        return db.update(TABLE_MEDICINE, values, KEY_ID + " = ?",
                new String[]{String.valueOf(medicine.getID())});
    }

    public void deleteMedicine(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_MEDICINE, KEY_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

    public void addMedicine(Medicine medicine) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, medicine.getName());
        values.put(KEY_COUNTRY, medicine.getCountry());
        values.put(KEY_SHELF_LIFE, medicine.getShelfLife());
        values.put(KEY_INTERNATIONAL_NAME, medicine.getInternationalName());
        values.put(KEY_CATEGORY, medicine.getCategory());
        values.put(KEY_STATUS, "0");
        db.insert(TABLE_MEDICINE, null, values);
    }

    public List<Medicine> getMedicineByCategory(String category) {
        String selectQuery = "SELECT  * FROM " + TABLE_MEDICINE + " WHERE " + KEY_CATEGORY + " = '" + category + "'";
        return getMedicinesByQuery(selectQuery);
    }

    public List<Medicine> getMedicineNew() {
        String selectQuery = "SELECT  * FROM " + TABLE_MEDICINE + " WHERE " + KEY_STATUS + " = '0'";
        return getMedicinesByQuery(selectQuery);
    }

    public  List<Medicine> getExpiredMedicines()
    {
        return getMedicinesByComingEnd(0);
    }

    public List<Medicine> getMedicinesByComingEnd(long plus) {
        long now = System.currentTimeMillis();
        String selectQuery = "SELECT  * FROM " + TABLE_MEDICINE + " WHERE " + KEY_SHELF_LIFE + " IS NOT NULL AND " + KEY_SHELF_LIFE + " < '" + String.valueOf(now + plus) + "'";
        return getMedicinesByQuery(selectQuery);
    }

    private List<Medicine> getMedicinesByQuery(String selectQuery)
    {
        List<Medicine> medicines = new ArrayList<Medicine>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Medicine medicine = new Medicine();
                medicine.setID(Integer.parseInt(cursor.getString(0)));
                medicine.setName(cursor.getString(1));
                medicine.setCountry(cursor.getString(2));
                medicine.setShelLife(cursor.getString(3));
                medicine.setInternationalName(cursor.getString(4));
                medicine.setCategory(cursor.getString(5));
                medicine.setStatus(cursor.getString(6));

                medicines.add(medicine);
            } while (cursor.moveToNext());
        }
        return medicines;
    }


    private void initInsert(SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, "Гентос");
        values.put(KEY_COUNTRY, "Польша");
        values.put(KEY_SHELF_LIFE, String.valueOf(System.currentTimeMillis() + 860000));
        values.put(KEY_INTERNATIONAL_NAME, "Gentos");
        values.put(KEY_CATEGORY, "Урология");
        values.put(KEY_STATUS, "1");

        db.insert(TABLE_MEDICINE, null, values);

        values.put(KEY_NAME, "Провитал ");
        values.put(KEY_COUNTRY, "Польша");
        values.put(KEY_SHELF_LIFE, String.valueOf(System.currentTimeMillis() + 86000000));
        values.put(KEY_INTERNATIONAL_NAME, "Provital");
        values.put(KEY_CATEGORY, "Урология");
        values.put(KEY_STATUS, "1");

        db.insert(TABLE_MEDICINE, null, values);
        values.put(KEY_NAME, "Долгит ");
        values.put(KEY_COUNTRY, "Польша");
        values.put(KEY_SHELF_LIFE, String.valueOf(System.currentTimeMillis() + 8600000));
        values.put(KEY_INTERNATIONAL_NAME, "Dolgit");
        values.put(KEY_CATEGORY, "Хирургия");
        values.put(KEY_STATUS, "1");

        db.insert(TABLE_MEDICINE, null, values);
    }
}
