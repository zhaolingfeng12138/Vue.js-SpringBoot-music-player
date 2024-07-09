
package com.leth.playmanageserver.Services;

import com.leth.playmanageserver.Daos.AlbumsDao;
import com.leth.playmanageserver.Pojos.Albums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service //将类交由spring管理
public class AlbumsServices {

    @Autowired     //注入Dao对象
    private AlbumsDao albumsdao;

    public int insertAlbums(Albums albums){
        return albumsdao.insertAlbums(albums);
    }

    public int deleteAlbums(int id){
        return albumsdao.deleteAlbums(id);
    }

    public int updateAlbums(Albums albums){
        return albumsdao.updateAlbums(albums);
    }

    public List<Albums> listAlbumsAll(){
        return albumsdao.listAlbumsAll();
    }

    public List<Albums> searchAlbums(Albums albums){
        return albumsdao.searchAlbums(albums);
    }

    public List<Albums> searchAlbumsOne(int id){
        return albumsdao.searchAlbumsOne(id);
    }
}
