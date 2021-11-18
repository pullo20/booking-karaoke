package com.example.e_booking_karaoke;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.e_booking_karaoke.Model.Booking;
import com.example.e_booking_karaoke.Model.User;


/**
 * Created by Belal on 9/5/2017.
 */

//here for this class we are using a singleton pattern

public class SharedPrefManager {

    //the constants
    private static final String SHARED_PREF_NAME = "mUser";
    private static final String KEY_NAME = "keyusername";
    private static final String KEY_EMAIL = "keyemail";
    private static final String KEY_IDUSER = "keyiduser";
    private static final String KEY_NOHP= "nohp";
    private static final String KEY_PASSWORD= "password";
    private static final String KEY_STATUS= "status";
    private static final String KEY_WAKTU_MULAI= "waktu mulai";
    private static final String KEY_TGL_BOOKING = "tgl booking";
    private static final String KEY_ID_BOOK = "Mbooking";
    private static final String KEY_STATUS_BOOK = "sttts";
    private static final String KEY_NAMA_PESAN = "nama pesan";
    private static final String KEY_TYPE_ROOM = "type room";
    private static final String KEY_NO_ROOM = "L120";
    private static final String KEY_USER_ID = "iduser";
    private static final String KEY_JAM = "2930";
    private static final String KEY_NIK = "1234567890";
    private static final String KEY_NO_TLP = "0987087";
    private static final String KEY_HARGA = "788888";
    private static final String KEY_WAKTU_SELESAI= "tgl booking selesai";


    private static SharedPrefManager mInstance;
    private static Context mCtx;

    private SharedPrefManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    //method to let the user login
    //this method will store the user data in shared preferences
    public void userLogin(User user) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_IDUSER, user.getIdUser());
        editor.putString(KEY_NAME, user.getName());
        editor.putString(KEY_EMAIL, user.getEmail());
        editor.putString(KEY_NOHP,user.getNoHp());
        editor.putString(KEY_PASSWORD,user.getPassword());
        editor.putString(KEY_STATUS,user.getStatus());
        editor.apply();
    }

    public void komentarActive(User user) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
    }
//    public  void setImg(String imgSm, String imgLg){
//        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putString(KEY_IMAGESM, imgSm);
//        editor.putString(KEY_IMAGESM, imgLg);
//        editor.apply();
//    }
    //this method will checker whether user is already logged in or not
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_IDUSER, null) != null;
    }

    //this method will give the logged in user
    public User getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getString(KEY_IDUSER, null),
                sharedPreferences.getString(KEY_NAME, null),
                sharedPreferences.getString(KEY_EMAIL, null),
                sharedPreferences.getString(KEY_NOHP, null),
                sharedPreferences.getString(KEY_PASSWORD, null),
                sharedPreferences.getString(KEY_STATUS, null)
        );
    }
    public Booking getBook()
    {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new Booking(
                sharedPreferences.getString(KEY_ID_BOOK, null),
                sharedPreferences.getString(KEY_NAMA_PESAN, null),
                sharedPreferences.getString(KEY_NO_ROOM, null),
                sharedPreferences.getString(KEY_TYPE_ROOM, null),
                sharedPreferences.getString(KEY_STATUS_BOOK, null),
                sharedPreferences.getString(KEY_JAM, null),
                sharedPreferences.getString(KEY_HARGA, null),
                sharedPreferences.getString(KEY_USER_ID, null),
                sharedPreferences.getString(KEY_TGL_BOOKING, null),
                sharedPreferences.getString(KEY_WAKTU_MULAI, null),
                sharedPreferences.getString(KEY_WAKTU_SELESAI, null),
                sharedPreferences.getString(KEY_NO_TLP, null),
                sharedPreferences.getString(KEY_NIK, null)
        );
    }

    //this method will logout the user
    public void logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        mCtx.startActivity(new Intent(mCtx, MainActivity.class));
    }
}