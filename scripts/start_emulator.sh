#!/bin/bash
DEVICE=`emulator -list-avds| head -n 1`
$ANDROID_HOME/emulator/emulator -avd $DEVICE -netdelay none -netspeed full -wipe-data &
adb wait-for-device
A=$(adb shell getprop sys.boot_completed | tr -d '\r')
while [ "$A" != "1" ]; do
        sleep 2
        A=$(adb shell getprop sys.boot_completed | tr -d '\r')
done
echo "Emulators Ready"