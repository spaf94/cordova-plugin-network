package cordova.plugin.network;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import com.dev.spaf94.sfnetworklib.SFNetwork;

/**
 * This class echoes a string called from JavaScript.
 */
public class SFNetworkPlugin extends CordovaPlugin {

    private SFNetwork sfNetwork = null;
    private CallbackContext onConnectionStateChangedCallback = null;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        boolean result = true;

        if(action.equals("haveNetworkConnection")){
          this.haveNetworkConnection(callbackContext);
        else if(action.equals("startNetworkMonitor")){
          this.startNetworkMonitor(callbackContext);
        }else if(action.equals("stopNetworkMonitor")){
          this.stopNetworkMonitor(callbackContext);
        }else{
          result = false;
        }

        return result;
    }

    private void initializeSFNetwork(){
      if(this.sfNetwork == null){
        this.sfNetwork = new SFNetwork(){
            @Override
            public void onConnectionStateChanged(boolean haveConnection) {
                super.onConnectionStateChanged(haveConnection);
            }
        };
      }
    }

    private void haveNetworkConnection(CallbackContext callbackContext){
      try{
        this.initializeSFNetwork();
        Context context = this.cordova.getActivity().getApplicationContext();

        if(this.sfNetwork.haveNetworkConnection(context)){
          callbackContext.success("OK");
        }else{
          callbackContext.error("NO_NETWORK_CONNECTION");
        }
      }catch(Exception e){
        callbackContext.error(e.getMessage());
      }
    }

    private void startNetworkMonitor(CallbackContext callbackContext){
      try{
        this.initializeSFNetwork();
        this.setOnConnectionStateChangedCallback(callbackContext);
        this.sfNetwork.startNetworkMonitor(this.cordova.getActivity());
        callbackContext.sendPluginResult(this.createResult(true, true, "OK"));
      }catch(Exception e){
        callbackContext.sendPluginResult(this.createResult(false, true, e.getMessage()));
      }
    }

    private void setOnConnectionStateChangedCallback(CallbackContext callbackContext){
      this.onConnectionStateChangedCallback = callbackContext;
    }

    private void stopNetworkMonitor(CallbackContext callbackContext){
      try{
        this.initializeSFNetwork();
        this.sfNetwork.stopNetworkMonitor(this.cordova.getActivity());
        callbackContext.success("OK");
      }catch(Exception e){
        callbackContext.error(e.getMessage());
      }
    }

    private PluginResult createResult(boolean isSucess, boolean keepCallback, String message){
      PluginResult result = null;

      if(isSucess){
        result = new PluginResult(PluginResult.Status.OK, message);
      }else{
        result = new PluginResult(PluginResult.Status.OK, message);
      }

      result.setKeepCallback(keepCallback);

      return result;
    }
}
