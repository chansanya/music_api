package com.music.domain.resp;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @name: SongListResp
 * @author: leihuangyan
 * @classPath: com.music.domain.resp.SongListResp
 * @date: 2023/3/17
 * @description:
 */

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SongListResp {


    @JsonProperty("hostuin")
    private String hostuin;

    @JsonProperty("encrypt_uin")
    private String encryptUin;

    @JsonProperty("hostname")
    private String hostname;

    @JsonProperty("totoal")
    private Integer totoal;

    @JsonProperty("disslist")
    private List<DisslistDTO> disslist;

    @Data
    @Accessors(chain = true)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DisslistDTO {

        @JsonProperty("diss_name")
        private String dissName;
        @JsonProperty("diss_cover")
        private String dissCover;
        @JsonProperty("song_cnt")
        private String songCnt;
        @JsonProperty("listen_num")
        private String listenNum;
        @JsonProperty("dirid")
        private String dirid;
        @JsonProperty("tid")
        private String tid;
        @JsonProperty("dir_show")
        private String dirShow;
    }

}
