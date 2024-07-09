package com.leth.playmanageserver.Daos;

import org.apache.ibatis.annotations.Mapper;
import com.leth.playmanageserver.Pojos.Users;
import java.util.List;

@Mapper
public interface UsersDao {
    // 用于添加
    public int insertUsers(Users users);

    // 用于删除
    public int deleteUsers(int id);

    // 用于更新
    public int updateUsers(Users users);

    // 用于查询
    public List<Users> listUsersAll();

    //用于登录
    public  List<Users> loginUsers(Users users);

    //用于搜索
    public List<Users> searchUsers(Users users);

    //用于搜索one
    public List<Users> searchUsersOne(int id);

    // 用于更新密码
    public int updateUsersPassword(Users users);

}
