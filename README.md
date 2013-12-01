# Kortkoll for Android (beta)

Android project for Kortkoll, an app that shows information about your SL Access Card (Stockholm public transport). The Android client still needs some work and is not live on play yet.

Android client developed by [@heinrisch](http://twitter.com/heinrisch).

Kortkoll started out as a pet project of [@blommegard](http://twitter.com/blommegard), [gellermark](http://dribbble.com/gellermark),  [@peterssonjesper](http://twitter.com/peterssonjesper) and [@wibron](http://twitter.com/wibron).

The app is currently live on iOS in the [App Store](https://itunes.apple.com/se/app/kortkoll/id681422117) and was first developed on [Stockholm Startup Hack](http://www.sthlmstartuphack.com/).

## Why public?

The app is already free, so why not? I would love to get some feedback on my work and hopefully someone will join in and fix stuff.

## Password storing

Currently the app stores the users credentials in shared preferences with MODE_PRIVATE. This is relativly secure way to store credentials together with device encryption. The reason for using this solution is that we currently don't have a server side access token solution. You can read more about these kinds of things [here](http://android-developers.blogspot.com/2013/02/using-cryptography-to-store-credentials.html)

## Getting Started

Run the following commands to get started:

    $ git clone https://github.com/Kortkoll/kortkoll-ios.git
    $ cd kortkoll-android
    $ gradle tasks

Need help? Email <hej@kortkoll.nu> or open an issue with specifics.

## Contributing

If you want to fix bugs, I'll love you forever! If you want to add some features, I may not merge it. I'm sure it will be awesome, but defending Kortkoll's simplicity is my utmost duty. If you're feeling like implementing a feature, check out the [issues](https://github.com/Kortkoll/kortkoll-android/issues) for things tagged with "feature".

## License

Kortkoll is released under the MIT-license (see the LICENSE file)

While it is not strictly forbidden by the license, I would greatly appreciate it if you didn't redistribute this app exactly the way it is in the App Store. There's nothing stopping you, but please don't be a jerk.

