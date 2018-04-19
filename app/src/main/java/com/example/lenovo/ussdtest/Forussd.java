package com.example.lenovo.ussdtest;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Intent;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Toast;

public class Forussd extends AccessibilityService {

    public static String TAG = "Forussd";

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        Log.d(TAG, "onAccessibilityEvent");
        String text = event.getText().toString();

        if (event.getClassName().equals("android.app.AlertDialog")) {
            performGlobalAction(GLOBAL_ACTION_BACK);
            Toast.makeText(this, " UssdMessage:- " + text, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent("com.times.ussd.action.REFRESH");
            intent.putExtra("message", text);
            // write a broad cast receiver and call sendbroadcast() from here, if you want to parse the message for balance, date
        }
    }

    @Override
    public void onInterrupt() {
    }

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        Log.d(TAG, "onServiceConnected");
        AccessibilityServiceInfo info = new AccessibilityServiceInfo();
        info.flags = AccessibilityServiceInfo.DEFAULT;
        info.packageNames = new String[]{"com.android.phone"};
        info.eventTypes = AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED;
        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_GENERIC;
        setServiceInfo(info);
    }

}