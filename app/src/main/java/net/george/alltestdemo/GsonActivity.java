package net.george.alltestdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.util.JsonWriter;
import android.util.Log;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.Since;
import com.google.gson.annotations.Until;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.StringReader;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
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

        test17();
    }
    // 如下是GsonBuilder基本使用
    /**
     * GsonBuilder:改变Gson的默认配置
     */
    private void test11() {
        Gson gson = new GsonBuilder()
                // 序列化null
                .serializeNulls()
                // 设置日期格式 在序列化和反序列化均生效
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
        Gson gson2 = new Gson();
        User user = new User("George", 29);
        String jsonNull = gson.toJson(user);
        Log.d(TAG, "test11: jsonNull:" + jsonNull);

        User user2 = new User("George2", 29, "sunniwell", new Date());
        String jsonDate = gson.toJson(user2);
        Log.d(TAG, "test11: jsonDate:" + jsonDate);

        Gson gson3 = new GsonBuilder()
                // 禁止序列化内部类
                .disableInnerClassSerialization()
                .create();
        User user3 = new User("George3", 29, "Inner", 30);
        String jsonWithInner = gson2.toJson(user3);
        String jsonWithoutInner = gson3.toJson(user3);
        Log.d(TAG, "test11: jsonWithInner:" + jsonWithInner);
        Log.d(TAG, "test11: jsonWithoutInner:" + jsonWithoutInner);
    }

    // 以下是4种排除字段的方式
    /**
     * @Expose注解测试接口
     */
    private void test12() {
        Category child1 = new Category();
        child1.id = 1;
        child1.name = "1";
        Category child2 = new Category();
        child2.id = 2;
        child2.name = "2";
        Category parent = new Category();
        parent.id = 111;
        parent.name = "111";
        List<Category> list = new ArrayList<>();
        list.add(child1);
        list.add(child2);
        Category category = new Category(222, "222", list, parent);
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        Log.d(TAG, "test12: " + gson.toJson(category));
    }

    /**
     * @Since @Until注解测试接口
     */
    private void test13() {
        SinceUntilSample sus1 = new SinceUntilSample();
        sus1.since = "since";
        sus1.until = "until";
        sus1.test = "test";
        Gson gson = new GsonBuilder().setVersion(4).create();
        Log.d(TAG, "test13: " + gson.toJson(sus1));
    }

    /**
     * 基于修饰符来决定是否导出某些字段的测试接口
     */
    private void test14() {
        ModifierSample sample = new ModifierSample();
        Gson gson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.FINAL, Modifier.PUBLIC, Modifier.PRIVATE).create();
        Log.d(TAG, "test14: " + gson.toJson(sample));
    }

    /**
     * 自定义策略测试接口
     */
    private void test15() {
        Gson gson = new GsonBuilder().addSerializationExclusionStrategy(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
//                Log.d(TAG, "shouldSkipField: name:" + f.getName());
//                if ("finalField".equals(f.getName())) return true;
//                Expose expose = f.getAnnotation(Expose.class);
//                if (expose != null && !expose.serialize()) return true;
                return false;
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                Log.d(TAG, "shouldSkipClass: clazz:" + clazz);
                boolean flag = (clazz == int.class);
                Log.d(TAG, "shouldSkipClass: flag:" + flag);
                return flag;
            }
        }).create();
        Gson gson2 = new Gson();
        CustomStrategy customStrategy = new CustomStrategy();
        customStrategy.age = 1;
        customStrategy.height = 2;
        customStrategy.tall = 3;
        customStrategy.aaaField = 4;
        customStrategy.finalField = 5;
        customStrategy.aaa = 1.1f;
        Log.d(TAG, "test15: gson:" + gson.toJson(customStrategy));
        Log.d(TAG, "test15: gson2:" + gson2.toJson(customStrategy));
    }

    /**
     * 自定义TypeAdapter测试接口
     */
    private void test16() {
        Gson gson = new GsonBuilder().registerTypeAdapter(User2.class, new UserTypeAdapter()).create();
        User2 user2 = new User2("AAA", 20, "BBB");
        Log.d(TAG, "test16: " + gson.toJson(user2));
    }

    /**
     * JsonSerializer与JsonDeserializer实现单向接管
     */
    private void test17() {
        Gson gson = new GsonBuilder().registerTypeAdapter(Integer.class, new JsonDeserializer<Integer>() {
            @Override
            public Integer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                try {
                    return json.getAsInt();
                } catch (NumberFormatException e) {
                    return -1;
                }
            }
        }).create();
        Log.d(TAG, "test17: " + gson.toJson(100));
        Log.d(TAG, "test17: " + gson.fromJson("\"\"", Integer.class));
    }

    // 如下是Gson基本使用
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
        private Date date;
        private InnerClass innerClass;
        public User() {}
        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }
        public User(String name, int age, String emailAddress) {
            this(name, age);
            this.emailAddress = emailAddress;
        }
        public User(String name, int age, String emailAddress, Date date) {
            this(name, age, emailAddress);
            this.date = date;
        }
        public User(String name, int age, String innerName, int innerAge) {
            this.name = name;
            this.age = age;
            this.innerClass = new InnerClass();
            this.innerClass.setName(innerName);
            this.innerClass.setAge(innerAge);
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

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public InnerClass getInnerClass() {
            return innerClass;
        }

        public void setInnerClass(InnerClass innerClass) {
            this.innerClass = innerClass;
        }

        public class InnerClass {
            private String name;
            private int age;

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
                return "[InnerClass]name:" + name + ",age:" + age;
            }
        }

        @Override
        public String toString() {
            return "[User]name:" + name + ",age:" + age + ",emailAddress:" + emailAddress + ",date:" + date + ",innerClass:" + innerClass;
        }
    }

    /**
     * @Expose注解测试类
     */
    public class Category {
        // @Expose:屏蔽某些字段的序列化和反序列化，不加此注解的不导出
        @Expose public int id;
        @Expose public String name;
        @Expose public List<Category> children;
        private Category parent;
        public Category() {}
        public Category(int id, String name, List<Category> children, Category parent) {
            this.id = id;
            this.name = name;
            this.children = children;
            this.parent = parent;
        }

        @Override
        public String toString() {
            StringBuffer stringBuffer = new StringBuffer();
            for (Category child : children) {
                stringBuffer.append(child);
            }
            stringBuffer.append("[Category]id:" + id + ",name:" + name + ",parent:" + parent);
            return stringBuffer.toString();
        }
    }

    /**
     * @Since @Until注解测试类
     * 导出GsonBuilder.setVersion >= Since && < Until，如果同时有2个注解，需要同时满足
     */
    public class SinceUntilSample {
        @Since(4)
        public String since;
        @Until(6)
        public String until;
        @Since(5)
        @Until(6)
        public String test;
    }

    public class User2 {
        public String name;
        public int age;
        public String email;

        public User2() {}
        public User2(String name, int age, String email) {
            this.name = name;
            this.age = age;
            this.email = email;
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

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }

    /**
     * 自定义TypeAdapter测试类（序列化和反序列化都必须接管）
     * 当我们为User.class 注册了 TypeAdapter之后，只要是操作User.class 那些之前介绍的@SerializedName 、FieldNamingStrategy、Since、Until、Expos通通都黯然失色，失去了效果，
     * 只会调用我们实现的UserTypeAdapter.write(JsonWriter, User) 方法，我想怎么写就怎么写。
     */
    public class UserTypeAdapter extends TypeAdapter<User2> {

        @Override
        public void write(com.google.gson.stream.JsonWriter out, User2 value) throws IOException {
            out.beginObject();
            out.name("name").value(value.getName())
                    .name("age").value(value.getAge())
                    .name("email").value(value.getEmail());
            out.endObject();
        }

        @Override
        public User2 read(com.google.gson.stream.JsonReader in) throws IOException {
            User2 user2 = new User2();
            in.beginObject();
            while (in.hasNext()) {
                switch (in.nextName()) {
                    case "name":
                        user2.name = in.nextString();
                        break;
                    case "age":
                        user2.age = in.nextInt();
                        break;
                    case "email":
                    case "email_address":
                    case "emailAddress":
                        user2.email = in.nextString();
                        break;
                }
            }
            return user2;
        }
    }
}

/**
 * 基于修饰符来决定是否导出某些字段
 */
class ModifierSample {
    final String finalField = "final";
    static String staticField = "static";
    public String publicField = "public";
    protected String proctectedField = "proctected";
    String defaultField = "default";
    private String privateField = "private";
}

/**
 * 自定义策略测试类
 */
class CustomStrategy {
    public int age;
    public Integer height;
    @Expose public int tall;
    public int finalField;
    public int aaaField;
    public float aaa;
}
