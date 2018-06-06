#import <Cordova/CDV.h>

@interface SFNetworkPlugin : CDVPlugin

- (void)haveNetworkConnection:(CDVInvokedUrlCommand*)command;

@end
