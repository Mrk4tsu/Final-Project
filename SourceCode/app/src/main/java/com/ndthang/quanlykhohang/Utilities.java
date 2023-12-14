package com.ndthang.quanlykhohang;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.widget.Toast;

public class Utilities {
    public static int COUNT_TAB = 3;
    public static void addInfo(Context context, String mess){
        Toast.makeText(context, mess, Toast.LENGTH_LONG).show();
    }

}
