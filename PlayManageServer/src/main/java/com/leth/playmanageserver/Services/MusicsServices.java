
package com.leth.playmanageserver.Services;

import com.leth.playmanageserver.Daos.MusicsDao;
import com.leth.playmanageserver.Pojos.Musics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service //将类交由spring管理
public class MusicsServices {

    @Autowired     //注入Dao对象
    private MusicsDao musicsdao;

    public int insertMusics(Musics musics){
        return musicsdao.insertMusics(musics);
    }

    public int deleteMusics(int id){
        return musicsdao.deleteMusics(id);
    }

    public int updateMusics(Musics musics){
        return musicsdao.updateMusics(musics);
    }

    public List<Musics> listMusicsAll(){
        return musicsdao.listMusicsAll();
    }

    public List<Musics> searchMusics(Musics musics){
        return musicsdao.searchMusics(musics);
    }

    public List<Musics> searchMusicsOne(int id){
        return musicsdao.searchMusicsOne(id);
    }
}
