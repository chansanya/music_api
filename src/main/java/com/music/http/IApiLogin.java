package com.music.http;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

/**
 * @name: IApiLogin
 * @author: leihuangyan
 * @classPath: com.music.http.IApiLogin
 * @date: 2023/3/14
 * @description:
 */

@HttpExchange(url = "/login/qr")
public interface IApiLogin {


    /**
     *  二维码 key 生成接口
     * 说明: 二维码登录涉及到 3 个接口,调用务必带上时间戳,防止缓存
     * @return Str
     */
    @GetExchange("/key")
    String  key();



    /**
     *  二维码生成接口
     *  调用此接口传入上一个接口生成的 key 可生成二维码图片的 base64 和二维码信息,
     *  可使用 base64 展示图片,或者使用二维码信息内容自行使用第三方二维码生成库渲染二维码
     * @param  key 必选参数: key,由 {@link #key()} 生成
     * @return Str
     */
    @GetExchange("/create")
    String  create(@RequestParam String key);

    /**
     *  二维码状态监测
     *  轮询此接口可获取二维码扫码状态,
     *  800 为二维码过期,
     *  801 为等待扫码,
     *  802 为待确认,
     *  803 为授权登录成功(803 状态码下会返回 cookies)
     *
     * @param  key 必选参数: key,由 {@link #key()} 生成
     * @return Str
     */
    @GetExchange("/check")
    String  check(@RequestParam String key);

}
