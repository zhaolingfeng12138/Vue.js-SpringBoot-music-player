package com.leth.playmanageserver.Pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data    // 为属性加上getset方法
@AllArgsConstructor  //有参构造方法
@NoArgsConstructor   //无参构造方法
public class Admins {
    int id;
    String account;
    String password;

}