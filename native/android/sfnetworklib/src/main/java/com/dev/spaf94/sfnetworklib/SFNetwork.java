package com.dev.spaf94.sfnetworklib;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;

public class SFNetwork {

    IntentFilter intentFilter;
    BroadcastReceiver broadcastReceiver;

    public boolean haveNetworkConnection(Context context) {
        if(context != null) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            if (connectivityManager != null) {
                NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();

                if (networkInfo != null && networkInfo.length > 0) {
                    for (NetworkInfo ni : networkInfo) {
                        String name = ni.getTypeName();

                        if (name.equalsIgnoreCase("WIFI") || name.equalsIgnoreCase("MOBILE")) {
                            if (ni.isConnected()) {
                                return true;
                            }
                        }
                    }
                }
            }
        }

        return false;
    }

    public void startNetworkMonitor(AppCompatActivity appCompatActivity){
        this.intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");

        this.broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if(intent.getExtras() != null){
                    boolean haveConnection = haveNetworkConnection(context);
                    onConnectionStateChanged(haveConnection);
                }
            }
        };

        appCompatActivity.registerReceiver(this.broadcastReceiver, this.intentFilter);
    }

    public void stopNetworkMonitor(AppCompatActivity appCompatActivity){
        if(this.broadcastReceiver != null){
            appCompatActivity.unregisterReceiver(this.broadcastReceiver);
        }
    }

    public void onConnectionStateChanged(boolean haveConnection){

    }
}
