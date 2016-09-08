package com.yeeuu.yeeuu_android_sdk_demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.yeeuu.yeeuusdk.Lock;
import com.yeeuu.yeeuusdk.LockLisener;
import com.yeeuu.yeeuusdk.OpenLisener;
import com.yeeuu.yeeuusdk.Yeeuu;
import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText tokenet;
    ListView locklv;
    CommonAdapter<Lock> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tokenet = (EditText) findViewById(R.id.edit_token);
        findViewById(R.id.getlock).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String token = tokenet.getText().toString();
                Yeeuu.init(token, null);
                Yeeuu.getLockList(new LockLisener() {
                    @Override
                    public void onSuccess(List<Lock> lockList) {
                        Toast.makeText(MainActivity.this, "获取成功", Toast.LENGTH_SHORT).show();
                        if (lockList.isEmpty())
                            Toast.makeText(MainActivity.this, "该用户无门锁", Toast.LENGTH_SHORT).show();
                        else
                            setlocklist(lockList);
                    }

                    @Override
                    public void onFailed(int errCode, String errMsg) {
                        Toast.makeText(MainActivity.this, "获取门锁列表失败", Toast.LENGTH_SHORT).show();
                        Log.e("获取门锁列表失败  " + errCode, errMsg);
                    }
                });
            }
        });
    }

    private void setlocklist(List<Lock> lockList) {
        locklv = (ListView) findViewById(R.id.locklist);
        adapter = new CommonAdapter<Lock>(this, R.layout.lock_item, lockList) {
            @Override
            protected void convert(ViewHolder viewHolder, final Lock lock, int position) {
                viewHolder.setText(R.id.hotel, "酒店名: " + lock.getHotel());
                viewHolder.setText(R.id.room, "房间号: " + lock.getRoom());
                viewHolder.getView(R.id.open).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Yeeuu.OpenLock(lock.getId(), new OpenLisener() {
                            @Override
                            public void onSucc() {
                                Toast.makeText(MainActivity.this, "开门成功", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFail(int errCode, String errMsg) {
                                Toast.makeText(MainActivity.this, "开门失败", Toast.LENGTH_SHORT).show();
                                Log.e("开门失败  " + errCode, errMsg);
                            }
                        });
                    }
                });
            }
        };
        locklv.setAdapter(adapter);
    }
}
