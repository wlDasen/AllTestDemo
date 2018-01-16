package net.george.alltestdemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import net.george.alltestdemo.binder.MyBinder;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * @author George
 * @date 2018/1/16
 * @email georgejiapeidi@gmail.com
 * @describe Android四大组件之ServiceActivity
 */
public class ServiceActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "jpd-ServiceAc";
    @Bind(R.id.start_service)
    Button startService;
    @Bind(R.id.stop_service)
    Button stopService;
    @Bind(R.id.bind_service)
    Button bindService;
    @Bind(R.id.unbind_service)
    Button unbindService;
    private MyBinder myBinder;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(TAG, "onServiceConnected: ");
            myBinder = (MyBinder)iBinder;
            myBinder.startDownload();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(TAG, "onServiceDisconnected: ");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        ButterKnife.bind(this);
        startService.setOnClickListener(this);
        stopService.setOnClickListener(this);
        bindService.setOnClickListener(this);
        unbindService.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start_service:
                Intent startService = new Intent(ServiceActivity.this, MyService.class);
                startService(startService);
                break;
            case R.id.stop_service:
                Intent stopService = new Intent(ServiceActivity.this, MyService.class);
                stopService(stopService);
                break;
            case R.id.bind_service:
                Intent bindIntent = new Intent(ServiceActivity.this, MyService.class);
                bindService(bindIntent, connection, BIND_AUTO_CREATE);
                break;
            case R.id.unbind_service:
                unbindService(connection);
                break;
        }
    }
}
