#import "SFNetworkPlugin.h"
#include <unistd.h>
#include <netdb.h>

@implementation SFNetworkPlugin

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

@end
