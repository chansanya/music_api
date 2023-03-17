package com.music.domain;

import java.nio.charset.StandardCharsets;

/**
 * @name: DomainBuilds
 * @author: leihuangyan
 * @classPath: com.music.domain.DomainBuilds
 * @date: 2023/3/17
 * @description:
 */
public class DomainBuilds {

    public static  SongList init(){
        return  SongList.builder()
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
