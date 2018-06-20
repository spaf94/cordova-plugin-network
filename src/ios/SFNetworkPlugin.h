#import <Cordova/CDV.h>

@interface SFNetworkPlugin : CDVPlugin

- (void)haveNetworkConnection:(CDVInvokedUrlCommand*)command;
- (void)startNetworkMonitor:(CDVInvokedUrlCommand*)command;
- (void)stopNetworkMonitor:(CDVInvokedUrlCommand*)command;

@end
