package com.leth.playmanageserver.Daos;

import org.apache.ibatis.annotations.Mapper;
import com.leth.playmanageserver.Pojos.Songers;
import java.util.List;

@Mapper
public interface SongersDao {
    // 用于添加
    public int insertSongers(Songers songers);

    // 用于删除
    public int deleteSongers(int id);

    // 用于更新
    public int updateSongers(Songers songers);

    // 用于查询
    public List<Songers> listSongersAll();

    //用于搜索
    public List<Songers> searchSongers(Songers songers);

    //用于搜索one
    public List<Songers> searchSongersOne(int id);
}
