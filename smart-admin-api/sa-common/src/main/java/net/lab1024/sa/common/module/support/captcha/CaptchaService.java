package net.lab1024.sa.common.module.support.captcha;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import lombok.extern.slf4j.Slf4j;
import net.lab1024.sa.common.common.code.UserErrorCode;
import net.lab1024.sa.common.common.constant.StringConst;
import net.lab1024.sa.common.common.domain.ResponseDTO;
import net.lab1024.sa.common.common.domain.SystemEnvironment;
import net.lab1024.sa.common.common.exception.BusinessException;
import net.lab1024.sa.common.constant.RedisKeyConst;
import net.lab1024.sa.common.module.support.captcha.domain.CaptchaForm;
import net.lab1024.sa.common.module.support.captcha.domain.CaptchaVO;
import net.lab1024.sa.common.module.support.redis.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Objects;
import java.util.UUID;

/**
 * Captcha service
 *
 */
@Slf4j
@Service
public class CaptchaService {

    /**
     * expiration: 65 seconds
     */
    private static final long EXPIRE_SECOND = 65L;

    @Autowired
    private DefaultKaptcha defaultKaptcha;
    @Autowired
    private SystemEnvironment systemEnvironment;
    @Autowired
    private RedisService redisService;

    /**
     * Generate graphical captcha.
     * By default, it has a 1-minute validity period.
     *
     * @return CaptchaVO object containing captcha information.
     */
    public CaptchaVO generateCaptcha() {
        String captchaText = defaultKaptcha.createText();
        BufferedImage image = defaultKaptcha.createImage(captchaText);

        String base64Code;
        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            ImageIO.write(image, "jpg", os);
            base64Code = Base64Utils.encodeToString(os.toByteArray());
        } catch (Exception e) {
            log.error("generateCaptcha error:", e);
            throw new BusinessException("Generate Captcha error");
        }

        /**
         * Return captcha
         *
         */
        // uuid 唯一标识
        String uuid = UUID.randomUUID().toString().replace("-", StringConst.EMPTY);

        CaptchaVO captchaVO = new CaptchaVO();
        captchaVO.setCaptchaUuid(uuid);
        captchaVO.setCaptchaBase64Image("data:image/png;base64," + base64Code);
        captchaVO.setExpireSeconds(EXPIRE_SECOND);
        if (!systemEnvironment.isProd()) {
            captchaVO.setCaptchaText(captchaText);
        }
        String redisCaptchaKey = redisService.generateRedisKey(RedisKeyConst.Support.CAPTCHA, uuid);
        redisService.set(redisCaptchaKey, captchaText, EXPIRE_SECOND);
        return captchaVO;
    }

    /**
     * checkCaptcha
     *
     * @param captchaForm
     * @return
     */
    public ResponseDTO<String> checkCaptcha(CaptchaForm captchaForm) {
        if (StringUtils.isBlank(captchaForm.getCaptchaUuid()) || StringUtils.isBlank(captchaForm.getCaptchaCode())) {
            return ResponseDTO.userErrorParam("Please input correct captcha");
        }
        /**
         * 1、Check the captcha in redis
         * 2、Delete redis
         */
        String redisCaptchaKey = redisService.generateRedisKey(RedisKeyConst.Support.CAPTCHA, captchaForm.getCaptchaUuid());
        String redisCaptchaCode = redisService.get(redisCaptchaKey);
        if (StringUtils.isBlank(redisCaptchaCode)) {
            return ResponseDTO.userErrorParam("Captcha has expired, please refresh and try again.");
        }
        if (!Objects.equals(redisCaptchaCode, captchaForm.getCaptchaCode())) {
            return ResponseDTO.userErrorParam("The captcha is wrong, please enter the correct code.");
        }
        redisService.delete(redisCaptchaKey);
        return ResponseDTO.ok();
    }

}
