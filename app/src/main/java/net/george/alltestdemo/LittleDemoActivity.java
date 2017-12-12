package net.george.alltestdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 比较小的Demo验证Activity
 * Created at 2017.12.11 By George
 */
public class LittleDemoActivity extends AppCompatActivity {
    private static final String TAG = "jpd-LDAc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_little_demo);

        test1();
    }

    /**
     * 测试泛型擦除接口
     */
    List<String> mList = new ArrayList<>();
    private void test1() {
        ArrayList<String> list1 = new ArrayList<>();
        list1.add("aaa");
        ArrayList<Integer> list2 = new ArrayList<>();
        list2.add(111);
        boolean flag = list1.getClass() == list2.getClass();
        Log.d(TAG, "test1: flag:" + flag);
        Log.d(TAG, "test1: list1:" + list1.getClass());
        try {
            // 方法1-利用反射将String类型添加到Integer类型的ArrayList中
            list2.getClass().getDeclaredMethod("add", Object.class).invoke(list2, "aaaa");
            for (int i = 0; i < list2.size(); i++) {
                Log.d(TAG, "test1: list:" + list2.get(i));
            }
            // 方法2-利用反射将this添加到String类型的ArrayList中
            mList.add("AA");
            Field field = getClass().getDeclaredField("mList");
            List list = (List)field.get(this);
            list.add(this);
            Log.d(TAG, "test1: size:" + mList.size());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 不指定泛型
        int i = LittleDemoActivity.add(1, 2);
        Number f = LittleDemoActivity.add(1, 1.2);
        Object o = LittleDemoActivity.add(1, "asd");
        // 指定泛型
        int a = LittleDemoActivity.<Integer>add(1, 2);
        // 编译错误
//        int b = LittleDemoActivity.<Integer>add(1, 2.2);
        Number c = LittleDemoActivity.<Number>add(1, 1.2);

        // 不指定泛型的时候，泛型类型为Object
        ArrayList arrayList = new ArrayList();
        arrayList.add(1);
        arrayList.add("12");
        arrayList.add(new Date());

        // 真正检查类型的是引用
        ArrayList<String> arrayList1 = new ArrayList();
        arrayList1.add("11");
        // 编译报错
//        arrayList1.add(1);
        String str1 = arrayList1.get(0);
        Log.d(TAG, "test1: str1:" +str1.getClass());

        ArrayList arrayList2 = new ArrayList<String>();
        arrayList2.add("11");
        arrayList2.add(1);
        Object object = arrayList2.get(0);
        Log.d(TAG, "test1: object:" + object.getClass());

        new ArrayList<String>().add("11");
        // 编译错误
//        new ArrayList<String>().add(22);
    }

    /**
     * 为泛型擦除接口测试准备的静态方法
     */
    public static <T> T add(T x, T y) {
        return y;
    }
}