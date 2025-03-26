#import <Flutter/Flutter.h>

@interface FlutterConfigEnvPlugin : NSObject<FlutterPlugin>
+ (NSDictionary *)env;
+ (NSString *)envFor: (NSString *)key;
@end
