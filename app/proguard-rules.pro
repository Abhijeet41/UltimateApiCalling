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

-ignorewarnings
-keepattributes Signature
#-keep class org.apache.**{*;}
#-keep class com.google.**{*;}
-keepattributes *Annotation*

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keepattributes InnerClasses
#-keep class **.R
-keep class **.R$* {
    <fields>;
}
-dontpreverify
-repackageclasses ''
-allowaccessmodification
-optimizations !code/simplification/arithmetic
-keepattributes *Annotation*
-keepattributes EnclosingMethod
-keepattributes InnerClasses
-dontwarn org.xmlpull.v1.**
-dontwarn  com.crashlytics.**
-keep class com.google.android.gms.* { *; }
-dontwarn com.google.android.gms.**
-keep public class com.google.** {*;}
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keepattributes SourceFile,LineNumberTable
-renamesourcefileattribute SourceFile

-keep class * extends android.app.Activity
# ---------------------------shimmer----------------------------

-keep class com.facebook.shimmer.** { *; }

# ---------------------------Dagger hilt----------------------------

-keepnames @dagger.hilt.android.lifecycle.HiltViewModel class * extends androidx.lifecycle.ViewModel


# ---------------------------okhttp----------------------------
-keep class com.squareup.okhttp.** { *; }
-keep interface com.squareup.okhttp.** { *; }

-dontwarn com.squareup.okhttp.**
-dontwarn okio.**

-keepattributes Signature
-keepattributes *Annotation*
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }

-dontwarn okhttp3.**

#----------------------retrofit---------------------------
-keep class com.squareup.** { *; }
-keep interface com.squareup.** { *; }
-keep class retrofit2.** { *; }
-keep interface retrofit2.** { *;}
-keep interface com.squareup.** { *; }


-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}

-dontwarn retrofit2.**
-dontwarn org.codehaus.mojo.**
-keep class retrofit2.** { *; }
-keepattributes Signature
-keepattributes Exceptions
-keepattributes *Annotation*

-keepattributes RuntimeVisibleAnnotations
-keepattributes RuntimeInvisibleAnnotations
-keepattributes RuntimeVisibleParameterAnnotations
-keepattributes RuntimeInvisibleParameterAnnotations

-keepattributes EnclosingMethod

-keepclasseswithmembers interface * {
    @retrofit2.* <methods>;
}
-keepclassmembers,allowobfuscation class * {
  @com.google.gson.annotations.SerializedName <fields>;
}

# Keep Gson classes
-keep class com.google.gson.** { *; }
-keep class kotlin.Metadata { *; }

# Keep fields in Gson classes
-keepclassmembers class com.google.gson.** {
    <fields>;
}

#--------------------------okieo library--------------------------------
-dontwarn okhttp3.**
-dontwarn okio.**
-dontwarn javax.annotation.**
-dontwarn org.conscrypt.**
-dontwarn org.codehaus.mojo.animal_sniffer.*
-keepnames class okhttp3.internal.publicsuffix.PublicSuffixDatabase

#--------------------Coroutine libarary------------------------------
# ServiceLoader support
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}

# Most of volatile fields are updated with AFU and should not be mangled
-keepclassmembers class kotlinx.coroutines.** {
    volatile <fields>;
}

# Same story for the standard library's SafeContinuation that also uses AtomicReferenceFieldUpdater
-keepclassmembers class kotlin.coroutines.SafeContinuation {
    volatile <fields>;
}

# Keep the special GSON type adapters
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

# Keep enum types along with their field values
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Keep Parcelable implementation classes (if applicable)
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

#------------------lottie animation-----------------------
-dontwarn com.airbnb.lottie.**
-keep class com.airbnb.lottie.** {*;}

#---------------viewmodel--------------------------
#ViewModel

-keep class androidx.lifecycle.** { *; }


#--------------keep packages ------------------------
-keep class android.arch.** { *; }
-keep class af.airpay.dukasdk.model** { *; }
-keep class af.airpay.dukasdk.data** { *; }
-keep class af.airpay.dukasdk.adapter** { *; }
-keep class af.airpay.dukasdk.presentation.component** { *; }
-keep class af.airpay.dukasdk.util** { *; }
-keep class af.airpay.dukasdk.di.** { *; }

#--------------keep classes ------------------------
-keep class af.airpay.dukasdk.presentation.payment.PaymentState { *; }
-keep class af.airpay.dukasdk.presentation.payment_status.PaymentData { *; }
-keep class af.airpay.dukasdk.presentation.progress.PaymentProgressState { *; }
-keep class af.airpay.sampleyaku.util.MyApplication { *; }
# Dagger Hilt
-keep class dagger.hilt.android.internal.* { *; }
-keep class dagger.hilt.android.components.* { *; }
-keep class dagger.hilt.internal.* { *; }

# Hilt annotations
-keep @dagger.hilt.* class * {
    *;
}
-keep @javax.inject.* class * {
    *;
}

# Hilt generated code
-keepclasseswithmembers class * {
    @dagger.hilt.* *;
}

# Keep the generated Dagger Hilt injectors
-keep class **_MembersInjector {
  <fields>;
}

# Hilt
-dontwarn dagger.hilt.android.**
-keepattributes Annotation

# Hilt generated code
-keep class com.example.generated.* {*;}

# Hilt ViewModels
-keepclassmembers class * extends androidx.lifecycle.ViewModel {
    <init>(...);
}

# Retain Hilt Component interfaces
-keep,allowobfuscation interface * {
    @dagger.hilt.* <methods>;
}

# Keep Dagger annotations
-keepattributes Signature, InnerClasses, EnclosingMethod

# Keep Dagger module classes
-keep class * {
    @dagger.Module *;
}

# Keep Dagger component classes
-keep class * {
    @dagger.Component *;
}

# Keep Dagger scope annotations
-keep class * {
    @dagger.hilt.* *;
}


# Keep Dagger bind methods
-keep class * {
    @dagger.Binds *;
}

# Keep Dagger generated factories
-keep class *Factory {
    *;
}

# Keep Dagger generated members injection
-keep class *MembersInjector {
    *;
}

-keepclassmembers class * {
    @dagger.hilt.InstallIn *;
}

-keepclassmembers class * {
    @dagger.hilt.android.components.ViewModelComponent *;
    @dagger.hilt.android.components.ActivityComponent *;
}

-keep class dagger.hilt.android.plugin.** { *; }
-keep class dagger.hilt.internal.GeneratedComponentManagerHolder { *; }
-keep class dagger.hilt.android.internal.managers.ApplicationComponentManager { *; }
-keep class dagger.hilt.android.internal.managers.ViewComponentManager { *; }
-keep class dagger.hilt.android.internal.managers.FragmentComponentManager { *; }
-keep class dagger.hilt.android.** { *; }

-keep class dagger.hilt.components.* { *; }

-verbose
-printmapping mapping.txt

#--------------------keep classes--------------------------
-keep class com.abhi41.ultimateapicalling.data.network.** { *; }
-keep class com.abhi41.ultimateapicalling.data.repository.** { *; }
-keep class com.abhi41.ultimateapicalling.adapters.** { *; }
-keep class com.abhi41.ultimateapicalling.model.** { *; }
-keep class com.abhi41.ultimateapicalling.utils.** { *; }
-keep class com.abhi41.ultimateapicalling.di.** { *; }