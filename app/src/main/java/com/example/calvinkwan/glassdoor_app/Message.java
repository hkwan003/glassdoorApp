package com.example.calvinkwan.glassdoor_app;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by calvinkwan on 4/1/16.
 */

public class Message
{
    public static void message(Context context, String message)
    {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}