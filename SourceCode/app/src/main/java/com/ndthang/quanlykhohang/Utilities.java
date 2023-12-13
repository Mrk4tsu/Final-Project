package com.ndthang.quanlykhohang;

import android.content.Context;
import android.widget.Toast;

public class Utilities {
    public static int COUNT_TAB = 3;
    public static void addInfo(Context context, String mess){
        Toast.makeText(context, mess, Toast.LENGTH_LONG).show();
    }
}
