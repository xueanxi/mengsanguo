#Create by anxi.xue at 2017-09-06

#这个脚本的作用是自动编译APK和test APK,然后自动签名，自动把两个apk安装到手机上，启动自动化测试，最终打印测试结果并且显示出来。

#使用注意点1 (根据自己的项目需求可以修改以下几个配置)
#包名(根据自己的需求修改)
packageName=com.tct.nowkey
#测试包名(不需要修改)
packageNameTest=$packageName.test
#主界面(根据自己的需求修改)
mainActivity=.NowKeyActivity
#签名之后的apk文件位置(可以不改，直接使用)
installApkPath=/local/release.apk
#签名之后的test apk文件位置(可以不改，直接使用)
installTestApkPath=/local/release_test.apk
#打印测试结果的位置(可以不修改，直接使用)
TestResultPath=/local/TestResult.log

#使用注意点2
#需要把签名包放在指定的位置，可以自己调整，没有这个签名包可以到windows系统的共享路径下获取：\\rdshare\temp\anxi\releasekey
#signApkPath=/local/tools/tcl_platform_release
signApkPath=/local/tools/google_platform_release

#使用注意点3
#为了提高效率，编译apk过程是使用了--offline模式，这就要求使用这个脚本之前需要成功编译一次，以后就可以离线了
#在线编译指令 gradle :app:assembleRelease

currentPath=$(pwd)
releaseApkPath=$currentPath"/app/build/outputs/apk/app-release-unsigned.apk"
testApkPath=$currentPath"/app/build/outputs/apk/app-debug-androidTest.apk"
echo "=== current Path:"$currentPath
echo "=== releaseApkPath Path:"$debugApkPath
echo "=== testApkPath Path:"$testApkPath
echo "=== signApkPath:"$signApkPath

cd $currentPath
echo "=== delete old apk ..."
rm $releaseApkPath
rm $testApkPath

echo "=== build test apk ..."
gradle :app:assembleAndroidTest --offline


cd $signApkPath
echo "=== sign test apk ..."
java -jar signapk.jar platform.x509.pem platform.pk8 $testApkPath  $installTestApkPath 

echo "=== uninstall old apk ..."
adb uninstall $packageNameTest

echo "=== install test apk ..."
adb install $installTestApkPath 

echo "=== Start test ..."
# 启动测试的语法
# 语法部分是固定的，中间部分根据需求调整
# adb shell am instrument -w >>这里中间部分可以插入下面三个的其中一个<< $packageNameTest/android.support.test.runner.AndroidJUnitRunner
# 1.测试所有       -e package $packageName
# 2.测试一个类     -e class 测试类的类名
# 3.测试一个方法   -e class 测试类的类名#测试方法名

#1.测试所有
#adb shell am instrument -w -e package $packageName -e debug false $packageNameTest/android.support.test.runner.AndroidJUnitRunner > $TestResultPath
#2.测试一个类
#adb shell am instrument -w -e class $packageName.ApiTest -e debug false $packageNameTest/android.support.test.runner.AndroidJUnitRunner > $TestResultPath
#3.测试一个方法
adb shell am instrument -w -e class $packageName.FunctionTest#testInsertErrorData -e debug false $packageNameTest/android.support.test.runner.AndroidJUnitRunner > $TestResultPath

gedit $TestResultPath &
cd $currentPath





















