# Cordova plugin network

Cordova plugin to monitor the network in your cordova mobile app.

## How to install
` cordova plugin add https://github.com/spaf94/cordova-plugin-network `

## How to use
#### Check if have network connection 
```javascript
function haveNetworkConnection(){

  var callbackAlert = function(msg){
    if(msg == 'OK'){
      alert('Network connection available!');
    }else{
      alert('Network connection not available!');
    }
  };

  sfNetworkPlugin.haveNetworkConnection(callbackAlert, callbackAlert);
}
```

#### Start to monitor the network state 
```javascript
function startNetworkMonitor(){

  var callbackAlert = function(msg){
    if(msg == 'HAVE_NETWORK_CONNECTION'){
      alert('Network connection available!');
    }else if(msg == 'NO_NETWORK_CONNECTION'){
      alert('Network connection not available!');
    }
  };

  sfNetworkPlugin.startNetworkMonitor(callbackAlert, callbackAlert);
}
```

#### Stop to monitor the network state
```javascript
function stopNetworkMonitor(){

  var callbackAlert = function(msg){
    console.log(msg);
  };

  sfNetworkPlugin.stopNetworkMonitor(callbackAlert, callbackAlert);
}
```

## Issues
If you find any bugs, please submit an [issue](https://github.com/spaf94/cordova-plugin-network/issues/new/).
