package com.zeal.musicplayer.dao;

import com.zeal.musicplayer.pojo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MusicMapper {
    //查找所有音乐
    List<Music> queryAllMusic();
    //搜索功能
    List<Music> queryByName(@Param("name") String name);
    //收藏列表
    List<Music> queryCollect(int id);
    //增加收藏
    void addCollect(Collect collect);
    //取消收藏
    void deleteCollect(Collect collect);
    //
    List<Type> queryAllSinger();
    //分类功能
    List<Music> queryBySinger(int id);

}
