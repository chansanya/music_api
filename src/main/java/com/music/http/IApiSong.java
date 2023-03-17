package com.music.http;

import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import com.music.domain.SongListReqParam;
import com.music.resolvers.FormBody;

/**
 * @name: IApiSong
 * @author: leihuangyan
 * @classPath: com.music.http.IApiSong
 * @date: 2023/3/17
 * @description:
 *
 * https://c.y.qq.com/rsc/fcgi-bin/fcg_user_created_diss
 *
 * https://u.y.qq.com/cgi-bin/musicu.fcg
 */
@HttpExchange
public interface IApiSong {


    /**
     * 得到歌单
     * @param songList songList
     * @return QQ音乐返回
     */
    @GetExchange(value = "/rsc/fcgi-bin/fcg_user_created_diss")
    String songList(@FormBody SongListReqParam songList);


    @GetExchange("/cgi-bin/musicu.fcg")
    String song(String id);


}
