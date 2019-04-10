package com.ninestartsvinaytask;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;



public class Utilities {
    private static ProgressDialog mProgressDialog = null;

    @SuppressLint("NewApi")
    public static void displayProgressDialog(Context context, String message, Boolean backButtonCancelable) {
        try {
            if (mProgressDialog == null && context != null) {
                mProgressDialog = new ProgressDialog(context);
                mProgressDialog.setMessage(message);
                mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                mProgressDialog.setIndeterminate(true);
                mProgressDialog.setCanceledOnTouchOutside(false);
                mProgressDialog.setCancelable(backButtonCancelable);
                mProgressDialog.show();
            }
        } catch (Exception e) {

        }
    }

    public static void cancelProgressDialog() {
        try {
            if (mProgressDialog != null) {
                mProgressDialog.dismiss();
                mProgressDialog.cancel();
                mProgressDialog = null;
            }
        } catch (Exception e) {

        }
    }

}
