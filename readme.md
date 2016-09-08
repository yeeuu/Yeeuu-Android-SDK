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

    }

    @Override
    public void onFail(int errCode, String errMsg) {

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


### 异常信息

```
HOST_NOT_ONLINE      YeeBox不在线

OPERATION_TOO_FREQUENT     操作太频繁

LOCK_NOT_EXIST      门锁不存在

LOCK_NOT_ONLINE      门锁不在线

HOST_NOT_EXIST      YeeBox不存在

HOST_NOT_LOGIN      YeeBox未登录

CONFIG_SERVER_DOWN      服务器异常

AUTH_FAIL      无效的认证参数

TIME OUT      请求超时
```