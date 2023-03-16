package com.music.config;

import java.time.Duration;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Bean;

import lombok.extern.slf4j.Slf4j;

/**
 * @name: ApiConfig
 * @author: leihuangyan
 * @classPath: com.music.config.ApiConfig
 * @date: 2023/3/14
 * @description:
 */

@Slf4j
public class ApiConfig {


    public ApiConfig() {
      log.info("初始化。。。");
    }

    // 销毁时关闭谷歌浏览器
    @Bean(destroyMethod = "quit")
    public ChromeDriver chromeDriver() {
        // idea中为了方便则开启这条注释，指定正确的chrome驱动位置
         System.setProperty("webdriver.chrome.driver", "D:/tools/chromedriver_win32/chromedriver.exe");
//        System.setProperty("webdriver.chrome.whitelistedIps", "");
        ChromeOptions options = new ChromeOptions();

        //无浏览器模式
//        options.addArguments("--headless");
        // 为了让root用户也能执行
        options.addArguments("--no-sandbox");

        options.addArguments("--remote-allow-origins=*");

        // 优化参数
//        options.addArguments("--disable-dev-shm-usage");
//        options.addArguments("--verbose");
//        options.addArguments("blink-settings=imagesEnabled=false");
//        options.addArguments("--disable-gpu");

        //实例化
        ChromeDriver chromeDriver = new ChromeDriver(options);
        chromeDriver.manage().timeouts().scriptTimeout(Duration.ofSeconds(15));
        return chromeDriver;
    }
}
