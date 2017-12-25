package net.george.alltestdemo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * @author George
 * @date 2017/12/25
 * @email georgejiapeidi@gmail.com
 * @describe 各种自定义Widget Activity
 */
public class CustomWidgetActivity extends AppCompatActivity {
    private static final String TAG = "jpd-CWAc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_widget);

        customDialogTest();
    }

    /**
     * 自定义Dialog测试接口
     */
    private void customDialogTest() {
        View view = LayoutInflater.from(this).inflate(R.layout.custom_widget_dialog_layout, null);
        TextView text = (TextView)view.findViewById(R.id.cw_text_view);
        text.setText("请稍等");
        CustomDialog customDialog = new CustomDialog(this, view, R.style.CustomDialogTheme);
        customDialog.show();
        customDialog.setCancelable(false);
    }
    /**
     * 系统自带的Diaglog用法
     */
    private void normalDialogTest() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("提示");
        builder.setMessage("AAAAAAAA");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setNeutralButton("忽略", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.setCancelable(false);
        builder.show();
    }

    public class CustomDialog extends Dialog {

        public CustomDialog(@NonNull Context context, View view, int themeResId) {
            super(context, themeResId);
            setContentView(view);
            Window window = getWindow();
            WindowManager.LayoutParams params = window.getAttributes();
            params.gravity = Gravity.CENTER;
            window.setAttributes(params);
        }
    }
}