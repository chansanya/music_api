package com.music.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @name: SongList
 * @author: leihuangyan
 * @classPath: com.music.domain.SongList
 * @date: 2023/3/17
 * @description:
 */
@Data
@Accessors(chain = true)
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class SongList {


    @JsonProperty("hostuin")
    private String hostuin;

    @JsonProperty("sin")
    private Integer sin;

    @JsonProperty("size")
    private Integer size;

    @JsonProperty("g_tk")
    private Integer gTk;

    @JsonProperty("format")
    private String format;

    @JsonProperty("inCharset")
    private String inCharset;


    @JsonProperty("outCharset")
    private String outCharset;

    @JsonProperty("notice")
    private Integer notice;

    @JsonProperty("platform")
    private String platform;

    @JsonProperty("needNewCode")
    private Integer needNewCode;


}
