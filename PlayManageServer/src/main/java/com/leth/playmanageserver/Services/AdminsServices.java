
package com.leth.playmanageserver.Services;

import com.leth.playmanageserver.Daos.AdminsDao;
import com.leth.playmanageserver.Pojos.Admins;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service //将类交由spring管理
public class AdminsServices {

    @Autowired     //注入Dao对象
    private AdminsDao adminsdao;

    public int insertAdmins(Admins admins){
        return adminsdao.insertAdmins(admins);
    }

    public int deleteAdmins(int id){
        return adminsdao.deleteAdmins(id);
    }

    public int updateAdmins(Admins admins){
        return adminsdao.updateAdmins(admins);
    }

    public List<Admins> listAdminsAll(){
        return adminsdao.listAdminsAll();
    }

    public List<Admins> loginAdmins(Admins admins){
        return adminsdao.loginAdmins(admins);
    }

    public List<Admins> searchAdmins(Admins admins){
        return adminsdao.searchAdmins(admins);
    }

    public List<Admins> searchAdminsOne(int id){
        return adminsdao.searchAdminsOne(id);
    }
}
