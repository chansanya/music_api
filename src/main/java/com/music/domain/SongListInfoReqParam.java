package com.music.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @name: SongListInfoReqParam
 * @author: leihuangyan
 * @classPath: com.music.domain.SongListInfoReqParam
 * @date: 2023/3/17
 * @description:
 */
@Data
@Accessors
@Builder
public class SongListInfoReqParam {

    @JsonProperty("type")
    private String type;

    @JsonProperty("utf8")
    private String utf8;

    @JsonProperty("disstid")
    private String disstid;

    @JsonProperty("loginUin")
    private String loginUin;

}
