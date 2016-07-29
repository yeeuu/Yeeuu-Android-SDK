# Yeeuu SDK for Android

### 1、配置SDK
+ 下载jar并添加到工程

[下载SDK](https://github.com/yeeuu/Yeeuu-Android-SDK/blob/master/yeeuuSDK/yeeuu-sdk.jar?raw=true)

+ manifest添加网络权限

`
<uses-permission android:name="android.permission.INTERNET"/>
`

### 2、使用方法

```java
//初始化
Yeeuu.init("token", "hotel_id");

//获取门锁列表
Yeeuu.getLockList(new LockLisener() {
    @Override
    public void onSuccess(List<Lock> lockList) {

    }

    @Override
    public void onFailed(int errCode, String errMsg) {

    }
});

//开门操作
Yeeuu.OpenLock(lock.getId(), new OpenLisener() {
    @Override
    public void onSucc() {
        Toast.makeText(MainActivity.this, "开门成功"
                , Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFail(int errCode, String errMsg) {
        Toast.makeText(MainActivity.this, "开门失败: " + errMsg
                , Toast.LENGTH_SHORT).show();
    }
});
```

### 门锁类

```java
public class Lock {

    private String id;  // 门锁ID
    private String hotel;  // 酒店名称
    private String room;  // 房间名
    private int start;  // 授权开始时间
    private int end;  // 授权结束时间
    private boolean status;  // 是否门锁在线
    private int count;  // 开门次数
    private int total;  // 总可用开门次数
    private boolean is_password_admin;  // 是否密码门锁管理员
    private boolean is_password_lock;  // 是否密码门锁
    private int password_type;  // 密码类型
    private boolean is_set_pass;  // 是否设置密码
    private int disabled;  // 是否禁用
}
```