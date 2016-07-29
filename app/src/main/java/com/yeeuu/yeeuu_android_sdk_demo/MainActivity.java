package com.yeeuu.yeeuu_android_sdk_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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


    ListView locklv;
    CommonAdapter<Lock> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Yeeuu.init("6b30f1c04d070e4541f65b10d06c17b7930a8279", "559c8867766d315ce8000000");

        findViewById(R.id.getlock).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Yeeuu.getLockList(new LockLisener() {
                    @Override
                    public void onSuccess(List<Lock> lockList) {
                        setlocklist(lockList);
                    }

                    @Override
                    public void onFailed(int errCode, String errMsg) {

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
                viewHolder.setText(R.id.lockid, "门锁ID: " + lock.getId());
                viewHolder.setText(R.id.room, "房间号: " + lock.getRoom());
                viewHolder.getView(R.id.open).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
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
                    }
                });
            }
        };
        locklv.setAdapter(adapter);
    }
}
