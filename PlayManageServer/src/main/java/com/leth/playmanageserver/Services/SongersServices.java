
package com.leth.playmanageserver.Services;

import com.leth.playmanageserver.Daos.SongersDao;
import com.leth.playmanageserver.Pojos.Songers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service //将类交由spring管理
public class SongersServices {

    @Autowired     //注入Dao对象
    private SongersDao songersdao;

    public int insertSongers(Songers songers){
        return songersdao.insertSongers(songers);
    }

    public int deleteSongers(int id){
        return songersdao.deleteSongers(id);
    }

    public int updateSongers(Songers songers){
        return songersdao.updateSongers(songers);
    }

    public List<Songers> listSongersAll(){
        return songersdao.listSongersAll();
    }

    public List<Songers> searchSongers(Songers songers){
        return songersdao.searchSongers(songers);
    }

    public List<Songers> searchSongersOne(int id){
        return songersdao.searchSongersOne(id);
    }
}
