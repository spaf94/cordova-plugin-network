package com.dev.spaf94.sfnetworklib;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NetworkStateReceiver extends BroadcastReceiver {

    SFNetwork sfNetwork;

    public NetworkStateReceiver() {
        sfNetwork = new SFNetwork();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getExtras() != null){
            boolean haveConnection = sfNetwork.haveNetworkConnection(context);
            sfNetwork.onConnectionStateChanged(haveConnection);
        }
    }
}
