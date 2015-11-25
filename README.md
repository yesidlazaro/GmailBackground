# GmailBackground
a small library to send a email in background withou user interaction 
```java
BackgroundMail backgroundMail=new BackgroundMail(MainActivity.this);
        backgroundMail.setGmailUserName("username@gmail.com");
        backgroundMail.setGmailPassword("password12345");
        backgroundMail.setMailTo("toemail@gmail.com");
        backgroundMail.setFormBody("this is the body");
        backgroundMail.setFormSubject("this is the subject");
        backgroundMail.send();
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
	        compile 'com.github.yesidlazaro:GmailBackground:1.0'
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
 
#license
Copyright 2015 Yesid Lazaro

Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements. See the NOTICE file distributed with this work for additional information regarding copyright ownership. The ASF licenses this file to you under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
