package com.zeal.musicplayer.dao;

import com.zeal.musicplayer.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author zeal
 */
@Mapper
@Repository
public interface LoginMapper {

    User queryUser(@Param("id") int id, @Param("password") String password);
    int register(@Param("id")int uid,@Param("username")String uname,@Param("password")String pwd);

}
