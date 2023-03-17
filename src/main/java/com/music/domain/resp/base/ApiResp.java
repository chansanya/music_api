package com.music.domain.resp.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @name: ApiResp
 * @author: leihuangyan
 * @classPath: com.music.domain.resp.base.ApiResp
 * @date: 2023/3/17
 * @description:
 */

@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResp<T>  extends RespCode{

    @JsonProperty("subcode")
    private String subcode;

    @JsonProperty("message")
    private String message;

    @JsonProperty("data")
    private T data;

}
