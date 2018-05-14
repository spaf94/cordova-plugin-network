package cordova.plugin.digitalarhttp;

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

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        boolean result = true;

        if(action.equals("haveNetworkConnection")){
          this.haveNetworkConnection(callbackContext);
        }else{
          result = false;
        }

        return result;
    }

    private void initializeSFNetwork(){
      if(this.sfNetwork == null){
        this.sfNetwork = new SFNetwork();
      }
    }

    private void haveNetworkConnection(CallbackContext callbackContext){
      try{
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
}
