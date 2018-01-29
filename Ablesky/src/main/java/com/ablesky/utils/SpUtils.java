 package com.ablesky.utils;

import android.content.Context;
import android.content.SharedPreferences;

 /**
  * Sp存储的工具类
  */
 public class SpUtils {

     private static SharedPreferences sp;
     private static SpUtils instance = new SpUtils();

     private SpUtils() {
     }

     public static SpUtils getInstance(Context context) {
         if (sp == null) {
             sp = context.getSharedPreferences("ablesky", Context.MODE_PRIVATE);
         }
         return instance;
     }

     public void put(String key, Object value) {
         if (value instanceof Integer) {
             sp.edit().putInt(key, (Integer) value).apply();
         } else if (value instanceof String) {
             sp.edit().putString(key, (String) value).apply();
         } else if (value instanceof Boolean) {
             sp.edit().putBoolean(key, (Boolean) value).apply();
         } else if (value instanceof Float) {
             sp.edit().putFloat(key, (Float) value).apply();
         } else if (value instanceof Long) {
             sp.edit().putLong(key, (Long) value).apply();
         }
     }

     public <T> T get(String key, T defValue) {
         T t = null;
         if (defValue instanceof String || defValue == null) {
             String value = sp.getString(key, (String) defValue);
             t = (T) value;
         } else if (defValue instanceof Integer) {
             Integer value = sp.getInt(key, (Integer) defValue);
             t = (T) value;
         } else if (defValue instanceof Boolean) {
             Boolean value = sp.getBoolean(key, (Boolean) defValue);
             t = (T) value;
         }else if (defValue instanceof Float){
             Float value = sp.getFloat(key, (Float) defValue);
             t = (T) value;
         }else if (defValue instanceof Long){
             Long value = sp.getLong(key, (Long) defValue);
             t = (T) value;
         }
         return t;
     }

     public void remove(String key) {
         sp.edit().remove(key).apply();
     }

     public  boolean isContainsKey(String key){
         return sp.contains(key);
     }
 }
