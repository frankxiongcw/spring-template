package com.fookoo.template.server.utils;

import com.fookoo.template.server.constants.CommonConstants;
import com.fookoo.template.server.constants.ExceptionDef;
import com.fookoo.template.server.constants.RedisConstants;
import com.fookoo.template.server.exception.ServiceException;
import com.spring.template.pojo.vo.VerifyImageVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

/**
 * 验证码工具类
 *
 * @author PengFei787
 * @version v1.0
 * @date 2020/2/19 14:56
 */
@Component
public class VerifyImageUtil {

    private static final Logger logger = LoggerFactory.getLogger(VerifyImageUtil.class);

    private static final String RAND_STRING = "3456789ABCDEFGHJKLMNPQRSTUVWXY";//随机产生数字与字母组合的字符串
    private static final int IMAGE_WIDTH = 95;// 图片宽
    private static final int IMAGE_HEIGHT = 25;// 图片高
    private static final int LINE_SIZE = 40;// 干扰线数量
    private static final int STRING_NUM = 4;// 随机产生字符数量
    private static final SecureRandom RANDOM = new SecureRandom();

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 生成随机图片
     */
    public VerifyImageVO getVerifyImage() {
        VerifyImageVO res = new VerifyImageVO();
        String base64Str = "";
        // BufferedImage类是具有缓冲区的Image类,Image类是用于描述图像信息的类
        BufferedImage image = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_INT_BGR);
        Graphics g = image.getGraphics();// 产生Image对象的Graphics对象,改对象可以在图像上进行各种绘制操作
        g.fillRect(0, 0, IMAGE_WIDTH, IMAGE_HEIGHT);//图片大小
        g.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 18));//字体大小
        g.setColor(getRandColor(110, 133));//字体颜色
        // 绘制干扰线
        for (int i = 0; i <= LINE_SIZE; i++) {
            drawLine(g);
        }
        // 绘制随机字符
        String randomString = "";
        for (int i = 1; i <= STRING_NUM; i++) {
            randomString = drawString(g, randomString, i);
        }
        String uuid = UUIDUtils.getUUID();
        res.setUuid(uuid);
        //把验证码放redis,验证用
        redisTemplate.opsForValue().set(RedisConstants.VERIFY_IMAGE_CODE + uuid, randomString, CommonConstants.VERIFY_IMAGE_CODE_EXPIRE_TIME, TimeUnit.SECONDS);
        g.dispose();
        try {
            // 将内存中的图片通过流动形式输出到客户端
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ImageIO.write(image, "png", out);
            byte[] bytes = out.toByteArray();
            //转base64
            BASE64Encoder encoder = new BASE64Encoder();
            String png_base64 = encoder.encodeBuffer(bytes).trim();//转换成base64串
            //删除 \r\n
            base64Str = "data:image/png;base64," + png_base64.replaceAll("\n", "").replaceAll("\r", "");

        } catch (Exception e) {
            logger.error("将内存中的图片通过流动形式输出到客户端失败>>>>   ", e);
            throw new ServiceException(ExceptionDef.C9999, "图片流输出失败");
        }
        res.setBase64str(base64Str);
        return res;
    }

    /**
     * 绘制字符串
     */
    private String drawString(Graphics g, String randomString, int i) {
        g.setFont(getFont());
        g.setColor(new Color(RANDOM.nextInt(101), RANDOM.nextInt(111), RANDOM.nextInt(121)));
        String rand = getRandomString(RANDOM.nextInt(RAND_STRING.length()));
        randomString += rand;
        g.translate(RANDOM.nextInt(3), RANDOM.nextInt(3));
        g.drawString(rand, 13 * i, 16);
        return randomString;
    }

    /**
     * 绘制干扰线
     */
    private void drawLine(Graphics g) {
        int x = RANDOM.nextInt(IMAGE_WIDTH);
        int y = RANDOM.nextInt(IMAGE_HEIGHT);
        int xl = RANDOM.nextInt(13);
        int yl = RANDOM.nextInt(15);
        g.drawLine(x, y, x + xl, y + yl);
    }

    /**
     * 获取随机的字符
     */
    private String getRandomString(int num) {
        return String.valueOf(RAND_STRING.charAt(num));
    }


    /**
     * 获得字体
     */
    private Font getFont() {
        return new Font("Fixedsys", Font.CENTER_BASELINE, 18);
    }

    /**
     * 获得颜色
     */
    private Color getRandColor(int fc, int bc) {
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + RANDOM.nextInt(bc - fc - 16);
        int g = fc + RANDOM.nextInt(bc - fc - 14);
        int b = fc + RANDOM.nextInt(bc - fc - 18);
        return new Color(r, g, b);
    }
}
