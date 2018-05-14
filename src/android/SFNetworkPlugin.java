package cordova.plugin.digitalarhttp;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

/**
 * This class echoes a string called from JavaScript.
 */
public class SFNetworkPlugin extends CordovaPlugin {

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

    private void haveNetworkConnection(CallbackContext callbackContext){
      try{
        Context context = this.cordova.getActivity().getApplicationContext();

        if(this.object.haveNetworkConnection(context)){
          callbackContext.success("OK");
        }else{
          callbackContext.error("NO_NETWORK_CONNECTION");
        }
      }catch(Exception e){
        callbackContext.error(e.getMessage());
      }
    }
}
