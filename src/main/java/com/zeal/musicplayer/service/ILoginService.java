package com.zeal.musicplayer.service;


import com.zeal.musicplayer.pojo.User;

public interface ILoginService {
     User queryUser(int id, String password);
     boolean register(int uid,String password,String uname);
}
