package com.robson.a5gsupport;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

import androidx.core.content.ContextCompat;

public class NetworkUtils {

    public static boolean is5GConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager != null) {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

            if (networkInfo != null && networkInfo.isConnected()) {
                if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                    TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

                    if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE)
                            == PackageManager.PERMISSION_GRANTED) {
                        if (telephonyManager != null) {
                            // Check if the device is connected to a 5G network
                            return telephonyManager.getNetworkType() == TelephonyManager.NETWORK_TYPE_NR;
                        }
                    }
                }
            }
        }

        return false;
    }
}
