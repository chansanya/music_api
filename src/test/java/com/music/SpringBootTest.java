package com.music;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;

import com.music.domain.DomainBuilds;
import com.music.http.ApiHelper;
import com.music.http.ApiSong;

import lombok.extern.slf4j.Slf4j;

/**
 * @name: SpringbootTest
 * @author: leihuangyan
 * @classPath: com.music.SpringbootTest
 * @date: 2023/3/17
 * @description:
 */


@Slf4j
//@org.springframework.boot.test.context.SpringBootTest
@AutoConfigureWebTestClient(timeout = "20000")
public class SpringBootTest {


    @Test
    public void getSongList(){
        ApiSong api = ApiHelper.getApi("https://c.y.qq.com", ApiSong.class);

//        ApiHelper.getApi("https://u.y.qq.com",ApiSong.class);

        log.info("开始请求歌单");
        String s = api.songList(DomainBuilds.init());
//        String s = api.songList();

        log.info("得到歌单:{}",s);
    }




}
