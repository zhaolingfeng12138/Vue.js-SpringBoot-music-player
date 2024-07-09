package com.leth.playmanageserver.Daos;

import org.apache.ibatis.annotations.Mapper;
import com.leth.playmanageserver.Pojos.Albums;
import java.util.List;

@Mapper
public interface AlbumsDao {
    // 用于添加
    public int insertAlbums(Albums albums);

    // 用于删除
    public int deleteAlbums(int id);

    // 用于更新
    public int updateAlbums(Albums albums);

    // 用于查询
    public List<Albums> listAlbumsAll();

    //用于搜索
    public List<Albums> searchAlbums(Albums albums);

    //用于搜索one
    public List<Albums> searchAlbumsOne(int id);
}
