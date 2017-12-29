package net.george.alltestdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.JsonWriter;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.StringReader;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.List;

/**
 * @author George
 * @date 2017/12/28
 * @email georgejiapeidi@gmail.com
 * @describe Google Gson验证Activity
 */
public class GsonActivity extends AppCompatActivity {
    private static final String TAG = "jpd-GsonAc";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gson);

        test10();
    }

    /**
     * 基本数据类型反序列化
     */
    private void test1() {
        Gson gson = new Gson();
        int i = gson.fromJson("100", int.class);
        double d = gson.fromJson("99.99", double.class);
        double d2 = gson.fromJson("\"99.99\"", double.class);
        boolean b = gson.fromJson("true", boolean.class);
        String str = gson.fromJson("String", String.class);

        Log.d(TAG, "test1: i:" + i + ",d:" + d + ",d2:" + d2
            + ",b:" + b + ",str:" + str);
    }

    /**
     * 基本数据类型序列化
     */
    private void test2() {
        Gson gson = new Gson();
        String jsonNumber = gson.toJson(100);
        String jsonBoolean = gson.toJson(true);
        String jsonString = gson.toJson("String");

        Log.d(TAG, "test2: jsonNumber:" + jsonNumber + ",jsonBoolean:" + jsonBoolean + ",jsonString:" + jsonString);
    }

    /**
     * 对象的序列化
     */
    private void test3() {
        Gson gson = new Gson();
        User user = new User("George", 29);
        String jsonObject = gson.toJson(user);
        Log.d(TAG, "test3: jsonObject:" + jsonObject);
    }

    /**
     * 对象的反序列化
     */
    private void test4() {
        Gson gson = new Gson();
        String jsonString = "{\"name\":\"怪盗kidou\",\"age\":24}";
        User user = gson.fromJson(jsonString, User.class);
        Log.d(TAG, "test4: user:" + user);
    }

    /**
     * @SerializedName注解
     */
    private void test5() {
        Gson gson = new Gson();
        String jsonString = "{\"name\":\"怪盗kidou\",\"age\":24,\"email_address\":\"ikidou@example.com\"}";
        User user = gson.fromJson(jsonString, User.class);
        Log.d(TAG, "test5: user:" + user);
    }
    /**
     * @SerializedName注解设置备选属性名
     */
    private void test6() {
        Gson gson = new Gson();
        String jsonString = "{\"name\":\"怪盗kidou\",\"age\":24,\"email_address\":\"ikidou@example.com\"}";
        String jsonString2 = "{\"name\":\"怪盗kidou\",\"age\":24,\"emailAddress\":\"ikidou2@example.com\"}";
        String jsonString3 = "{\"name\":\"怪盗kidou\",\"age\":24,\"email\":\"ikidou3@example.com\"}";
        User user = gson.fromJson(jsonString, User.class);
        User user2 = gson.fromJson(jsonString2, User.class);
        User user3 = gson.fromJson(jsonString3, User.class);
        Log.d(TAG, "test6: user:" + user);
        Log.d(TAG, "test6: user2:" + user2);
        Log.d(TAG, "test6: user3:" + user3);
    }

    /**
     * 使用TypeToken实现对泛型的支持
     */
    private void test7() {
        Gson gson = new Gson();
        String jsonArray = "[\"Android\",\"Java\",\"PHP\"]";
        String[] array = gson.fromJson(jsonArray, String[].class);
        List<String> list = gson.fromJson(jsonArray, new TypeToken<List<String>>() {}.getType());
        for (String s : list) {
            Log.d(TAG, "test7: s:" + s);
        }
    }

    /**
     * 不同Json的同一类封装测试接口
     * {"code":"0","message":"success","data":{}}
     * {"code":"0","message":"success","data":[]}
     */
    private void test8() {
        Gson gson = new Gson();
        String jsonString = "{\"code\":\"0\", \"message\":\"success\", \"data\":{\"name\":\"怪盗kidou\",\"age\":24,\"email_address\":\"ikidou@example.com\"}}";
        String jsonString2 = "{\"code\":\"0\", \"message\":\"success\", \"data\":[{\"name\":\"怪盗kidou\",\"age\":24,\"email_address\":\"ikidou@example.com\"},{\"name\":\"怪盗kidou2\",\"age\":24,\"email_address\":\"ikidou@example.com\"}]}";
        Type userType = new TypeToken<Result<User>>() {}.getType();
        Result<User> userResult = gson.fromJson(jsonString, userType);
        Log.d(TAG, "test8: userResult:" + userResult);

        Type listType = new TypeToken<Result<List<User>>>() {}.getType();
        Result<List<User>> listResult = gson.fromJson(jsonString2, listType);
        Log.d(TAG, "test8: listResult:" + listResult);
    }

    /**
     * 流式反序列化
     */
    private void test9() {
        String json = "{\"name\":\"怪盗kidou\",\"age\":24}";
        User user = new User();
        JsonReader jsonReader = new JsonReader(new StringReader(json));
        try {
            jsonReader.beginObject();
            while (jsonReader.hasNext()) {
                String s = jsonReader.nextName();
                switch (s) {
                    case "name":
                        user.name = jsonReader.nextString();
                        break;
                    case "age":
                        user.age = jsonReader.nextInt();
                        break;
                }
            }
            jsonReader.endObject();
            Log.d(TAG, "test9: user:" + user);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 流式序列化
     */
    private void test10() {
        Gson gson = new Gson();
        User user = new User("George", 29, "sunniwell.net");
        gson.toJson(user, System.out);
        System.out.println("AAAAAAAAAAAAAAAAAAAAAa");
        JsonWriter jsonWriter = new JsonWriter(new OutputStreamWriter(System.out));
        try {
            jsonWriter.beginObject()
                    .name("name").value("George")
                    .name("age").value(29)
                    .name("email").value("sunniwell.net")
                    .endObject();
            jsonWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 不同Json封装的测试类
     * @param <T>
     */
    public class Result<T> {
        private String code;
        private String message;
        private T data;

        @Override
        public String toString() {
            return "[Result]code:" + code + ",message:" + message + ",data:" + data;
        }
    }
    /**
     * 对象序列化和反序列化的测试类
     */
    public class User {
        private String name;
        private int age;
//        @SerializedName("email_address")
        @SerializedName(value = "emailAddress", alternate = {"email", "email_address"})
        private String emailAddress;
        public User() {}
        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }public User(String name, int age, String emailAddress) {
            this(name, age);
            this.emailAddress = emailAddress;
        }


        public String getEmailAddress() {
            return emailAddress;
        }

        public void setEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "[User]name:" + name + ",age:" + age + ",emailAddress:" + emailAddress;
        }
    }
}
