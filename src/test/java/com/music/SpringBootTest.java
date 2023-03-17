package com.music;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;

import com.music.domain.DomainBuilds;
import com.music.http.WebClientHelper;
import com.music.http.IApiSong;

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
        IApiSong api = WebClientHelper.getApi("https://c.y.qq.com", IApiSong.class);

        log.info("开始请求歌单");
        String s = api.songList(DomainBuilds.init());

        log.info("得到歌单:{}",s);
    }




}
