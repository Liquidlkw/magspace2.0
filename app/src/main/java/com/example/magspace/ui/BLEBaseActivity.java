package com.example.magspace.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Calendar;

public class BLEBaseActivity extends Activity {

    private String ERROR = "exception";
    public String AS = "AppRunTime";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread.currentThread().setUncaughtExceptionHandler(MyUncaughtExceptionHandler.getInstance());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Thread.currentThread().setUncaughtExceptionHandler(null);
    }
}
