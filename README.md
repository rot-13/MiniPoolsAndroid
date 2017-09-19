# MiniPoolsAndroid
Android client for mini pools

## Usage

You can configure the app to work against a mini-pools server hosted on Heroku or against a server running on your local machine.
To configure the app to work against Heroku select the `productionDebug` build variant and for local running server use the `localDebug` build variant.

Note that the local IP is set to `10.0.2.2` which is equivalent to `localhost` if you run the app from an Android Studio emulator.
If you choose to run the app in Genymotion you should change the `app/build.gradle` to use `10.0.3.2`.
