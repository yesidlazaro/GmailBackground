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
 
