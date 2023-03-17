package com.music.http;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import com.music.domain.SongListInfoReqParam;
import com.music.domain.SongListReqParam;
import com.music.domain.SongReqParam;
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
 * https://u.y.qq.com /cgi-bin/musicu.fcg
 *
 *
 * http://c.y.qq.com/qzone/fcg-bin/fcg_ucc_getcdinfo_byids_cp.fcg
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


    @GetExchange(value = "/qzone/fcg-bin/fcg_ucc_getcdinfo_byids_cp.fcg")
    String songListById(@FormBody SongListInfoReqParam songList);



    @PostExchange("/cgi-bin/musicu.fcg")
    String song(@RequestBody  SongReqParam param);


}
