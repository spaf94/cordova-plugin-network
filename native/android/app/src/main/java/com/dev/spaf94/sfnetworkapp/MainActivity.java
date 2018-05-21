package com.dev.spaf94.sfnetworkapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dev.spaf94.sfnetworklib.SFNetwork;

public class MainActivity extends AppCompatActivity {

    SFNetwork sfNetwork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.sfNetwork = new SFNetwork();
        this.sfNetwork.startNetworkMonitor(this);
    }

    /*
    private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getExtras() != null){
                boolean haveConnection = sfNetwork.haveNetworkConnection(context);
                sfNetwork.onConnectionStateChanged(haveConnection);
            }
        }
    };
    */

    @Override
    protected void onDestroy() {
        super.onDestroy();

        this.sfNetwork.stopNetworkMonitor(this);
    }
}
