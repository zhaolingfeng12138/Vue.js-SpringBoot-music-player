package com.leth.playmanageserver.Configs;

import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class ResultJson {
    //响应码
    public int code;
    //数据
    public Object data;
    //数据
    public Object data1;
    public Object data2;
    public Object data3;
    public Object data4;

    //书籍
    public String name;
    public String text1;
    public String text2;
    public String url;

    //消息
    public String msg;

    public static ResultJson error(Object data) {
        return ResultJson.builder().code(500).data(data).msg("网络错误!").build();
    }

    public static ResultJson error() {
        return ResultJson.builder().code(500).msg("网络错误!").build();
    }

    public static ResultJson success(Object data) {
        return ResultJson.builder().code(200).data(data).msg("请求成功!").build();
    }

    public static ResultJson success() {
        return ResultJson.builder().code(200).msg("请求成功!").build();
    }

    //类型查询
    public static ResultJson successType(Object data1,Object data2,Object data3,Object data4) {
        return ResultJson.builder().code(200).data1(data1).data2(data2).data3(data3).data4(data4).msg("请求成功!").build();
    }

    public static ResultJson errorType(Object data1,Object data2,Object data3,Object data4) {
        return ResultJson.builder().code(500).data1(data1).data2(data2).data3(data3).data4(data4).msg("网络错误!").build();
    }

    //返回书籍
    public static ResultJson successBook(String name,String text1,String text2,String url) {
        return ResultJson.builder().code(200).name(name).text1(text1).text2(text2).url(url).msg("请求成功!").build();
    }

    public static ResultJson errorBook(String name,String text1,String text2) {
        return ResultJson.builder().code(500).msg("网络错误!").build();
    }

}


/*这段代码定义了一个名为ResultJson的Java类，用于构建HTTP响应的JSON格式数据。让我们来逐个解释这个类的各个部分：

package com.leth.playmanageserver.Configs;: 这是Java包的声明，表明ResultJson类位于com.leth.playmanageserver.Configs包下。

import lombok.Builder; 和 import lombok.ToString;: 这两行是导入Lombok库中的注解。@Builder注解用于生成构建器模式的代码，而@ToString注解用于生成toString()方法的代码。

public class ResultJson {: 这是ResultJson类的定义，它包含了一些字段和静态方法。

public int code;: 定义了一个名为code的整数型字段，用于表示HTTP响应的状态码。

public Object data;: 定义了一个名为data的对象型字段，用于存储HTTP响应的数据。

public Object data1;, public Object data2;, public Object data3;, public Object data4;: 定义了四个额外的对象型字段，用于存储更多的数据。

public String name;, public String text1;, public String text2;, public String url;: 定义了四个字符串型字段，用于存储书籍相关信息。

public String msg;: 定义了一个名为msg的字符串型字段，用于存储HTTP响应的消息。

下面是一些静态方法，用于构建不同类型的ResultJson对象：

error(Object data): 构建一个表示网络错误的ResultJson对象，可以附带数据。
error(): 构建一个表示网络错误的ResultJson对象。
success(Object data): 构建一个表示请求成功的ResultJson对象，可以附带数据。
success(): 构建一个表示请求成功的ResultJson对象。
successType(Object data1,Object data2,Object data3,Object data4): 构建一个表示请求成功，并包含多个数据的ResultJson对象。
errorType(Object data1,Object data2,Object data3,Object data4): 构建一个表示网络错误，并包含多个数据的ResultJson对象。
successBook(String name,String text1,String text2,String url): 构建一个表示请求成功，并包含书籍相关信息的ResultJson对象。
errorBook(String name,String text1,String text2): 构建一个表示网络错误的ResultJson对象，但不包含书籍相关信息。
这个类的设计目的是为了方便构建不同类型的HTTP响应，根据不同的情况返回不同的数据格式和状态码。同时，使用了Lombok库的@Builder和@ToString注解，简化了代码编写，并增强了可读性。*/


/*这段代码实现了一个用于构建HTTP响应的工具类 ResultJson。它的主要功能包括：

定义了HTTP响应的数据结构： ResultJson 类中定义了几个字段，用于表示HTTP响应的状态码、消息以及可能包含的数据。这些字段包括：

code: 表示HTTP状态码。
data, data1, data2, data3, data4: 用于存储响应数据的对象。
name, text1, text2, url: 用于存储书籍相关信息的字符串。
msg: 用于存储HTTP响应的消息。
提供了不同类型的响应构建方法： ResultJson 类中包含了一系列静态方法，用于构建不同类型的HTTP响应。这些方法包括：

error(Object data): 构建一个表示网络错误的响应，可以附带数据。
error(): 构建一个表示网络错误的响应。
success(Object data): 构建一个表示请求成功的响应，可以附带数据。
success(): 构建一个表示请求成功的响应。
successType(Object data1, Object data2, Object data3, Object data4): 构建一个表示请求成功，并包含多个数据的响应。
errorType(Object data1, Object data2, Object data3, Object data4): 构建一个表示网络错误，并包含多个数据的响应。
successBook(String name, String text1, String text2, String url): 构建一个表示请求成功，并包含书籍相关信息的响应。
errorBook(String name, String text1, String text2): 构建一个表示网络错误的响应，但不包含书籍相关信息。
通过这些方法，开发者可以根据不同的场景和需求，方便地构建出符合要求的HTTP响应对象。这种设计使得代码更具可维护性和可扩展性，同时提高了开发效率。
*/

