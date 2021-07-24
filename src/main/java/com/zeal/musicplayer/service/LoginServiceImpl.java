package com.zeal.musicplayer.service;


import com.zeal.musicplayer.dao.LoginMapper;
import com.zeal.musicplayer.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("loginService")
public class LoginServiceImpl implements ILoginService{

    private LoginMapper loginMapper;

    public void setLoginMapper(LoginMapper loginMapper) {
        this.loginMapper = loginMapper;
    }
    @Autowired
    public LoginServiceImpl(LoginMapper loginMapper){
        setLoginMapper(loginMapper);
    }

    public User queryUser(int id, String password){
        return loginMapper.queryUser(id,password);
    };

    public boolean register(int uid,String pwd,String uname) {
        int num=loginMapper.register(uid, pwd, uname);
        if(num>=1) return true;
        else return false;
    }
}
