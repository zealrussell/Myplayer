package com.zeal.musicplayer.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import com.zeal.musicplayer.pojo.*;
import com.zeal.musicplayer.service.ILoginService;
import com.zeal.musicplayer.service.IMusicService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.List;

@Controller
@RequestMapping("/c")
public class MusicController {
    private IMusicService musicService;
    private ILoginService loginService;

    @Autowired
    public MusicController(ILoginService loginService,IMusicService musicService){
        this.musicService = musicService;
        this.loginService = loginService;
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        List<Music> musicList = musicService.queryAllMusic();
        return musicList.toString();
    }

    @RequestMapping("/hello")
    public String welcome(){
        return "login";
    }

    @RequestMapping("/clogin")
    public String login(String uphonenumber,String upassword,String uverifycode,HttpServletResponse response, HttpSession session) throws IOException { //登录
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        if(uphonenumber==null || upassword==null || upassword==null) return null;
        int id = Integer.parseInt(uphonenumber);
        String msg="wrong";
        //MD5加密
        //String pwd = DigestUtils.md5DigestAsHex(password.getBytes());
        String code = (String) session.getAttribute("RANDOMKEY");

        if(!code.equalsIgnoreCase(uverifycode)){
            msg="codeerror";
        }else {
            User user = loginService.queryUser(id, upassword);
            if (user != null) {
                session.setAttribute("userid", id);
                session.setAttribute("username", user.getUsername());
                msg = "success";
            } else msg = "usererror";
        }
        response.getWriter().print(msg);
        return null;

    }

    @RequestMapping("/cregister")
    public String register(String uphonenumber,String uname,String upassword,HttpServletResponse response) throws IOException {//注册
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        int id=Integer.parseInt(uphonenumber);
        if( loginService.register(id,uname,upassword) ) response.getWriter().print("success"); //插入成功
        else response.getWriter().print("error");
        return null;
    }

    @RequestMapping("/clogout")
    public String logout(HttpSession session){ //登出
        session.removeAttribute("userid");
        session.removeAttribute("username");
        return "login";
    }

    @RequestMapping("/cmain")
    public String main(Model model){  //主页面 ✔
        List<Music> musicList = musicService.queryAllMusic();
        JSON musicjson = (JSON) JSONArray.toJSON(musicList);
        model.addAttribute("mlist",musicList);
        model.addAttribute("mjson",musicjson);
        return "main";
    }

    @RequestMapping("/csearch")
    public String search(String musicname,Model model){  //搜索
        List<Music> musicList = musicService.queryByName(musicname);
        JSON musicjson = (JSON) JSON.toJSON(musicList);
        model.addAttribute("mlist",musicList);
        model.addAttribute("mjson",musicjson);
        return "main";
    }

    //收藏页面
    @RequestMapping("/ccollect")
    public String collect(Model model,HttpSession session) throws IOException {  //收藏页面
        int uid = (Integer) session.getAttribute("userid");
        List<Music> musicList = musicService.queryCollect(uid);
        JSON musicjson = (JSON) JSONArray.toJSON(musicList);
        model.addAttribute("mlist",musicList);
        model.addAttribute("mjson",musicjson);
        return "collect";
    }

    //收藏操作
    @RequestMapping("/ccoloperation")
    public String add(String act,String musicid,HttpServletResponse response,HttpSession session) throws IOException { //增加、取消收藏
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        int uid = (Integer) session.getAttribute("userid");
        if(session.getAttribute("userid")==null) response.getWriter().print("error");
        else {
            int mid=Integer.parseInt(musicid);
            Collect collect = new Collect(uid, mid);
            if ("收藏".equals(act)) {
                musicService.addCollect(collect);
                response.getWriter().print("add");
            } else {
                musicService.deleteCollect(collect);
                response.getWriter().print("del");
            }
        }
        return null;
    }

    //歌手页面
    @RequestMapping("/ctype")
    public String type(Model model){
        List<Type> singerList = musicService.queryAllSinger();
        JSON singerjson = (JSON)JSONArray.toJSON(singerList);
        model.addAttribute("slist",singerList);
        //model.addAttribute("sjson",singerjson);
        return "type";
    }

    //分类页面
    @RequestMapping("/cclassify")
    public String classify(String sid,Model model){
        int id=Integer.parseInt(sid);
        List<Music> musicList = musicService.queryBySinger(id);
        JSON musicjson = (JSON) JSONArray.toJSON(musicList);
        model.addAttribute("mlist",musicList);
        model.addAttribute("mjson",musicjson);
        return "main";
    }

    // 生成验证码图片
    @RequestMapping("/getcode")
    @ResponseBody
    public void getCaptcha(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("image/png");
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Expire", "0");
            response.setHeader("Pragma", "no-cache");

            ValidateCode validateCode = new ValidateCode();
            // 直接返回图片
            validateCode.getRandomCodeImage(request, response);
        } catch (Exception e) {
            System.out.println(e);
        }

    }

}
