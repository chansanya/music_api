package com.music.http;

import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import com.music.domain.SongList;
import com.music.resolvers.FormBody;

/**
 * @name: ApiSong
 * @author: leihuangyan
 * @classPath: com.music.http.ApiSong
 * @date: 2023/3/17
 * @description:
 *
 * https://c.y.qq.com/rsc/fcgi-bin/fcg_user_created_diss
 *
 * https://u.y.qq.com/cgi-bin/musicu.fcg
 */
@HttpExchange
public interface ApiSong {



//    @GetExchange("/rsc/fcgi-bin/fcg_user_created_diss")
    @GetExchange(value = "/rsc/fcgi-bin/fcg_user_created_diss",accept =  {"application/json"})
    String songList(@FormBody  SongList songList);


    @GetExchange("/cgi-bin/musicu.fcg")
    String song(String id);


}
