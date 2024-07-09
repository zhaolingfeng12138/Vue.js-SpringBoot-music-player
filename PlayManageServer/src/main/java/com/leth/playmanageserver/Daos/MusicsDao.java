package com.leth.playmanageserver.Daos;

import org.apache.ibatis.annotations.Mapper;
import com.leth.playmanageserver.Pojos.Musics;
import java.util.List;

@Mapper
public interface MusicsDao {
    // 用于添加
    public int insertMusics(Musics musics);

    // 用于删除
    public int deleteMusics(int id);

    // 用于更新
    public int updateMusics(Musics musics);

    // 用于查询
    public List<Musics> listMusicsAll();

    //用于搜索
    public List<Musics> searchMusics(Musics musics);

    //用于搜索one
    public List<Musics> searchMusicsOne(int id);
}
