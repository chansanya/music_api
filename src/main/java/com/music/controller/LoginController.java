package com.music.controller;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.music.enums.WebLoginEnum;

import cn.hutool.json.JSONObject;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
public class LoginController {

    @Autowired(required = false)
    ChromeDriver chromeDriver;


    @GetMapping(value = "/login/qq")
    public JSONObject commonLogin() {
        return loginAndGetCookie("984038622", "6666qaQA", WebLoginEnum.QQ_MUSIC, "2");
    }

    /**
     * 登录已知类型的网站
     *
     * @param username qq账号
     * @param password qq密码
     * @param name     枚举登录页面的封装类,如果hashMap中没有则这个name就是登录页面的地址
     * @param format   返回的cookie类型，1表示原cookie数据，2表示处理后的cookie
     * @return json类型的cookie封装类
     */
    public JSONObject loginAndGetCookie(String username, String password, WebLoginEnum name, String format) {
        final JSONObject cookieJson = new JSONObject();
        try {
            // 登录前先清除cookie
            final String url = WebLoginEnum.getUrl(name);
            if (!StringUtils.hasText(url)) {
                throw new RuntimeException("请使用另外一个接口进行登录");
            }

            log.info("准备访问");

            chromeDriver.get(url);
            log.info("进行访问:{}",url);

            final WebDriver ptloginIframe = chromeDriver.switchTo().frame("ptlogin_iframe");

            ptloginIframe.findElement(By.id("switcher_plogin")).click();

            final WebElement u = ptloginIframe.findElement(By.className("inputstyle"));
            // 清空输入的用户名
            u.clear();

            // 输入账号
            u.sendKeys(username + "\n");

            final WebElement p = ptloginIframe.findElement(By.id("p"));

            // 清空输入的密码数据
            p.clear();
            // 输入密码，回车就提交了下面的这个点击登录不需要
            p.sendKeys(password + "\n");

            log.info("判断页面跳转前");

            //得到标题
            final String title = chromeDriver.getTitle();
            log.info("当前标题 ==>{} " , title);

            // 如果标题没有换则说明页面没有切换
            int times = 0;
            while (chromeDriver.getTitle().equals(title) || !StringUtils.hasText(chromeDriver.getTitle())) {
                //如果标题相等就让他自旋，不相等说明跳转了页面
                try {
                    times++;
                    if (times > 20) {
                        System.out.println("===============>>");
                        chromeDriver.findElement(By.id("login_button")).click();

                        chromeDriver.switchTo().defaultContent();
                        chromeDriver.switchTo().frame("ptlogin_iframe");
                        try {
                            TimeUnit.SECONDS.sleep(2);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        final WebDriver tcaptcha_iframe = chromeDriver.switchTo().frame("tcaptcha_iframe");
                        System.out.println("111");

                        Actions actions = new Actions(tcaptcha_iframe);
                        final WebElement tcaptchaDragButton = tcaptcha_iframe.findElement(By.id("tcaptcha_drag_thumb"));
                        actions.clickAndHold(tcaptchaDragButton);

                        // 获取拖拽验证码的位置
                        final Point location = tcaptchaDragButton.getLocation();
                        log.info("验证码位置:{}",location);

                        IntStream.range(1, 170).forEach(x -> {
                            actions.moveToElement(tcaptchaDragButton, 1, 0).perform();
                        });
                        actions.release(tcaptchaDragButton).perform();

                        break;
                    }
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

//            chromeDriver.navigate().refresh();// 刷新页面
            // 重新获取manage，之前的那个manage已经失效了
            WebDriver.Options manage  = chromeDriver.manage();

            // 获取到了标题说明页面跳转成功了
            System.out.println("标题变化后 ==> " + chromeDriver.getTitle());
            System.out.println("页面进行了跳转或者超时，限定了5秒内，否则结束循环");
            //获得cookie
            Set<Cookie> coo = manage.getCookies();
            //打印cookie
            System.out.println(coo);

            if ("2".equals(format)) {
                final String cookie = parseCookieSet(coo);
                // 解析cookie并添加
                cookieJson.set("cookie", cookie);
            } else {
                cookieJson.set("cookie", coo);
            }
//            manage.deleteAllCookies();// 每次登录完就清除cookie
        } catch (Exception e) {
            log.error("异常:",e);
        }
        return cookieJson;
    }


    /**
     * cookie数据的处理
     *
     * @param cookies 传入cookie的集合
     * @return k1=v1;k2=v2;  这种形式的cookie字符串
     */
    private String parseCookieSet(Set<Cookie> cookies) {
        if (cookies == null) {
            return "";
        }
        System.out.println("\n解析前cookie是" + cookies.toString());
        String cookieString = "";
        for (Cookie cookie : cookies) {
            // 为空的cookie则去除
            if (StringUtils.hasText(cookie.getValue())) {
                cookieString += (cookie.getName() + "=" + cookie.getValue() + ";");
            }
        }
        System.out.println("解析后：\n" + cookieString);
        return cookieString;
    }



}