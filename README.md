# Who Sings

<p align="center">
  <img src="https://github.com/dariobrux/Who-Sings/blob/main/app/src/main/res/mipmap-xxxhdpi/ic_launcher.png" />
</p>

This Android application written in Kotlin is a simple game that shows a lyrics snippet and 3 different singers. The goal is choosing the right singer.

|            |.              |        |
|:----------:|:-------------:|:------:|
| ![Splash screen](https://github.com/dariobrux/Who-Sings/blob/main/other/screenshot/Screenshot_1608244146.png) |  ![Login screen - ready to fill](https://github.com/dariobrux/Who-Sings/blob/main/other/screenshot/Screenshot_1608244153.png) | ![Login screen - name filled](https://github.com/dariobrux/Who-Sings/blob/main/other/screenshot/Screenshot_1608244437.png) |
| ![Score screen](https://github.com/dariobrux/Who-Sings/blob/main/other/screenshot/Screenshot_1608244327.png) |    ![Game screen](https://github.com/dariobrux/Who-Sings/blob/main/other/screenshot/Screenshot_1608244340.png)  |   ![Result screen](https://github.com/dariobrux/Who-Sings/blob/main/other/screenshot/Screenshot_1608244410.png)  |

## In this app you will find:
- a splash screen
- a login screen, where you can insert your username to play.
- a score screen, that displays the top scores of each player.
- a game scren, where you can play.
- a result screen, that's displayed when a match ends.

## The code contains:
- Only one Activity. It's a Single-Activity project.
- MVVM + Repository pattern as main architecture.
- View binding
- Hilt as Dependency Injection
- Room Database
- Retrofit as HTTP Client
- JetPack Navigation Component to navigate between fragments
- Kotlin Coroutines for asynchronous code
- LiveData as observable data
- LeakCanary to check memory leaks
- Timber to log
