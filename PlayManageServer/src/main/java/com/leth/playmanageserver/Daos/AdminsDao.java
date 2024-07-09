package com.leth.playmanageserver.Daos;

import org.apache.ibatis.annotations.Mapper;
import com.leth.playmanageserver.Pojos.Admins;
import java.util.List;

@Mapper
public interface AdminsDao {
    // 用于添加
    public int insertAdmins(Admins admins);

    // 用于删除
    public int deleteAdmins(int id);

    // 用于更新
    public int updateAdmins(Admins admins);

    // 用于查询
    public List<Admins> listAdminsAll();

    //用于登录
    public  List<Admins> loginAdmins(Admins admins);

    //用于搜索
    public List<Admins> searchAdmins(Admins admins);

    //用于搜索one
    public List<Admins> searchAdminsOne(int id);
}
