package com.music.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;



@Data
@Accessors(chain = true)
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class SongReqParam {

    @JsonProperty("comm")
    private CommDTO comm;
    @JsonProperty("req_1")
    private Req1DTO req1;
    @JsonProperty("req_2")
    private Req2DTO req2;
    @JsonProperty("req_3")
    private Req1DTO req3;

    @Data
    @Accessors(chain = true)
    @Builder
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CommDTO {
        @JsonProperty("cv")
        private Integer cv;
        @JsonProperty("ct")
        private Integer ct;
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
        @JsonProperty("uin")
        private Integer uin;
        @JsonProperty("g_tk_new_20200303")
        private Integer gTkNew20200303;
        @JsonProperty("g_tk")
        private Integer gTk;


    }

    @Data
    @Accessors(chain = true)
    @Builder
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Req1DTO {

        @JsonProperty("module")
        private String module;
        @JsonProperty("method")
        private String method;
        @JsonProperty("param")
        private ParamDTO param;


        @Data
        @Accessors(chain = true)
        @Builder
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class ParamDTO {

            @JsonProperty("guid")
            private String guid;
            @JsonProperty("uin")
            private String uin;
            @JsonProperty("loginflag")
            private Integer loginflag;
            @JsonProperty("platform")
            private String platform;
            @JsonProperty("songmid")
            private java.util.List<String> songmid;

            @JsonProperty("songtype")
            private java.util.List<Integer> songtype;

        }
    }


    @Data
    @Accessors(chain = true)
    @Builder
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Req2DTO {

        @JsonProperty("module")
        private String module;
        @JsonProperty("method")
        private String method;
        @JsonProperty("param")
        private ParamDTO param;


        @Data
        @Accessors(chain = true)
        @Builder
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class ParamDTO {
            @JsonProperty("songID")
            private String songId;
            @JsonProperty("songMID")
            private String songMId;

        }
    }


}
