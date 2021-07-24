package com.zeal.musicplayer;

import com.zeal.musicplayer.dao.MusicMapper;
import com.zeal.musicplayer.pojo.Music;
import com.zeal.musicplayer.service.MusicServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@SpringBootTest
class MyPlayerApplicationTests {
    @Autowired
    private DataSource dataSource;
    @Test
    void contextLoads() throws SQLException {
        System.out.println(dataSource.getClass());
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
        //template模板，拿来即用
        connection.close();
    }
    @Autowired
    private MusicServiceImpl musicService;
    @Test
    void mapperTest(){
        List<Music> musicList = musicService.queryAllMusic();
        musicList.forEach(e-> System.out.println(e));
    }
}
