package com.zeal.musicplayer.service;


import com.zeal.musicplayer.dao.MusicMapper;
import com.zeal.musicplayer.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("musicService")
public class MusicServiceImpl implements IMusicService{
    private MusicMapper musicMapper;
    public void setMusicMapper(MusicMapper musicMapper){
        this.musicMapper=musicMapper;
    }
    @Autowired
    public MusicServiceImpl(MusicMapper musicMapper){
        setMusicMapper(musicMapper);
    }

    public List<Music> queryAllMusic() { return musicMapper.queryAllMusic(); }

    public List<Music> queryByName(String name) {
        return musicMapper.queryByName(name);
    }

    public List<Music> queryCollect(int id) {
        return musicMapper.queryCollect(id);
    }

    public List<Type> queryAllSinger(){
        return musicMapper.queryAllSinger();
    }

    public List<Music> queryBySinger(int id){
        return musicMapper.queryBySinger(id);
    }

    public void addCollect(Collect collect) {
        musicMapper.addCollect(collect);
    }

    public void deleteCollect(Collect collect) {
        musicMapper.deleteCollect(collect);
    }

}
