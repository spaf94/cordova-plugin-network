module.exports = {
    haveNetworkConnection: function (successCallback, errorCallback) {
        cordova.exec(successCallback, errorCallback, "SFNetworkPlugin", "haveNetworkConnection", []);
    }
};
