# GmailBackground
a small library to send a email in background withou user interaction 
```java
        BackgroundMail.newBuilder(this)
                .withUsername("username@gmail.com")
                .withPassword("password12345")
                .withMailto("toemail@gmail.com")
                .withType(BackgroundMail.TYPE_PLAIN)
                .withSubject("this is the subject")
                .withBody("this is the body")
                .withOnSuccessCallback(new BackgroundMail.OnSuccessCallback() {
                    @Override
                    public void onSuccess() {
                        //do some magic
                    }
                })
                .withOnFailCallback(new BackgroundMail.OnFailCallback() {
                    @Override
                    public void onFail() {
                        //do some magic
                    }
                })
                .send();
```
**Gradle via jitpack**

```groovy
 repositories {
        // ...
        maven { url "https://jitpack.io" }
 }
```
```groovy
 dependencies {
	        compile 'com.github.yesidlazaro:GmailBackground:1.2.0'
	}
```

**Permissions**
```xml
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
<uses-permission android:name="android.permission.INTERNET"/>
```
**attachments**

 for attachments you need set READ_EXTERNAL_STORAGE permission in your manifiest 
 ```xml
 <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
```
Based on https://github.com/kristijandraca/BackgroundMailLibrary (code cleanup, tweaks, and jitpack support)

**Proguard**
```
-keep class org.apache.** { *; }
-dontwarn org.apache.**

-keep class com.sun.mail.** { *; }
-dontwarn com.sun.mail.**

-keep class java.beans.** { *; }
-dontwarn java.beans.**
```

#license
Copyright 2015 Yesid Lazaro

Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements. See the NOTICE file distributed with this work for additional information regarding copyright ownership. The ASF licenses this file to you under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
