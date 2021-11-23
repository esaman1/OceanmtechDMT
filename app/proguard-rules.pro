# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile


-keep class com.oceanmtech.dmt.Model.*{*;}
-keep class com.oceanmtech.dmt.DataBase.*{*;}
-keep class com.oceanmtech.dmt.Activity.*{*;}
-keep class com.oceanmtech.dmt.Adapter.*{*;}
-keep class com.oceanmtech.dmt.api.*{*;}
-keep class com.oceanmtech.dmt.QuoteCreater.Constans.*{*;}
-keep class com.oceanmtech.dmt.Fragment.*{*;}
-keep class com.oceanmtech.dmt.home_models.*{*;}
-keep class com.oceanmtech.dmt.home_adapters.*{*;}
-keep class com.oceanmtech.dmt.Welcome.*{*;}
-keep class com.oceanmtech.dmt.Utils.BASE_URl.*{*;}
-keep class com.oceanmtech.dmt.MyFirebaseMessagingService.*{*;}
-keep class com.oceanmtech.dmt.PrefManager1.*{*;}
-keep class com.oceanmtech.dmt.PrefManager.*{*;}

-keepclassmembers class com.oceanmtech.dmt.Utils {
   public *;
}

-keepclassmembers class * {
    @android.webkit.JavascriptInterface <methods>;
}

-keepattributes JavascriptInterface
-keepattributes *Annotation*

-dontwarn com.razorpay.**
-keep class com.razorpay.** {*;}

-optimizations !method/inlining/*

-keepclasseswithmembers class * {
  public void onPayment*(...);
}
# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
