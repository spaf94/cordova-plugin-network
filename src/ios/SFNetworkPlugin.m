#import "SFNetworkPlugin.h"
#import "Reachability.h"

#include <unistd.h>
#include <netdb.h>

@implementation SFNetworkPlugin

CDVPluginResult* monitorPluginResult = nil;
CDVInvokedUrlCommand* monitorCommand = nil;
Reachability* reachability = nil;

- (void)haveNetworkConnection:(CDVInvokedUrlCommand*)command
{
    CDVPluginResult* pluginResult = nil;

    char *hostname;
    struct hostent *hostinfo;
    hostname = "google.com";
    hostinfo = gethostbyname(hostname);

    if (hostinfo == NULL)
    {
        //NSLog(@"-> no connection!\n");
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
    }
    else
    {
        //NSLog(@"-> connection established!\n");
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@"OK"];
    }

    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

- (void)startNetworkMonitor:(CDVInvokedUrlCommand*)command
{
    monitorPluginResult = nil;

    reachability = [Reachability reachabilityWithHostname:@"www.google.com"];
    [reachability startNotifier];

    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(reachabilityDidChange:) name:kReachabilityChangedNotification object:nil];

    monitorCommand = command;

    monitorPluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@"OK"];
    [monitorPluginResult setKeepCallback:[NSNumber numberWithBool:YES]];

    [self.commandDelegate sendPluginResult:monitorPluginResult callbackId:command.callbackId];
}

- (void)reachabilityDidChange:(NSNotification *)notification {
    Reachability *reachability = (Reachability *)[notification object];
    monitorPluginResult = nil;
    
    if ([reachability isReachable]) {
        monitorPluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@"HAVE_NETWORK_CONNECTION"];
    } else {
        monitorPluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:@"NO_NETWORK_CONNECTION"];
    }

    [self.commandDelegate sendPluginResult:monitorPluginResult callbackId:monitorCommand.callbackId];
}

- (void)stopNetworkMonitor:(CDVInvokedUrlCommand*)command
{
    CDVPluginResult* pluginResult = nil;

	if (reachability) {
        [reachability stopNotifier];
    }
    
    pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@"OK"];

    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

@end
