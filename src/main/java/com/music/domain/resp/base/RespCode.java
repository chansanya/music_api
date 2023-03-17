package com.music.domain.resp.base;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @name: ApiResp
 * @author: leihuangyan
 * @classPath: com.music.domain.resp.base.ApiResp
 * @date: 2023/3/17
 * @description:
 */

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RespCode {

    @JsonProperty("code")
    private String code;

}
