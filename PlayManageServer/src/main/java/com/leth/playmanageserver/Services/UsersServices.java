
package com.leth.playmanageserver.Services;

import com.leth.playmanageserver.Daos.UsersDao;
import com.leth.playmanageserver.Pojos.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service //将类交由spring管理
public class UsersServices {

    @Autowired     //注入Dao对象
    private UsersDao usersdao;

    public int insertUsers(Users users){
        return usersdao.insertUsers(users);
    }

    public int deleteUsers(int id){
        return usersdao.deleteUsers(id);
    }

    public int updateUsers(Users users){
        return usersdao.updateUsers(users);
    }

    public List<Users> listUsersAll(){
        return usersdao.listUsersAll();
    }

    public List<Users> loginUsers(Users users){
        return usersdao.loginUsers(users);
    }

    public List<Users> searchUsers(Users users){
        return usersdao.searchUsers(users);
    }

    public List<Users> searchUsersOne(int id){
        return usersdao.searchUsersOne(id);
    }

    public int updateUsersPassword(Users users){
        return usersdao.updateUsersPassword(users);
    }
}
