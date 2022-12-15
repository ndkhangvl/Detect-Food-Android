package com.food.lite.nckh.detection.sqlite;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.food.lite.nckh.detection.SharedData;
import com.food.lite.nckh.detection.model.Food;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;


import java.util.ArrayList;
import java.util.List;


public class sqlFood extends SQLiteAssetHelper {
    private static final String DB_NAME = "foodlist789.db";
    private static final int DB_VERSION = 1;
    //private static final String TABLE_NAME = "Food";

    public sqlFood(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    public ArrayList<Food> getFoodId(Integer food_id) { //add

        SQLiteDatabase db = getReadableDatabase();
        String q;
        if (SharedData.ngonngu == 1) {
            q = "SELECT lf.ma_mon,lf.ten_mon,lf.tinh,lf.gioi_thieu,lf.nguyen_lieu,lf.cach_lam,lf2.hinh_anh " +
                    "FROM listfood as lf join listfood2 as lf2 where lf.ma_mon = lf2.ma_mon and lf.ma_mon = " + food_id;
        } else {
            q = "SELECT * FROM listfood2 WHERE ma_mon= " + food_id;
        }
        //String q = "SELECT * FROM listfood WHERE ma_mon= " + food_id ;
        Cursor cursor = db.rawQuery(q, null);

        ArrayList<Food> resultfood = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                Food food = new Food();
                food.setIdFood(cursor.getString(cursor.getColumnIndexOrThrow("ma_mon")));
                food.setNameFood(cursor.getString(cursor.getColumnIndexOrThrow("ten_mon")));
                food.setRecipesFood(cursor.getString(cursor.getColumnIndexOrThrow("cach_lam")));
                food.setIntroductionFood(cursor.getString(cursor.getColumnIndexOrThrow("gioi_thieu")));
                food.setIngredientFood(cursor.getString(cursor.getColumnIndexOrThrow("nguyen_lieu")));
                food.setTinh(cursor.getString(cursor.getColumnIndexOrThrow("tinh")));
                food.setImgFood(cursor.getBlob(cursor.getColumnIndexOrThrow("hinh_anh")));
                resultfood.add(food);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return resultfood;
    }

    public List<Food> getFoodIdArray(List<Integer> food_id) { //add

        SQLiteDatabase db = getReadableDatabase();
        String q;
        List<Food> resultfood = new ArrayList<>();
        for (Integer refood : food_id) {
            if (SharedData.ngonngu == 1) {
                q = "SELECT lf.ma_mon,lf.ten_mon,lf.tinh,lf.gioi_thieu,lf.nguyen_lieu,lf.cach_lam,lf2.hinh_anh " +
                        "FROM listfood as lf join listfood2 as lf2 where lf.ma_mon = lf2.ma_mon and lf.ma_mon = ?";
            } else {
                q = "SELECT * FROM listfood2 WHERE ma_mon= ?";
            }
            //String q = "SELECT * FROM listfood WHERE ma_mon= " + food_id ;
            Cursor cursor = db.rawQuery(q, new String[] {"" +refood+ ""});

            if (cursor.moveToFirst()) {
                do {
                    Food food = new Food();
                    food.setIdFood(cursor.getString(cursor.getColumnIndexOrThrow("ma_mon")));
                    food.setNameFood(cursor.getString(cursor.getColumnIndexOrThrow("ten_mon")));
                    food.setRecipesFood(cursor.getString(cursor.getColumnIndexOrThrow("cach_lam")));
                    food.setIntroductionFood(cursor.getString(cursor.getColumnIndexOrThrow("gioi_thieu")));
                    food.setIngredientFood(cursor.getString(cursor.getColumnIndexOrThrow("nguyen_lieu")));
                    food.setTinh(cursor.getString(cursor.getColumnIndexOrThrow("tinh")));
                    food.setImgFood(cursor.getBlob(cursor.getColumnIndexOrThrow("hinh_anh")));
                    resultfood.add(food);
                } while (cursor.moveToNext());
            }
        }
        return resultfood;
    }

    public ArrayList<Food> getAllFood() {
        SQLiteDatabase db = getReadableDatabase();
        String q = "";
        /*
        if (Locale.getDefault().equals(Locale.ENGLISH.getLanguage())) {
            q = "SELECT lf.ma_mon,lf.ten_mon,lf.tinh,lf.gioi_thieu,lf.nguyen_lieu,lf.cach_lam,lf2.hinh_anh " +
                    "FROM listfood as lf join listfood2 as lf2 where lf.ma_mon = lf2.ma_mon order by lf.ma_mon asc";
        } else {
            q = "SELECT * FROM listfood2 order by ma_mon asc";
        }
*/
        if (SharedData.ngonngu == 1) {
            q = "SELECT lf.ma_mon,lf.ten_mon,lf.tinh,lf.gioi_thieu,lf.nguyen_lieu,lf.cach_lam,lf2.hinh_anh " +
                    "FROM listfood as lf join listfood2 as lf2 where lf.ma_mon = lf2.ma_mon order by lf.ma_mon asc";
        } else {
            q = "SELECT * FROM listfood2 order by ma_mon asc";
        }
        Cursor cursor = db.rawQuery(q, null);

        ArrayList<Food> resultfood = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                Food food = new Food();
                food.setNameFood(cursor.getString(cursor.getColumnIndexOrThrow("ten_mon")));
                food.setRecipesFood(cursor.getString(cursor.getColumnIndexOrThrow("cach_lam")));
                food.setIntroductionFood(cursor.getString(cursor.getColumnIndexOrThrow("gioi_thieu")));
                food.setIngredientFood(cursor.getString(cursor.getColumnIndexOrThrow("nguyen_lieu")));
                food.setTinh(cursor.getString(cursor.getColumnIndexOrThrow("tinh")));
                food.setImgFood(cursor.getBlob(cursor.getColumnIndexOrThrow("hinh_anh")));
                resultfood.add(food);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return resultfood;
    }

}
