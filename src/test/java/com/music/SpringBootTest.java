package com.music;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.music.domain.DomainBuilds;
import com.music.domain.resp.SongListResp;
import com.music.domain.resp.base.ApiResp;
import com.music.http.IApiSong;
import com.music.http.WebClientHelper;
import com.music.util.JsonUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * @name: SpringbootTest
 * @author: leihuangyan
 * @classPath: com.music.SpringbootTest
 * @date: 2023/3/17
 * @description:
 */


@Slf4j
//@org.springframework.boot.test.context.SpringBootTest
@AutoConfigureWebTestClient(timeout = "50000")
public class SpringBootTest {


    @Test
    public void getSongList(){
        IApiSong api = WebClientHelper.getApi("https://c.y.qq.com", IApiSong.class);

        log.info("开始请求歌单");
        String apiResp = api.songList(DomainBuilds.init());
        log.info("得到请求结果:{}",apiResp);

        ApiResp<SongListResp> resp = JsonUtil.toResp(apiResp, new TypeReference<>() {});
        if(!"0".equals(resp.getCode())){
            log.info("请求失败");
            return;
        }

        print(resp);
    }

    @Test
    public void getSongInfo(){
        IApiSong api = WebClientHelper.getApi("https://c.y.qq.com", IApiSong.class);
        log.info("开始请求歌单");
        String apiResp = api.songListById(DomainBuilds.initSongListInfo("8054843490"));
        log.info("得到请求结果:{}",apiResp);
    }

    @Test
    public void getSong(){
        IApiSong api = WebClientHelper.getApi("https://u.y.qq.com", IApiSong.class);
        String apiResp = api.song(DomainBuilds.initSong());
        log.info("得到请求结果:{}",apiResp);

        try {
            JsonNode jsonNode = JsonUtil.getObjectMapper().readTree(apiResp);

            if(0 != jsonNode.get("code").asInt()){
                log.info("请求失败");
                return;
            }

            songPares(jsonNode,"req_1");

            songPares(jsonNode,"req_3");


        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    private static void songPares(JsonNode jsonNode,String node) {
        JsonNode data = jsonNode.get(node).get("data");

        ArrayNode sips =(ArrayNode) data.get("sip");

        sips.forEach(i->{
            log.info("SIP:{}",sips);
        });

        ArrayNode midUrlList =(ArrayNode) data.get("midurlinfo");
        for (JsonNode midUrlInfo : midUrlList) {
            JsonNode songMid = midUrlInfo.get("songmid");
            JsonNode purl = midUrlInfo.get("purl");

            log.info("songMid:{} ,purl :{}{}",songMid.asText(),sips.get(0).asText(),purl.asText());
        }
    }


    @Test
    public void json(){
        String jsonStr = "{\"code\":0,\"subcode\":0,\"message\":\"ok\",\"data\":{\"hostuin\":984038622,\"encrypt_uin\":\"NKcPoeoF7w-A\",\"hostname\":\"产彡\",\"totoal\":7,\"disslist\":[{\"diss_name\":\"QZone背景音乐\",\"diss_cover\":\"http://y.gtimg.cn/mediastyle/y/img/cover_qzone_130.jpg\",\"song_cnt\":5,\"listen_num\":0,\"dirid\":205,\"tid\":0,\"dir_show\":0},{\"diss_name\":\"搞错了，再来\",\"diss_cover\":\"http://y.gtimg.cn/music/photo_new/T002R300x300M000000wjiaZ0Q68Vt.jpg?n=1\",\"song_cnt\":9,\"listen_num\":87,\"dirid\":25,\"tid\":8445417748,\"dir_show\":1},{\"diss_name\":\"QQ里的网易云\",\"diss_cover\":\"http://y.gtimg.cn/music/photo_new/T002R300x300M0000044tjcU3HYRUt.jpg?n=1\",\"song_cnt\":7,\"listen_num\":256,\"dirid\":23,\"tid\":8445412062,\"dir_show\":1},{\"diss_name\":\"哦哦哦哦\",\"diss_cover\":\"http://y.gtimg.cn/music/photo_new/T002R300x300M0000019DOaT2CE0OG.jpg?n=1\",\"song_cnt\":10,\"listen_num\":94,\"dirid\":22,\"tid\":8445391399,\"dir_show\":1},{\"diss_name\":\"睡觉了\",\"diss_cover\":\"http://y.gtimg.cn/music/photo_new/T002R300x300M000001HM2km2K4GUv.jpg?n=1\",\"song_cnt\":9,\"listen_num\":29,\"dirid\":1,\"tid\":8445382323,\"dir_show\":1},{\"diss_name\":\"惊艳了时光\",\"diss_cover\":\"http://qpic.y.qq.com/music_cover/T76450IyeJNdibl3a2PvIRDo2zFwbR8L1g2icGu8GfgxKabkyKTVMveg/300?n=1\",\"song_cnt\":11,\"listen_num\":2227,\"dirid\":17,\"tid\":8054843490,\"dir_show\":1},{\"diss_name\":\"1楼选亚索\",\"diss_cover\":\"http://qpic.y.qq.com/music_cover/T76450IyeJNdibl3a2PvIRAN81k5hQloWCzsnQNE298kGWfqQG7WCmQ/300?n=1\",\"song_cnt\":12,\"listen_num\":550,\"dirid\":13,\"tid\":1436210735,\"dir_show\":1}]}}";

        log.info("原字符:{}",jsonStr);
//        ApiResp<SongListResp> bean = JsonUtil.toBean(jsonStr, ApiResp.class);
//        log.info("得到歌单:{}",bean);

        ApiResp<SongListResp> resp1 = JsonUtil.toResp(jsonStr, new TypeReference<>() {});

        log.info("得到歌单:{}",resp1);

        print(resp1);
    }

    private static void print(ApiResp<SongListResp> resp1) {
        SongListResp data = resp1.getData();

        String hostUin = data.getHostuin();

        String hostname = data.getHostname();

        List<SongListResp.DisslistDTO> disslist = data.getDisslist();

        log.info("得到QQ号:{},{}:的歌单",hostUin,hostname);
        disslist.forEach(i->{
            log.info("歌单名信息: 歌单ID:{} ,歌单名:{},歌曲数:{},收听次数:{}封面:{}",
                    i.getTid(),
                    i.getDissName(),
                    i.getSongCnt(),
                    i.getListenNum(),
                    i.getDissCover()
            );
        });
    }


}
