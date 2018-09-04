# Fragment Stack Manager
[![platform](https://img.shields.io/badge/plataform-Android-brightgreen.svg)](https://www.android.com)
[![API](https://img.shields.io/badge/API-16%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=16)
[![GitHub version](https://badge.fury.io/gh/douglasspgyn%2FFragmentStackManager.svg)](https://badge.fury.io/gh/douglasspgyn%2FFragmentStackManager)
[![JitPack version](https://jitpack.io/v/douglasspgyn/FragmentStackManager.svg)](https://jitpack.io/#douglasspgyn/FragmentStackManager)

A custom and easy Fragment Stack Manager.

Don't worry about Fragment Stack anymore, focus on what you like and matters, create a better product. The Fragment Stack Manager will do all the hard work without change any of the main behaviors from Android.

Imagine the Activity just like a holder for fragments, you need to set the title, options menu and still can handle the ActivityResult, all inside the Fragment. And you can push/pop a Fragmente whenever you have a Context (like inside an Adapter).

You can see a [Sample Project here](https://github.com/douglasspgyn/FragmentStackManagerSample) and learn more on the [Wiki](https://github.com/douglasspgyn/FragmentStackManager/wiki).

## Add to your project:
You just need to add the Maven Jitpack repository on Project Gradle:
```xml
 allprojects {
    repositories {
      maven { url 'https://jitpack.io' }
    }
 }
```
and the library dependence on Module Gradle:
```xml
 dependencies {
    implementation 'com.github.douglasspgyn:FragmentStackManager:0.1.0'
 }
```
