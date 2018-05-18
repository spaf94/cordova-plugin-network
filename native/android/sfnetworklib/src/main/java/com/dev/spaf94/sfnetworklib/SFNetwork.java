package com.dev.spaf94.sfnetworklib;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class SFNetwork {

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

    public void onConnectionStateChanged(boolean haveConnection){

    }
}
