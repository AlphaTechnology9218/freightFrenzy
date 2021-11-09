## NOTICE

This repository contains the public FTC SDK for the Freight Frenzy (2021-2022) competition season.

## Welcome!
This GitHub repository contains the source code that is used to build an Android app to control a *FIRST* Tech Challenge competition robot.  To use this SDK, download/clone the entire project to your local computer.

## Getting Started
If you are new to robotics or new to the *FIRST* Tech Challenge, then you should consider reviewing the [FTC Blocks Tutorial](https://github.com/FIRST-Tech-Challenge/FtcRobotController/wiki/Blocks-Tutorial) to get familiar with how to use the control system:

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[FTC Blocks Online Tutorial](https://github.com/FIRST-Tech-Challenge/FtcRobotController/wiki/Blocks-Tutorial)

Even if you are an advanced Java programmer, it is helpful to start with the [FTC Blocks tutorial](https://github.com/FIRST-Tech-Challenge/FtcRobotController/wiki/Blocks-Tutorial), and then migrate to the [OnBot Java Tool](https://github.com/FIRST-Tech-Challenge/FtcRobotController/wiki/OnBot-Java-Tutorial) or to [Android Studio](https://github.com/FIRST-Tech-Challenge/FtcRobotController/wiki/Android-Studio-Tutorial) afterwards.

## Downloading the Project
If you are an Android Studio programmer, there are several ways to download this repo.  Note that if you use the Blocks or OnBot Java Tool to program your robot, then you do not need to download this repository.

* If you are a git user, you can clone the most current version of the repository:

<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;git clone https://github.com/FIRST-Tech-Challenge/FtcRobotController.git</p>

* Or, if you prefer, you can use the "Download Zip" button available through the main repository page.  Downloading the project as a .ZIP file will keep the size of the download manageable.

* You can also download the project folder (as a .zip or .tar.gz archive file) from the Downloads subsection of the [Releases](https://github.com/FIRST-Tech-Challenge/FtcRobotController/releases) page for this repository.

* The Releases page also contains prebuilt APKs.

Once you have downloaded and uncompressed (if needed) your folder, you can use Android Studio to import the folder  ("Import project (Eclipse ADT, Gradle, etc.)").

## Getting Help
### User Documentation and Tutorials
*FIRST* maintains online documentation with information and tutorials on how to use the *FIRST* Tech Challenge software and robot control system.  You can access this documentation using the following link:

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[FtcRobotController Online Documentation](https://github.com/FIRST-Tech-Challenge/FtcRobotController/wiki)

Note that the online documentation is an "evergreen" document that is constantly being updated and edited.  It contains the most current information about the *FIRST* Tech Challenge software and control system.

### Javadoc Reference Material
The Javadoc reference documentation for the FTC SDK is now available online.  Click on the following link to view the FTC SDK Javadoc documentation as a live website:

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[FTC Javadoc Documentation](https://javadoc.io/org.firstinspires.ftc)

### Online User Forum
For technical questions regarding the Control System or the FTC SDK, please visit the FTC Technology forum:

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[FTC Technology Forum](https://ftcforum.firstinspires.org/forum/ftc-technology)

### Sample OpModes
This project contains a large selection of Sample OpModes (robot code examples) which can be cut and pasted into your /teamcode folder to be used as-is, or modified to suit your team's needs.

Samples Folder: &nbsp;&nbsp; [/FtcRobotController/src/main/java/org/firstinspires/ftc/robotcontroller/external/samples](FtcRobotController/src/main/java/org/firstinspires/ftc/robotcontroller/external/samples)

The readme.md file located in the [/TeamCode/src/main/java/org/firstinspires/ftc/teamcode](TeamCode/src/main/java/org/firstinspires/ftc/teamcode) folder contains an explanation of the sample naming convention, and instructions on how to copy them to your own project space.

# Release Information

## Version 7.0 (20210915-141025)

### Enhancements and New Features
* Adds support for external libraries to OnBotJava and Blocks.
    * Upload .jar and .aar files in OnBotJava.
      * Known limitation - RobotController device must be running Android 7.0 or greater.
      * Known limitation - .aar files with assets are not supported.
    * External libraries can provide support for hardware devices by using the annotation in the
      com.qualcomm.robotcore.hardware.configuration.annotations package.
    * External libraries can include .so files for native code.
    * External libraries can be used from OnBotJava op modes.
    * External libraries that use the following annotations can be used from Blocks op modes.
      * org.firstinspires.ftc.robotcore.external.ExportClassToBlocks
      * org.firstinspires.ftc.robotcore.external.ExportToBlocks
    * External libraries that use the following annotations can add new hardware devices:
      * com.qualcomm.robotcore.hardware.configuration.annotations.AnalogSensorType
      * com.qualcomm.robotcore.hardware.configuration.annotations.DeviceProperties
      * com.qualcomm.robotcore.hardware.configuration.annotations.DigitalIoDeviceType
      * com.qualcomm.robotcore.hardware.configuration.annotations.I2cDeviceType
      * com.qualcomm.robotcore.hardware.configuration.annotations.MotorType
      * com.qualcomm.robotcore.hardware.configuration.annotations.ServoType
    * External libraries that use the following annotations can add new functionality to the Robot Controller:
      * org.firstinspires.ftc.ftccommon.external.OnCreate
      * org.firstinspires.ftc.ftccommon.external.OnCreateEventLoop
      * org.firstinspires.ftc.ftccommon.external.OnCreateMenu
      * org.firstinspires.ftc.ftccommon.external.OnDestroy
      * org.firstinspires.ftc.ftccommon.external.WebHandlerRegistrar
* Adds support for REV Robotics Driver Hub
* Adds fully custom userspace USB gamepad driver to Driver Station (see "Advanced Gamepad Features" menu in DS settings)
    * Allows gamepads to work on devices without native Linux kernel support (e.g. some Romanian Motorola devices)
    * Allows the DS to read the unique serial number of each gamepad, enabling auto-recovery of dropped gamepads even if two gamepads of the same model drop. *(NOTE: unfortunately this does not apply to Etpark gamepads, because they do not have a unique serial)*
    * Reading the unique serial number also provides the ability to configure the DS to assign gamepads to a certain position by default (so no need to do start+a/b at all)
    * The LED ring on the Xbox360 gamepad and the RGB LED bar on the PS4 gamepad is used to indicate the driver position the gamepad is bound to
    * The rumble motors on the Xbox360, PS4, and Etpark gamepads can be controlled from OpModes
    * The 2-point touchpad on the PS4 gamepad can be read from OpModes
    * The "back" and "guide" buttons on the gamepad can now be safely bound to robot controls (Previously, on many devices, Android would intercept these buttons as home button presses and close the app)
    * Advanced Gamepad features are enabled by default, but may be disabled through the settings menu in order to revert to gamepad support provided natively by Android
* Improves accuracy of ping measurement
    * Fixes issue where the ping time showed as being higher than reality when initially connecting to or restarting the robot
    * To see the full improvement, you must update both the Robot Controller and Driver Station apps
* Updates samples located at [/FtcRobotController/src/main/java/org/firstinspires/ftc/robotcontroller/external/samples](FtcRobotController/src/main/java/org/firstinspires/ftc/robotcontroller/external/samples)
    * Added ConceptGamepadRumble and ConceptGamepadTouchpad samples to illustrtate the use of these new gampad capabilities.
    * Condensed existing Vuforia samples into just 2 samples (ConceptVuforiaFieldNavigation & ConceptVuforiaFieldNavigationWebcam) showing how to determine the robot's location on the field using Vuforia. These both use the current season's Target images.  
    * Added ConceptVuforiaDriveToTargetWebcam to illustrate an easy way to drive directly to any visible Vuforia target.
* Makes many improvements to the warning system and individual warnings
    * Warnings are now much more spaced out, so that they are easier to read
    * New warnings were added for conditions that should be resolved before competing
    * The mismatched apps warning now uses the major and minor app versions, not the version code
    * The warnings are automatically re-enabled when a Robot Controller app from a new FTC season is installed
* Adds support for I2C transactions on the Expansion Hub / Control Hub without specifying a register address
    * See section 3 of the [TI I2C spec](https://www.ti.com/lit/an/slva704/slva704.pdf)
    * Calling these new methods when using Modern Robotics hardware will result in an UnsupportedOperationException
* Changes VuforiaLocalizer `close()` method to be public
* Adds support for TensorFlow v2 object detection models.
* Reduces ambiguity of the Self Inspect language and graphics.
* OnBotJava now warns about potentially unintended file overwrites
* Improves behavior of the Wi-Fi band and channel selector on the Manage webpage.

### Bug fixes
 * Fixes Robot Controller app crash on Android 9+ when a Driver Station connects
 * Fixes issue where an Op Mode was responsible for calling shutdown on the
   TensorFlow TFObjectDetector. Now this is done automatically.
 * Fixes Vuforia initialization blocks to allow user to chose AxesOrder. Updated
   relevant blocks sample opmodes.
 * Fixes [FtcRobotController issue #114](https://github.com/FIRST-Tech-Challenge/FtcRobotController/issues/114)
   LED blocks and Java class do not work
 * Fixes match logging for Op Modes that contain special characters in their names
 * Fixes Driver Station OpMode controls becoming unresponsive if the Driver Station was set to the landscape layout and an OnBotJava build was triggered while an OpMode was running
 * Fixes the Driver Station app closing itself when it is switched away from, or the screen is turned off
 * Fixes "black swirl of doom" (Infinite "configuring Wi-Fi Direct" message) on older devices
 * Updates the wiki comment on the OnBotJava intro page

## Version 6.2 (20210218-074821)

### Enhancements
* Attempts to automatically fix the condition where a Control Hub's internal Expansion Hub is not
  working by re-flashing its firmware
* Makes various improvements to the Wi-Fi Direct pairing screen, especially in landscape mode
* Makes the Robot Controller service no longer be categorically restarted when the main activity is brought to foreground
    * (e.g. the service is no longer restarted simply by viewing the Self Inspect screen and pressing the back button)
    * It is still restarted if the Settings menu or Configure Robot menu is opened


### Bug fixes
* Fixes [FtcRobotController issue #71](https://github.com/FIRST-Tech-Challenge/FtcRobotController/issues/71)
  Cannot open OpModes in v6.1 Blocks offline editor
* Fixes [FtcRobotController issue #79](https://github.com/FIRST-Tech-Challenge/FtcRobotController/issues/79)
  6.1 causes a soft reboot on the Motorola E5 Play
* Fixes issue where the Control Hub OS's watchdog would restart the Robot Controller app if 
  the Control Hub was not able to communicate with its internal Expansion Hub
* Fixes certain I2C devices not showing up in the appropriate `HardwareMap` fields (such as `hardwareMap.colorSensor`) 
* Fixes issue where performing a Wi-Fi factory reset on the Control Hub would not set the Wi-Fi band to 2.4 GHz
* Fixes issue where OnBotJava might fail to create a new file if the option to "Setup Code for Configured Hardware" was selected
* Fixes issue where performing certain operations after an Op Mode crashes would temporarily break Control/Expansion Hub communication
* Fixes issue where a Control Hub with a configured USB-connected Expansion Hub would not work if the Expansion Hub was missing at startup
* Fixes potential issues caused by having mismatched Control/Expansion Hub firmware versions 
* Fixes [ftc_app issue 673](https://github.com/ftctechnh/ftc_app/issues/673) Latest matchlog is being deleted instead of old ones by RobotLog
* Fixes ConceptVuforiaUltimateGoalNavigationWebcam sample opmode by correctly orienting camera on robot.
* Fixes issue where logcat would be spammed with InterruptedExceptions when stop is requested from the Driver Station (this behavior was accidentally introduced in v5.3). This change has no impact on functionality.
* Fixes issue where the blocks editor fails to load if the name of any TeleOp opmode contains an apostrophe.

## Version 6.1 (20201209-113742)
* Makes the scan button on the configuration screen update the list of Expansion Hubs connected via RS-485
    * Fixes [SkyStone issue #143](https://github.com/FIRST-Tech-Challenge/SkyStone/issues/143)
* Improves web interface compatibility with older browser and Android System WebView versions.
* Fixes issue in UVC driver where some cameras (e.g. certain MS Lifecams) which reported frame intervals as rounded rather than truncated values (e.g. `666667*100ns` instead of `666666*100ns` for 15FPS) would fail to start streaming.
* Adds support in UVC driver for virtual PTZ control
* Adds support in UVC driver for gain (ISO) control
* Adds support in UVC driver for enabling/disable AE priority. This setting provides a means to tell the camera firmware either
    * A) It can undershoot the requested frame rate in order to provide a theoretically better image (i.e. with a longer exposure than the inter-frame period of the selected frame rate allows)
    * B) It *must* meet the inter-frame deadline for the selected frame rate, even if the image may be underexposed as a result
* Adds support for the Control Hub OS 1.1.2 Robot Controller watchdog
    * The Robot Controller app will be restarted if it stops responding for more than 10 seconds
* Adds support for using the Driver Station app on Android 10+
* Introduces an automatic TeleOp preselection feature
    * For details and usage guide, please see [this wiki entry](https://github.com/FIRST-Tech-Challenge/FtcRobotController/wiki/Automatically-Loading-a-Driver-Controlled-Op-Mode)
* Shows icon next to OpMode name in the OpMode list dropdown on the Driver Station to indicate the source of the OpMode (i.e. the programming tool used to create it)
* Fixes issue where the Driver Station app would exit after displaying the Configuring Wi-Fi Direct screen
* Fixes Blocks and OnBotJava prompts when accessed via the REV Hardware Client

## Version 6.0 (20200921-085816)

### Important Notes
* Version 6.0 is the version for the Ultimate Goal season.
* Requires Android Studio 4.0.
* Android Studio users need to be connected to the Internet the first time they build the app (in order to download needed packages for the build).
* Version 5.5 was a moderately large off-season, August 2020, drop.  It's worth reviewing those release notes below also.
* Version 5.5 and greater will not work on older Android 4.x and 5.x phones.  Users must upgrade to an approved Android 6.x device or newer.
* The default PIDF values for REV motors have been reverted to the default PID values that were used in the 2018-2019 season
    * This change was made because the 2018-2019 values turned out to work better for many mechanisms
    * This brings the behavior of the REV motors in line with the behavior of all other motors
    * If you prefer the 2019-2020 season's behavior for REV motors, here are the PIDF values that were in place, so that you can manually set them in your OpModes:
      <br>
      **HD Hex motors (all gearboxes):**
      Velocity PIDF values: `P = 1.17`, `I = 0.117`, `F = 11.7`
      Position PIDF values: `P = 5.0`
      **Core Hex motor:**
      Velocity PIDF values: `P = 4.96`, `I = 0.496`, `F = 49.6`
      Position PIDF values: `P = 5.0`

### New features
* Includes TensorFlow inference model and sample op modes to detect Ultimate Goal Starter Stacks (four rings vs single ring stack).
* Includes Vuforia Ultimate Goal vision targets and sample op modes.
* Introduces a digital zoom feature for TensorFlow object detection (to detect objects more accurately at greater distances).
* Adds configuration entry for the REV UltraPlanetary HD Hex motor

### Enhancements
* Adds setGain() and getGain() methods to the NormalizedColorSensor interface
    * By setting the gain of a color sensor, you can adjust for different lighting conditions.
      For example, if you detect lower color values than expected, you can increase the gain.
    * The gain value is only applied to the argb() and getNormalizedColors() methods, not to the raw color methods.
      The getNormalizedColors() method is recommended for ease-of-use and clarity, since argb() has to be converted.
    * Updates SensorColor Java sample to demonstrate gain usage
* Merges SensorREVColorDistance Java sample into SensorColor Java sample, which showcases best practices for all color sensors
* Improves retrieving values from the REV Color Sensor V3
    * Updates the normalization calculation of the RGB channels
    * Improves the calculation of the alpha channel (can be used as an overall brightness indicator)
    * Fixes the default sensor resolution, which caused issues with bright environments
    * Adds support for changing the resolution and measuring rate of the Broadcom sensor chip
    * Removes IR readings and calculations not meant for the Broadcom sensor chip

### Bug fixes
* Improves reliability of BNO055IMU IMU initialization to prevent random initialization failures (which manifested as `Problem with 'imu'`).

## Version 5.5 (20200824-090813)

Version 5.5 requires Android Studio 4.0 or later.

### New features
* Adds support for calling custom Java classes from Blocks OpModes (fixes [SkyStone issue #161](https://github.com/FIRST-Tech-Challenge/SkyStone/issues/161)).
    * Classes must be in the org.firstinspires.ftc.teamcode package.
    * To have easy access to the opMode, hardwareMap, telemetry, gamepad1, and gamepad2, classes can
      extends org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.
    * Methods must be public static and have no more than 21 parameters.
    * Methods must be annotated with org.firstinspires.ftc.robotcore.external.ExportToBlocks.
    * Parameters declared as OpMode, LinearOpMode, Telemetry, and HardwareMap are supported and the
      argument is provided automatically, regardless of the order of the parameters. On the block,
      the sockets for those parameters are automatically filled in.
    * Parameters declared as char or java.lang.Character will accept any block that returns text
      and will only use the first character in the text.
    * Parameters declared as boolean or java.lang.Boolean will accept any block that returns boolean.
    * Parameters declared as byte, java.lang.Byte, short, java.lang.Short, int, java.lang.Integer,
      long, or java.lang.Long,  will accept any block that returns a number and will round that
      value to the nearest whole number.
    * Parameters declared as float, java.lang.Float, double, java.lang.Double will accept any
      block that returns a number.
* Adds telemetry API method for setting display format
    * Classic
    * Monospace
    * HTML (certain tags only)
* Adds blocks support for switching cameras.
* Adds Blocks support for TensorFlow Object Detection with a custom model.
* Adds support for uploading a custom TensorFlow Object Detection model in the Manage page, which
  is especially useful for Blocks and OnBotJava users.
* Shows new Control Hub blink codes when the Wi-Fi band is switched using the Control Hub's button (only possible on Control Hub OS 1.1.2)
* Adds new warnings which can be disabled in the Advanced RC Settings
    * Mismatched app versions warning
    * Unnecessary 2.4 GHz Wi-Fi usage warning
    * REV Hub is running outdated firmware (older than version 1.8.2)
* Adds support for Sony PS4 gamepad, and reworks how gamepads work on the Driver Station
    * Removes preference which sets gamepad type based on driver position. Replaced with menu which allows specifying type for gamepads with unknown VID and PID
	* Attempts to auto-detect gamepad type based on USB VID and PID
	* If gamepad VID and PID is not known, use type specified by user for that VID and PID
	* If gamepad VID and PID is not known AND the user has not specified a type for that VID and PID, an educated guess is made about how to map the gamepad
* Driver Station will now attempt to automatically recover from a gamepad disconnecting, and re-assign it to the position it was assigned to when it dropped
    * If only one gamepad is assigned and it drops: it can be recovered
    * If two gamepads are assigned, and have **different** VID/PID signatures, and only one drops: it will be recovered
    * If two gamepads are assigned, and have **different** VID/PID signatures, and BOTH drop: both will be recovered
    * If two gamepads are assigned, and have **the same** VID/PID signatures, and only one drops: it will be recovered
    * If two gamepads are assigned, and have **the same** VID/PID signatures, and BOTH drop: **neither** will be recovered, because of the ambiguity of the gamepads when they re-appear on the USB bus.
    * There is currently one known edge case: if there are **two** gamepads with **the same** VID/PID signature plugged in, **but only one is assigned**, and they BOTH drop, it's a 50-50 chance of which one will be chosen for automatic recovery to the assigned position: it is determined by whichever one is re-enumerated first by the USB bus controller.
* Adds landscape user interface to Driver Station
    * New feature: practice timer with audio cues
    * New feature (Control Hub only): wireless network connection strength indicator (0-5 bars)
    * New feature (Control Hub only): tapping on the ping/channel display will switch to an alternate display showing radio RX dBm and link speed (tap again to switch back)
    * The layout will NOT autorotate. You can switch the layout from the Driver Station's settings menu.
### Breaking changes
* Removes support for Android versions 4.4 through 5.1 (KitKat and Lollipop). The minSdkVersion is now 23.
* Removes the deprecated `LinearOpMode` methods `waitOneFullHardwareCycle()` and `waitForNextHardwareCycle()`
### Enhancements
* Handles RS485 address of Control Hub automatically
    * The Control Hub is automatically given a reserved address
    * Existing configuration files will continue to work
    * All addresses in the range of 1-10 are still available for Expansion Hubs
    * The Control Hub light will now normally be solid green, without blinking to indicate the address
    * The Control Hub will not be shown on the Expansion Hub Address Change settings page
* Improves REV Hub firmware updater
    * The user can now choose between all available firmware update files
    * Version 1.8.2 of the REV Hub firmware is bundled into the Robot Controller app.
    * Text was added to clarify that Expansion Hubs can only be updated via USB.
    * Firmware update speed was reduced to improve reliability
    * Allows REV Hub firmware to be updated directly from the Manage webpage
* Improves log viewer on Robot Controller
    * Horizontal scrolling support (no longer word wrapped)
    * Supports pinch-to-zoom
    * Uses a monospaced font
    * Error messages are highlighted
    * New color scheme
* Attempts to force-stop a runaway/stuck OpMode without restarting the entire app
    * Not all types of runaway conditions are stoppable, but if the user code attempts to talk to hardware during the runaway, the system should be able to capture it.
* Makes various tweaks to the Self Inspect screen
    * Renames "OS version" entry to "Android version"
    * Renames "Wi-Fi Direct Name" to "Wi-Fi Name"
    * Adds Control Hub OS version, when viewing the report of a Control Hub
    * Hides the airplane mode entry, when viewing the report of a Control Hub
    * Removes check for ZTE Speed Channel Changer
    * Shows firmware version for **all** Expansion and Control Hubs
* Reworks network settings portion of Manage page
    * All network settings are now applied with a single click
    * The Wi-Fi Direct channel of phone-based Robot Controllers can now be changed from the Manage page
    * Wi-Fi channels are filtered by band (2.4 vs 5 GHz) and whether they overlap with other channels
    * The current Wi-Fi channel is pre-selected on phone-based Robot Controllers, and Control Hubs running OS 1.1.2 or later.
    * On Control Hubs running OS 1.1.2 or later, you can choose to have the system automatically select a channel on the 5 GHz band
* Improves OnBotJava
    * New light and dark themes replace the old themes (chaos, github, chrome,...)
        * the new default theme is `light` and will be used when you first update to this version
    * OnBotJava now has a tabbed editor
    * Read-only offline mode
* Improves function of "exit" menu item on Robot Controller and Driver Station
    * Now guaranteed to be fully stopped and unloaded from memory
* Shows a warning message if a LinearOpMode exists prematurely due to failure to monitor for the start condition
* Improves error message shown when the Driver Station and Robot Controller are incompatible with each other
* Driver Station OpMode Control Panel now disabled while a Restart Robot is in progress
* Disables advanced settings related to Wi-Fi Direct when the Robot Controller is a Control Hub.
* Tint phone battery icons on Driver Station when low/critical.
* Uses names "Control Hub Portal" and "Control Hub" (when appropriate) in new configuration files
* Improve I2C read performance
    * Very large improvement on Control Hub; up to ~2x faster with small (e.g. 6 byte) reads
    * Not as apparent on Expansion Hubs connected to a phone
* Update/refresh build infrastructure
    * Update to 'androidx' support library from 'com.android.support:appcompat', which is end-of-life
    * Update targetSdkVersion and compileSdkVersion to 28
    * Update Android Studio's Android plugin to latest
    * Fix reported build timestamp in 'About' screen
* Add sample illustrating manual webcam use: ConceptWebcam


### Bug fixes
* Fixes [SkyStone issue #248](https://github.com/FIRST-Tech-Challenge/SkyStone/issues/248)
* Fixes [SkyStone issue #232](https://github.com/FIRST-Tech-Challenge/SkyStone/issues/232) and
  modifies bulk caching semantics to allow for cache-preserving MANUAL/AUTO transitions.
* Improves performance when REV 2M distance sensor is unplugged
* Improves readability of Toast messages on certain devices
* Allows a Driver Station to connect to a Robot Controller after another has disconnected
* Improves generation of fake serial numbers for UVC cameras which do not provide a real serial number
    * Previously some devices would assign such cameras a serial of `0:0` and fail to open and start streaming
	* Fixes [ftc_app issue #638](https://github.com/ftctechnh/ftc_app/issues/638).
* Fixes a slew of bugs with the Vuforia camera monitor including:
    * Fixes bug where preview could be displayed with a wonky aspect ratio
    * Fixes bug where preview could be cut off in landscape
    * Fixes bug where preview got totally messed up when rotating phone
    * Fixes bug where crosshair could drift off target when using webcams
* Fixes issue in UVC driver on some devices ([ftc_app 681](https://github.com/ftctechnh/ftc_app/issues/681)) if streaming was started/stopped multiple times in a row
    * Issue manifested as kernel panic on devices which do not have [this kernel patch](https://lore.kernel.org/patchwork/patch/352400/).
    * On affected devices which **do** have the patch, the issue was manifest as simply a failure to start streaming.
    * The Tech Team believes that the root cause of the issue is a bug in the Linux kernel XHCI driver. A workaround was implemented in the SDK UVC driver.
* Fixes bug in UVC driver where often half the frames from the camera would be dropped (e.g. only 15FPS delivered during a streaming session configured for 30FPS).
* Fixes issue where TensorFlow Object Detection would show results whose confidence was lower than
  the minimum confidence parameter.
* Fixes a potential exploitation issue of [CVE-2019-11358](https://www.cvedetails.com/cve/CVE-2019-11358/) in OnBotJava
* Fixes changing the address of an Expansion Hub with additional Expansion Hubs connected to it
* Preserves the Control Hub's network connection when "Restart Robot" is selected
* Fixes issue where device scans would fail while the Robot was restarting
* Fix RenderScript usage
    * Use androidx.renderscript variant: increased compatibility
    * Use RenderScript in Java mode, not native: simplifies build
* Fixes webcam-frame-to-bitmap conversion problem: alpha channel wasn't being initialized, only R, G, & B
* Fixes possible arithmetic overflow in Deadline
* Fixes deadlock in Vuforia webcam support which could cause 5-second delays when stopping OpMode

## Version 5.4 (20200108-101156)
* Fixes [SkyStone issue #88](https://github.com/FIRST-Tech-Challenge/SkyStone/issues/88)
* Adds an inspection item that notes when a robot controller (Control Hub) is using the factory default password.
* Fixes [SkyStone issue #61](https://github.com/FIRST-Tech-Challenge/SkyStone/issues/61)
* Fixes [SkyStone issue #142](https://github.com/FIRST-Tech-Challenge/SkyStone/issues/142)
* Fixes [ftc_app issue #417](https://github.com/ftctechnh/ftc_app/issues/417) by adding more current and voltage monitoring capabilities for REV Hubs.
* Fixes [a crash sometimes caused by OnBotJava activity](https://ftcforum.firstinspires.org/forum/ftc-technology/76217-onbotjava-crashes-robot-controller)
* Improves OnBotJava autosave functionality [ftc_app #738](https://github.com/ftctechnh/ftc_app/issues/738)
* Fixes system responsiveness issue when an Expansion Hub is disconnected
* Fixes issue where IMU initialization could prevent Op Modes from stopping
* Fixes issue where AndroidTextToSpeech.speak() would fail if it was called too early
* Adds telemetry.speak() methods and blocks, which cause the Driver Station (if also updated) to speak text
* Adds and improves Expansion Hub-related warnings
    * Improves Expansion Hub low battery warning
        * Displays the warning immediately after the hub reports it
        * Specifies whether the condition is current or occurred temporarily during an OpMode run
        * Displays which hubs reported low battery
    * Displays warning when hub loses and regains power during an OpMode run
        * Fixes the hub's LED pattern after this condition
    * Displays warning when Expansion Hub is not responding to commands
        * Specifies whether the condition is current or occurred temporarily during an OpMode run
    * Clarifies warning when Expansion Hub is not present at startup
        * Specifies that this condition requires a Robot Restart before the hub can be used.
        * The hub light will now accurately reflect this state
    * Improves logging and reduces log spam during these conditions
* Syncs the Control Hub time and timezone to a connected web browser programming the robot, if a Driver Station is not available.
* Adds bulk read functionality for REV Hubs
  * A bulk caching mode must be set at the Hub level with `LynxModule#setBulkCachingMode()`. This applies to all relevant SDK hardware classes that reference that Hub.
  * The following following Hub bulk caching modes are available:
    * `BulkCachingMode.OFF` (default): All hardware calls operate as usual. Bulk data can read through `LynxModule#getBulkData()` and processed manually.
    * `BulkCachingMode.AUTO`: Applicable hardware calls are served from a bulk read cache that is cleared/refreshed automatically to ensure identical commands don't hit the same cache. The cache can also be cleared manually with `LynxModule#clearBulkCache()`, although this is not recommended.
    * (advanced users) `BulkCachingMode.MANUAL`: Same as `BulkCachingMode.AUTO` except the cache is never cleared automatically. To avoid getting stale data, the cache must be manually cleared at the beginning of each loop body or as the user deems appropriate.
* Removes PIDF Annotation values added in Rev 5.3 (to AndyMark, goBILDA and TETRIX motor configurations).
  * The new motor types will still be available but their Default control behavior will revert back to Rev 5.2
* Adds new `ConceptMotorBulkRead` sample Opmode to demonstrate and compare Motor Bulk-Read modes for reducing I/O latencies.

## Version 5.3 (20191004-112306)
* Fixes external USB/UVC webcam support
* Makes various bugfixes and improvements to Blocks page, including but not limited to:
    * Many visual tweaks
    * Browser zoom and window resize behave better
    * Resizing the Java preview pane works better and more consistently across browsers
    * The Java preview pane consistently gets scrollbars when needed
    * The Java preview pane is hidden by default on phones
    * Internet Explorer 11 should work
    * Large dropdown lists display properly on lower res screens
    * Disabled buttons are now visually identifiable as disabled
    * A warning is shown if a user selects a TFOD sample, but their device is not compatible
    * Warning messages in a Blocks op mode are now visible by default.
* Adds goBILDA 5201 and 5202 motors to Robot Configurator
* Adds PIDF Annotation values to AndyMark, goBILDA and TETRIX motor configurations.
    This has the effect of causing the RUN_USING_ENCODERS and RUN_TO_POSITION modes to use
    PIDF vs PID closed loop control on these motors.  This should provide more responsive, yet stable, speed control.
    PIDF adds Feedforward control to the basic PID control loop.
    Feedforward is useful when controlling a motor's speed because it "anticipates" how much the control voltage
    must change to achieve a new speed set-point, rather than requiring the integrated error to change sufficiently.
    The PIDF values were chosen to provide responsive, yet stable, speed control on a lightly loaded motor.
    The more heavily a motor is loaded (drag or friction), the more noticable the PIDF improvement will be.
* Fixes startup crash on Android 10
* Fixes [ftc_app issue #712](https://github.com/ftctechnh/ftc_app/issues/712) (thanks to FROGbots-4634)
* Fixes [ftc_app issue #542](https://github.com/ftctechnh/ftc_app/issues/542)
* Allows "A" and lowercase letters when naming device through RC and DS apps.

## Version 5.2 (20190905-083277)
* Fixes extra-wide margins on settings activities, and placement of the new configuration button
* Adds Skystone Vuforia image target data.
   * Includes sample Skystone Vuforia Navigation op modes (Java).
   * Includes sample Skystone Vuforia Navigation op modes (Blocks).
* Adds TensorFlow inference model (.tflite) for Skystone game elements.
   * Includes sample Skystone TensorFlow op modes (Java).
   * Includes sample Skystone TensorFlow op modes (Blocks).
* Removes older (season-specific) sample op modes.
* Includes 64-bit support (to comply with [Google Play requirements](https://android-developers.googleblog.com/2019/01/get-your-apps-ready-for-64-bit.html)).
* Protects against Stuck OpModes when a Restart Robot is requested. (Thanks to FROGbots-4634) ([ftc_app issue #709](https://github.com/ftctechnh/ftc_app/issues/709))
* Blocks related changes:
   * Fixes bug with blocks generated code when hardware device name is a java or javascript reserved word.
   * Shows generated java code for blocks, even when hardware items are missing from the active configuration.
   * Displays warning icon when outdated Vuforia and TensorFlow blocks are used ([SkyStone issue #27](https://github.com/FIRST-Tech-Challenge/SkyStone/issues/27))

## Version 5.1 (20190820-222104)
* Defines default PIDF parameters for the following motors:
    * REV Core Hex Motor
    * REV 20:1 HD Hex Motor
    * REV 40:1 HD Hex Motor
* Adds back button when running on a device without a system back button (such as a Control Hub)
* Allows a REV Control Hub to update the firmware on a REV Expansion Hub via USB
* Fixes [SkyStone issue #9](https://github.com/FIRST-Tech-Challenge/SkyStone/issues/9)
* Fixes [ftc_app issue #715](https://github.com/ftctechnh/ftc_app/issues/715)
* Prevents extra DS User clicks by filtering based on current state.
* Prevents incorrect DS UI state changes when receiving new OpMode list from RC
* Adds support for REV Color Sensor V3
* Adds a manual-refresh DS Camera Stream for remotely viewing RC camera frames.
    * To show the stream on the DS, initialize **but do not run** a stream-enabled opmode, select the Camera Stream option in the DS menu, and tap the image to refresh. This feature is automatically enabled when using Vuforia or TFOD—no additional RC configuration is required for typical use cases. To hide the stream, select the same menu item again.
    * Note that gamepads are disabled and the selected opmode cannot be started while the stream is open as a safety precaution.
    * To use custom streams, consult the API docs for `CameraStreamServer#setSource` and `CameraStreamSource`.
* Adds many Star Wars sounds to RobotController resources.
* Added Skystone Sounds Chooser Sample Program.
* Switches out startup, connect chimes, and error/warning sounds for Star Wars sounds
* Updates OnBot Java to use a WebSocket for communication with the robot
    * The OnBot Java page no longer has to do a full refresh when a user switches from editing one file to another

Known issues:
* Camera Stream
    * The Vuforia camera stream inherits the issues present in the phone preview (namely [ftc_app issue #574](https://github.com/ftctechnh/ftc_app/issues/574)). This problem does not affect the TFOD camera stream even though it receives frames from Vuforia.
    * The orientation of the stream frames may not always match the phone preview. For now, these frames may be rotated manually via a custom `CameraStreamSource` if desired.
* OnBotJava
    * Browser back button may not always work correctly
    * It's possible for a build to be queued, but not started. The OnBot Java build console will display a warning if this occurs.
    * A user might not realize they are editing a different file if the user inadvertently switches from one file to another since this switch is now seamless. The name of the currently open file is displayed in the browser tab.

## Version 5.0 (built on 19.06.14)
 * Support for the REV Robotics Control Hub.
 * Adds a Java preview pane to the Blocks editor.
 * Adds a new offline export feature to the Blocks editor.
 * Display Wi-Fi channel in Network circle on Driver Station.
 * Adds calibration for Logitech C270
 * Updates build tooling and target SDK.
 * Compliance with Google's permissions infrastructure (Required after build tooling update).
 * Keep Alives to mitigate the Motorola Wi-Fi scanning problem.  Telemetry substitute no longer necessary.
 * Improves Vuforia error reporting.
 * Fixes ftctechnh/ftc_app issues 621, 713.
 * Miscellaneous bug fixes and improvements.

## Version 4.3 (built on 18.10.31)
 * Includes missing TensorFlow-related libraries and files.

## Version 4.2 (built on 18.10.30)
 * Includes fix to avoid deadlock situation with WatchdogMonitor which could result in USB communication errors.
     - Comm error appeared to require that user disconnect USB cable and restart the Robot Controller app to recover.
     - robotControllerLog.txt would have error messages that included the words "E RobotCore: lynx xmit lock: #### abandoning lock:"
 * Includes fix to correctly list the parent module address for a REV Robotics Expansion Hub in a configuration (.xml) file.
     - Bug in versions 4.0 and 4.1 would incorrect list the address module for a parent REV Robotics device as "1".
     - If the parent module had a higher address value than the daisy-chained module, then this bug would prevent the Robot Controller from communicating with the downstream Expansion Hub.
 * Added requirement for ACCESS_COARSE_LOCATION to allow a Driver Station running Android Oreo to scan for Wi-Fi Direct devices.
 * Added google() repo to build.gradle because aapt2 must be downloaded from the google() repository beginning with version 3.2 of the Android Gradle Plugin.
     - Important Note: Android Studio users will need to be connected to the Internet the first time build the ftc_app project.
     - Internet connectivity is required for the first build so the appropriate files can be downloaded from the Google repository.
     - Users should not need to be connected to the Internet for subsequent builds.
     - This should also fix buid issue where Android Studio would complain that it "Could not find com.android.tools.lint:lint-gradle:26.1.4" (or similar).
 * Added support for REV Spark Mini motor controller as part of the configuration menu for a servo/PWM port on the REV Expansion Hub.
 * Provide examples for playing audio files in an Op Mode.
 * Block Development Tool Changes
     - Includes a fix for a problem with the Velocity blocks that were reported in the FTC Technology forum (Blocks Programming subforum).
     - Change the "Save completed successfully." message to a white color so it will contrast with a green background.
     - Fixed the "Download image" feature so it will work if there are text blocks in the op mode.
 * Introduce support for Google's TensorFlow Lite technology for object detetion for 2018-2019 game.
     - TensorFlow lite can recognize Gold Mineral and Silver Mineral from 2018-2019 game.
     - Example Java and Block op modes are included to show how to determine the relative position of the gold block (left, center, right).

## Version 4.1 (released on 18.09.24)

Changes include:
 * Fix to prevent crash when deprecated configuration annotations are used.
 * Change to allow FTC Robot Controller APK to be auto-updated using FIRST Global Control Hub update scripts.
 * Removed samples for non supported / non legal hardware.
 * Improvements to Telemetry.addData block with "text" socket.
 * Updated Blocks sample op mode list to include Rover Ruckus Vuforia example.
 * Update SDK library version number.

## Version 4.0 (released on 18.09.12)

Changes include:
 * Initial support for UVC compatible cameras
    - If UVC camera has a unique serial number, RC will detect and enumerate by serial number.
    - If UVC camera lacks a unique serial number, RC will only support one camera of that type connected.
    - Calibration settings for a few cameras are included (see TeamCode/src/main/res/xml/teamwebcamcalibrations.xml for details).
    - User can upload calibration files from Program and Manage web interface.
    - UVC cameras seem to draw a fair amount of electrical current from the USB bus.
         + This does not appear to present any problems for the REV Robotics Control Hub.
	 + This does seem to create stability problems when using some cameras with an Android phone-based Robot Controller.
	 + FTC Tech Team is investigating options to mitigate this issue with the phone-based Robot Controllers.
    - Updated sample Vuforia Navigation and VuMark Op Modes to demonstrate how to use an internal phone-based camera and an external UVC webcam.

 * Support for improved motor control.
    - REV Robotics Expansion Hub firmware 1.8 and greater will support a feed forward mechanism for closed loop motor control.
    - FTC SDK has been modified to support PIDF coefficients (proportional, integral, derivative, and feed forward).
    - FTC Blocks development tool modified to include PIDF programming blocks.
    - Deprecated older PID-related methods and variables.
    - REV's 1.8.x PIDF-related changes provide a more linear and accurate way to control a motor.

 * Wireless
    - Added 5GHz support for wireless channel changing for those devices that support it.
        + Tested with Moto G5 and E4 phones.
	+ Also tested with other (currently non-approved) phones such as Samsung Galaxy S8.

* Improved Expansion Hub firmware update support in Robot Controller app
    - Changes to make the system more robust during the firmware update process (when performed through Robot Controller app).
    - User no longer has to disconnect a downstream daisy-chained Expansion Hub when updating an Expansion Hub's firmware.
        + If user is updating an Expansion Hub's firmware through a USB connection, he/she does not have to disconnect RS485 connection to other Expansion Hubs.
	+ The user still must use a USB connection to update an Expansion Hub's firmware.
	+ The user cannot update the Expansion Hub firmware for a downstream device that is daisy chained through an RS485 connection.
    - If an Expansion Hub accidentally gets "bricked" the Robot Controller app is now more likely to recognize the Hub when it scans the USB bus.
        + Robot Controller app should be able to detect an Expansion Hub, even if it accidentally was bricked in a previous update attempt.
	+ Robot Controller app should be able to install the firmware onto the Hub, even if if accidentally was bricked in a previous update attempt.

 * Resiliency
    - FTC software can detect and enable an FTDI reset feature that is available with REV Robotics v1.8 Expansion Hub firmware and greater.
        + When enabled, the Expansion Hub can detect if it hasn't communicated with the Robot Controller over the FTDI (USB) connection.
	+ If the Hub hasn't heard from the Robot Controller in a while, it will reset the FTDI connection.
	+ This action helps system recover from some ESD-induced disruptions.
    - Various fixes to improve reliability of FTC software.

 * Blocks
    - Fixed errors with string and list indices in blocks export to java.
    - Support for USB connected UVC webcams.
    - Refactored optimized Blocks Vuforia code to support Rover Ruckus image targets.
    - Added programming blocks to support PIDF (proportional, integral, derivative and feed forward) motor control.
    - Added formatting options (under Telemetry and Miscellaneous categories) so user can set how many decimal places to display a numerical value.
    - Support to play audio files (which are uploaded through Blocks web interface) on Driver Station in addition to the Robot Controller.
    - Fixed bug with Download Image of Blocks feature.
    - Support for REV Robotics Blinkin LED Controller.
    - Support for REV Robotics 2m Distance Sensor.
    - Added support for a REV Touch Sensor (no longer have to configure as a generic digital device).
    - Added blocks for DcMotorEx methods.
        + These are enhanced methods that you can use when supported by the motor controller hardware.
	+ The REV Robotics Expansion Hub supports these enhanced methods.
	+ Enhanced methods include methods to get/set motor velocity (in encoder pulses per second), get/set PIDF coefficients, etc..

 * Modest Improvements in Logging
    - Decrease frequency of battery checker voltage statements.
    - Removed non-FTC related log statements (wherever possible).
    - Introduced a "Match Logging" feature.
        + Under "Settings" a user can enable/disable this feature (it's disabled by default).
	+ If enabled, user provides a "Match Number" through the Driver Station user interface (top of the screen).
	    * The Match Number is used to create a log file specifically with log statements from that particular Op Mode run.
	    * Match log files are stored in /sdcard/FIRST/matlogs on the Robot Controller.
	    * Once an op mode run is complete, the Match Number is cleared.
	    * This is a convenient way to create a separate match log with statements only related to a specific op mode run.

 * New Devices
    - Support for REV Robotics Blinkin LED Controller.
    - Support for REV Robotics 2m Distance Sensor.
    - Added configuration option for REV 20:1 HD Hex Motor.
    - Added support for a REV Touch Sensor (no longer have to configure as a generic digital device).

 * Miscellaneous
    - Fixed some errors in the definitions for acceleration and velocity in our javadoc documentation.
    - Added ability to play audio files on Driver Station
    - When user is configuring an Expansion Hub, the LED on the Expansion Hub will change blink pattern (purple-cyan)  to indicate which Hub is currently being configured.
    - Renamed I2cSensorType to I2cDeviceType.
    - Added an external sample Op Mode that demonstrates localization using 2018-2019 (Rover Ruckus presented by QualComm) Vuforia targets.
    - Added an external sample Op Mode that demonstrates how to use the REV Robotics 2m Laser Distance Sensor.
    - Added an external sample Op Mode that demonstrates how to use the REV Robotics Blinkin LED Controller.
    - Re-categorized external Java sample Op Modes to "TeleOp" instead of "Autonomous".

Known issues:
 * Initial support for UVC compatible cameras
    - UVC cameras seem to draw significant amount of current from the USB bus.
        + This does not appear to present any problems for the REV Robotics Control Hub.
	+ This does seem to create stability problems when using some cameras with an Android phone-based Robot Controller.
	+ FTC Tech Team is investigating options to mitigate this issue with the phone-based Robot Controllers.
    - There might be a possible deadlock which causes the RC to become unresponsive when using a UVC webcam with a Nougat Android Robot Controller.

 * Wireless
    - When user selects a wireless channel, this channel does not necessarily persist if the phone is power cycled.
        + Tech Team is hoping to eventually address this issue in a future release.
	+ Issue has been present since apps were introduced (i.e., it is not new with the v4.0 release).
    - Wireless channel is not currently displayed for Wi-Fi Direct connections.

 * Miscellaneous
    - The blink indication feature that shows which Expansion Hub is currently being configured does not work for a newly created configuration file.
        + User has to first save a newly created configuration file and then close and re-edit the file in order for blink indicator to work.

## Version 3.6 (built on 17.12.18)

Changes include:
 * Blocks Changes
     - Uses updated Google Blockly software to allow users to edit their op modes on Apple iOS devices (including iPad and iPhone).
     - Improvement in Blocks tool to handle corrupt op mode files.
     - Autonomous op modes should no longer get switched back to tele-op after re-opening them to be edited.
     - The system can now detect type mismatches during runtime and alert the user with a message on the Driver Station.
 * Updated javadoc documentation for setPower() method to reflect correct range of values (-1 to +1).
 * Modified VuforiaLocalizerImpl to allow for user rendering of frames
     - Added a user-overrideable onRenderFrame() method which gets called by the class's renderFrame() method.

## Version 3.5 (built on 17.10.30)

Changes with version 3.5 include:
 * Introduced a fix to prevent random op mode stops, which can occur after the Robot Controller app has been paused and then resumed (for example, when a user temporarily turns off the display of the Robot Controller phone, and then turns the screen back on).
 * Introduced a fix to prevent random op mode stops, which were previously caused by random peer disconnect events on the Driver Station.
 * Fixes issue where log files would be closed on pause of the RC or DS, but not re-opened upon resume.
 * Fixes issue with battery handler (voltage) start/stop race.
 * Fixes issue where Android Studio generated op modes would disappear from available list in certain situations.
 * Fixes problem where OnBot Java would not build on REV Robotics Control Hub.
 * Fixes problem where OnBot Java would not build if the date and time on the Robot Controller device was "rewound" (set to an earlier date/time).
 * Improved error message on OnBot Java that occurs when renaming a file fails.
 * Removed unneeded resources from android.jar binaries used by OnBot Java to reduce final size of Robot Controller app.
 * Added MR_ANALOG_TOUCH_SENSOR block to Blocks Programming Tool.

## Version 3.4 (built on 17.09.06)

Changes with version 3.4 include:
 * Added telemetry.update() statement for BlankLinearOpMode template.
 * Renamed sample Block op modes to be more consistent with Java samples.
 * Added some additional sample Block op modes.
 * Reworded OnBot Java readme slightly.

## Version 3.3 (built on 17.09.04)

This version of the software includes improves for the FTC Blocks Programming Tool and the OnBot Java Programming Tool.

Changes with verion 3.3 include:
 * Android Studio ftc_app project has been updated to use Gradle Plugin 2.3.3.
 * Android Studio ftc_app project is already using gradle 3.5 distribution.
 * Robot Controller log has been renamed to /sdcard/RobotControllerLog.txt (note that this change was actually introduced w/ v3.2).
 * Improvements in I2C reliability.
 * Optimized I2C read for REV Expansion Hub, with v1.7 firmware or greater.
 * Updated all external/samples (available through OnBot and in Android project folder).
 * Vuforia
    - Added support for VuMarks that will be used for the 2017-2018 season game.
 * Blocks
    - Update to latest Google Blockly release.
    - Sample op modes can be selected as a template when creating new op mode.
    - Fixed bug where the blocks would disappear temporarily when mouse button is held down.
    - Added blocks for Range.clip and Range.scale.
    - User can now disable/enable Block op modes.
    - Fix to prevent occasional Blocks deadlock.
 * OnBot Java
    - Significant improvements with autocomplete function for OnBot Java editor.
    - Sample op modes can be selected as a template when creating new op mode.
    - Fixes and changes to complete hardware setup feature.
    - Updated (and more useful) onBot welcome message.

Known issues:
 * Android Studio
    - After updating to the new v3.3 Android Studio project folder, if you get error messages indicating "InvalidVirtualFileAccessException" then you might need to do a File->Invalidate Caches / Restart to clear the error.
 * OnBot Java
    - Sometimes when you push the build button to build all op modes, the RC returns an error message that the build failed.  If you press the build button a second time, the build typically suceeds.

## Version 3.2 (built on 17.08.02)

This version of the software introduces the "OnBot Java" Development Tool.  Similar to the FTC Blocks Development Tool, the FTC OnBot Java Development Tool allows a user to create, edit and build op modes dynamically using only a Javascript-enabled web browser.

The OnBot Java Development Tool is an integrated development environment (IDE) that is served up by the Robot Controller.  Op modes are created and edited using a Javascript-enabled browser (Google Chromse is recommended).  Op modes are saved on the Robot Controller Android device directly.

The OnBot Java Development Tool provides a Java programming environment that does NOT need Android Studio.



