module.exports = {
    haveNetworkConnection: function (successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "SFNetworkPlugin", "haveNetworkConnection", []);
    },
    startNetworkMonitor: function (successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "SFNetworkPlugin", "startNetworkMonitor", []);
    },
    stopNetworkMonitor: function (successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "SFNetworkPlugin", "stopNetworkMonitor", []);
    }
};
