package com.blue.blueplanserviceapplicationpc.Controller;

import com.google.code.kaptcha.Producer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
@RequestMapping("/blue")
@Slf4j
public class KaptchaController {
    @Autowired
    private Producer producer;

    @Autowired
    private CacheManager cacheManager;

    @GetMapping("/code")
    public void generation(HttpServletResponse response, HttpSession session) {
        String text = RandomStringUtils.randomAlphanumeric(4);

        log.info("生成验证码：{}", text);

        BufferedImage image = producer.createImage(text);

        //缓存验证码
        cacheManager.getCache("kaptcha").put(text, text);
        session.setAttribute("code",text);
        System.out.println(session.getAttribute("code"));
        //set content type
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control","no-store,no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setContentType(MediaType.IMAGE_JPEG.getType());
        try {
            FastByteArrayOutputStream os = new FastByteArrayOutputStream();
            ImageIO.write(image, "jpeg", os);
            os.writeTo(response.getOutputStream());
        } catch (IOException e) {
            log.error("验证码处理失败：{}", e.getMessage(), e);
            throw new RuntimeException("验证码获取失败", e);
        }

    }
}
