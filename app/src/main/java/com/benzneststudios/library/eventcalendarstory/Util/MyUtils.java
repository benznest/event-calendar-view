package com.benzneststudios.library.eventcalendarstory.Util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;


import com.benzneststudios.library.eventcalendarstory.R;

import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 * Created by benznest on 01-Jul-16.
 */
public class MyUtils {

    private static boolean LOG_MODE = true;

    public static void log(String message) {
        if(LOG_MODE) {
            Log.d("Benznest DEBUG", message);
        }
    }

    public static int convertColor(String RGB) {
        try {
            return Color.parseColor("" + RGB);
        } catch (Exception e) {

        }
        return Color.parseColor("#FFFFFF");
    }

    public static void printHashKey(Context context) {
        PackageInfo info;
        try {
            info = context.getPackageManager().getPackageInfo("com.wisdomlanna.knowledgeplus.knowledgeplus", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                //String something = new String(Base64.encodeBytes(md.digest()));
                log("HASH KEY is  " + something);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            log("name not found = " + e1.toString());
        } catch (NoSuchAlgorithmException e) {
            log("no such an algorithm ," + e.toString());
        } catch (Exception e) {
            log("exception =" + e.toString());
        }
    }


    public static boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    public static Bitmap decodeUri(Context c, Uri uri, final int requiredSize)
            throws FileNotFoundException {
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(c.getContentResolver().openInputStream(uri), null, o);

        int width_tmp = o.outWidth
                , height_tmp = o.outHeight;
        int scale = 1;

        while(true) {
            if(width_tmp / 2 < requiredSize || height_tmp / 2 < requiredSize)
                break;
            width_tmp /= 2;
            height_tmp /= 2;
            scale *= 2;
        }

        BitmapFactory.Options o2 = new BitmapFactory.Options();
        o2.inSampleSize = scale;
        return BitmapFactory.decodeStream(c.getContentResolver().openInputStream(uri), null, o2);
    }

    public static String getDeviceID(Context context) {
        return Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }

    public static int getColorDayOfWeek(Context context,int dayOfWeek){
        int[] color = context.getResources().getIntArray(R.array.array_color_day_of_week);
        return color[dayOfWeek - 1];
    }
}
