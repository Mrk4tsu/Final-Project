package com.ndthang.quanlykhohang.helper;

import android.content.Context;
import android.widget.Toast;

import com.ndthang.quanlykhohang.MainActivity;

public class Utilities {
    public static int COUNT_TAB = 3;
    public static void addInfo(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
