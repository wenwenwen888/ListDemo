# ListDemo
所有Demo仅用于个人测试</br>

一个伟大的项目, 都是源自于一个个小小的demo~

## 1,Socket Demo
一个socket通信的简单demo,支持断开重连</br>
此demo引用了[这里的Socket封装库](https://github.com/vilyever/AndroidSocketClient)并作了自己的业务修改</br>

--------------------------主要文件或代码--------------------------

主文件 : [SocketActivity.java](https://github.com/wenwenwen888/ListDemo/blob/master/app/src/main/java/com/wyt/list/activity/SocketActivity.java)</br>
库文件 : [socketclient](https://github.com/wenwenwen888/ListDemo/tree/master/app/src/main/java/com/wyt/list/socketclient)</br>
两个工具类:</br>
[ClientSocket.java](https://github.com/wenwenwen888/ListDemo/blob/master/app/src/main/java/com/wyt/list/util/ClientSocket.java)</br>
[SingleSocket.java](https://github.com/wenwenwen888/ListDemo/blob/master/app/src/main/java/com/wyt/list/util/SingleSocket.java)</br>




## 2,Unfile Demo
用于解压文件(内含删除整个文件夹的功能)</br>


--------------------------主要文件或代码--------------------------


[UnFileActivity.java](https://github.com/wenwenwen888/ListDemo/blob/master/app/src/main/java/com/wyt/list/activity/UnFileActivity.java)</br>
解压文件库 : [ZipEntry.jar]
(https://github.com/wenwenwen888/ListDemo/blob/master/app/libs/ZipEntry.jar)
</br>
>* 解压文件代码如下:
```Java
    /**
     * 解压缩一个文件
     *
     * @param zipFile    压缩文件
     * @param folderPath 解压缩的目标目录
     * @return 返回解压根目录
     * @throws IOException 当解压缩过程出错时抛出
     */
    public static String upZipFile(File zipFile, String folderPath) throws IOException {
        String FolderPath = null;
        File desDir = new File(folderPath);
        if (!desDir.exists()) {
            desDir.mkdirs();
        }
        ZipFile zf = new ZipFile(zipFile, "GBK");
        for (Enumeration<?> entries = zf.getEntries(); entries.hasMoreElements(); ) {
            ZipEntry entry = ((ZipEntry) entries.nextElement());
            if (entry.isDirectory()) {
                continue;
            }
            InputStream in = zf.getInputStream((com.file.zip.ZipEntry) entry);
            String str = folderPath + File.separator + entry.getName();
            FolderPath = folderPath + File.separator + entry.getName().substring(0, entry.getName().indexOf("/"));//返回的根目录
            Log.e("upZipFileToTFcard", "FolderPath:" + entry.getName());
            str = new String(str.getBytes(), "utf-8");
            File desFile = new File(str);
            if (!desFile.exists()) {
                File fileParentDir = desFile.getParentFile();
                if (!fileParentDir.exists()) {
                    Log.e("upZipFileToTFcard", "fileParentDir:" + fileParentDir.getPath());
                    fileParentDir.mkdirs();
                }
                Log.e("upZipFileToTFcard" , "desFile:"+desFile.getPath());
                desFile.createNewFile();
            }
            OutputStream out = new FileOutputStream(desFile);
            byte buffer[] = new byte[BUFF_SIZE];
            int realLength;
            while ((realLength = in.read(buffer)) > 0) {
                out.write(buffer, 0, realLength);
            }
            in.close();
            out.close();
        }
        return FolderPath;
    }
```
>* 删除整个文件夹文件代码如下
```java
    /**
     * 递归删除文件和文件夹
     * @param file    要删除的根目录
     */
    public static void RecursionDeleteFile(File file){
        if(file.isFile()){
            file.delete();
            return;
        }
        if(file.isDirectory()){
            File[] childFile = file.listFiles();
            if(childFile == null || childFile.length == 0){
                file.delete();
                return;
            }
            for(File f : childFile){
                RecursionDeleteFile(f);
            }
            file.delete();
        }
    }
```

## 3,Timer Demo
一个简单的倒计时demo</br>


--------------------------主要文件或代码--------------------------


[TimerActivity.java](https://github.com/wenwenwen888/ListDemo/blob/master/app/src/main/java/com/wyt/list/activity/TimerActivity.java)</br>

## 4,MultiMedia Demo
有关多媒体Demo(暂时有拍照与从相册选照片)</br>


--------------------------主要文件或代码--------------------------


[MultiMediaActivity.java](https://github.com/wenwenwen888/ListDemo/blob/master/app/src/main/java/com/wyt/list/activity/MultiMediaActivity.java)</br>

## 5,DrawerLayout Demo
官方控件DrawerLayout的简单使用</br>


--------------------------主要文件或代码--------------------------


[DrawerLayoutActivity.java](https://github.com/wenwenwen888/ListDemo/blob/master/app/src/main/java/com/wyt/list/activity/DrawerLayoutActivity.java)</br>
[activity_drawerlayout.xml](https://github.com/wenwenwen888/ListDemo/blob/master/app/src/main/res/layout/activity_drawerlayout.xml)</br>


## 6,CustomProgressBar Demo
一个自定义进度条</br>
>* 示例图 : </br>
<img src="https://github.com/wenwenwen888/ListDemo/blob/master/preview/2.png" width="50%" height="50%"></br>


--------------------------主要文件或代码--------------------------


</br>
activity_customprogressbar.xml:
```java
    <ProgressBar
        android:id="@+id/progressBar"
        style="@style/StyleProgressBarMini"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_margin="10dp"
        android:background="@drawable/shape_progressbar_bg"
        android:max="100"
        android:progress="50" />
```
style.xml:
```java
      <style name="StyleProgressBarMini" parent="@android:style/Widget.ProgressBar.Horizontal">
        <item name="android:maxHeight">50dip</item>
        <item name="android:minHeight">10dip</item>
        <item name="android:indeterminateOnly">false</item>
        <item name="android:indeterminateDrawable">@android:drawable/progress_indeterminate_horizontal</item>
        <item name="android:progressDrawable">@drawable/shape_progressbar_mini</item>
    </style>
```
[shape_progressbar_mini.xml](https://github.com/wenwenwen888/ListDemo/blob/master/app/src/main/res/drawable/shape_progressbar_mini.xml)</br>
[shape_progressbar_bg.xml](https://github.com/wenwenwen888/ListDemo/blob/master/app/src/main/res/drawable/shape_progressbar_bg.xml)</br>


## 7,MPAndroidLineChart Demo
基于MPAndroidChart的一个折线图</br>
>* 示例图 : </br>
<img src="https://github.com/wenwenwen888/ListDemo/blob/master/preview/1.png" width="50%" height="50%">

[这里是MPAndroidChart的地址](https://github.com/PhilJay/MPAndroidChart)</br>


--------------------------主要文件或代码--------------------------


[MPAndroidLineChartActivity.java](https://github.com/wenwenwen888/ListDemo/blob/master/app/src/main/java/com/wyt/list/activity/MPAndroidLineChartActivity.java)</br>

## 8,EventBus Demo
EventBus的简单使用</br>
[这里是EventBus的地址](https://github.com/greenrobot/EventBus)</br>


--------------------------主要文件或代码--------------------------


[EventBusActivity.java](https://github.com/wenwenwen888/ListDemo/blob/master/app/src/main/java/com/wyt/list/activity/EventBusActivity.java)</br>
[EventBusSecondActivity.java](https://github.com/wenwenwen888/ListDemo/blob/master/app/src/main/java/com/wyt/list/activity/EventBusSecondActivity.java)</br>

## 9,Realm Demo
Realm数据库的简单使用</br>
[Realm for Android详细教程](http://www.jianshu.com/p/28912c2f31db)</br>


--------------------------主要文件或代码--------------------------


[RealmActivity.java](https://github.com/wenwenwen888/ListDemo/blob/master/app/src/main/java/com/wyt/list/activity/RealmActivity.java)</br>

## 10,ScreenShot Demo
一个简单的截屏工具</br>
[这里是截屏库的地址](https://github.com/tarek360/InstaCapture)</br>


--------------------------主要文件或代码--------------------------


[ScreenShotActivity.java](https://github.com/wenwenwen888/ListDemo/blob/master/app/src/main/java/com/wyt/list/activity/ScreenShotActivity.java)</br>

## 11,Drawing Demo
一个画板(可以弹出颜色选择器选择画笔颜色)</br>
[这里是画板的地址](https://github.com/vilyever/AndroidDrawingView)</br>
[这里是颜色选择器的地址](https://github.com/gzu-liyujiang/AndroidPicker)</br>


--------------------------主要文件或代码--------------------------


[DrawingActivity.java](https://github.com/wenwenwen888/ListDemo/blob/master/app/src/main/java/com/wyt/list/activity/DrawingActivity.java)</br>

## 12,Notification Demo
Notification广播简单使用</br>
[这里是引用notification库的地址](https://github.com/halysongoncalves/Pugnotification)</br>


--------------------------主要文件或代码--------------------------


```
PugNotification.with(context)
                .load()
                .title(title)       //title
                .message(message)   //content
                .smallIcon(R.drawable.ic_notifications)     //smallicon
                .largeIcon(R.drawable.ic_notifications)     //largeicon
                .flags(Notification.DEFAULT_ALL)
                .autoCancel(true)           //点击取消
                .simple()
                .build();
```
</br>

## 13,CustomSearchEditText Demo
一个自定义搜索框</br>
[这里是自定义搜索框的地址](https://github.com/wenwenwen888/SearchEditTextDemo)</br>

## 14,Spinner Demo(nice-spinner)
一个自定义的spinner</br>
[这里是nice_spinner的开源地址](https://github.com/arcadefire/nice-spinner)</br>


--------------------------主要文件或代码--------------------------


[SpinnerActivity.java](https://github.com/wenwenwen888/ListDemo/blob/master/app/src/main/java/com/wyt/list/activity/SpinnerActivity.java)</br>
```
<org.angmarch.views.NiceSpinner
        android:id="@+id/nicespinner"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_margin="10dp"
        app:backgroundSelector="@drawable/gray_border_bg"
        app:textTint="@color/colorBlack" />
```
