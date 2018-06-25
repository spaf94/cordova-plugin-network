#import <Cordova/CDV.h>
#import "Reachability.h"

@interface SFNetworkPlugin : CDVPlugin

- (void)haveNetworkConnection:(CDVInvokedUrlCommand*)command;
- (void)startNetworkMonitor:(CDVInvokedUrlCommand*)command;
- (void)stopNetworkMonitor:(CDVInvokedUrlCommand*)command;

extern CDVPluginResult* monitorPluginResult;
extern CDVInvokedUrlCommand* monitorCommand;
extern Reachability* reachability;

@end
