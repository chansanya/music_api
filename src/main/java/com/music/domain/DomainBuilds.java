package com.music.domain;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @name: DomainBuilds
 * @author: leihuangyan
 * @classPath: com.music.domain.DomainBuilds
 * @date: 2023/3/17
 * @description:
 */
public class DomainBuilds {

    public static SongListInfoReqParam initSongListInfo(String dissId){

        return new SongListInfoReqParam.SongListInfoReqParamBuilder()
                .disstid(dissId)
//                .loginUin("984038622")
                .loginUin("0")
                .utf8("1")
                .type("1")
                .build();
    }

    public static SongReqParam initSong(){
//        String  songMId = "001LMj8V1Leoxy";
//        String  songId = "237773551";

        String  songMId = "003Y997x0AUDHK";
        String  songId = "393960841";

        return SongReqParam.builder()
                .comm(new SongReqParam.CommDTO.CommDTOBuilder()
//                        .cv(4747474)
//                        .ct(24)
                        .format("json")
                        .inCharset(StandardCharsets.UTF_8.name())
                        .outCharset(StandardCharsets.UTF_8.name())
                        .notice(0)
                        .platform("yqq.json")
                        .needNewCode(1)
                        .uin(984038622)
                        .build())
                .req1(new SongReqParam.Req1DTO.Req1DTOBuilder()
                        .module("vkey.GetVkeyServer")
                        .method("CgiGetVkey")
                        .param(new SongReqParam.Req1DTO.ParamDTO.ParamDTOBuilder()
                                .guid("4241080140")
//                                .guid()
                                .songmid(List.of("001LMj8V1Leoxy","003Y997x0AUDHK","002LNOds0rYvpK","002kkJSE0IeS1F","002kkJSE0IeS1F","001ufyHx10iWpg","003cI52o4daJJL","004TXEXY2G2c7C","004295Et37taLD"))
                                .songtype(List.of(0))
                                .uin("984038622")
                                .loginflag(1)
                                .platform("20")
                                .build())
                        .build())
                .req2(new SongReqParam.Req2DTO.Req2DTOBuilder()
                        .module("music.musichallSong.PlayLyricInfo")
                        .method("GetPlayLyricInfo")
                        .param(new SongReqParam.Req2DTO.ParamDTO.ParamDTOBuilder()
                                .songMId(songMId)
                                .songId(songId)
                                .build())
                        .build())
                .req3(new SongReqParam.Req1DTO.Req1DTOBuilder()
                        .module("vkey.GetVkeyServer")
                        .method("CgiGetVkey")
                        .param(new SongReqParam.Req1DTO.ParamDTO.ParamDTOBuilder()
                                .guid("5814727336")
//                                .guid()
                                .songmid(List.of(songMId))
                                .songtype(List.of(0))
                                .uin("984038622")
                                .loginflag(1)
                                .platform("20")
                                .build())
                        .build())
                .build();
    }

    public static SongListReqParam init(){
        return  SongListReqParam.builder()
                .hostuin("984038622")
                .sin(0)
                .size(11)
                .gTk(5381)
                .format("json")
                .inCharset(StandardCharsets.UTF_8.name())
                .outCharset(StandardCharsets.UTF_8.name())
                .notice(0)
                .platform("yqq.json")
                .needNewCode(0)
                .build();
    }

}
