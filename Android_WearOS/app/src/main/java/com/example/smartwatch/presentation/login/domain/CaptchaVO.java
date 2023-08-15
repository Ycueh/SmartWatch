package com.example.smartwatch.presentation.login.domain;

import lombok.Data;

@Data
public class CaptchaVO {
    private String captchaUuid;
    private String captchaText;
    private String captchaBase64Image;
    private Long expireSeconds;

    public String getCaptchaUuid() {
        return captchaUuid;
    }

    public void setCaptchaUuid(String captchaUuid) {
        this.captchaUuid = captchaUuid;
    }

    public String getCaptchaText() {
        return captchaText;
    }

    public void setCaptchaText(String captchaText) {
        this.captchaText = captchaText;
    }

    public String getCaptchaBase64Image() {
        return captchaBase64Image;
    }

    public void setCaptchaBase64Image(String captchaBase64Image) {
        this.captchaBase64Image = captchaBase64Image;
    }

    public Long getExpireSeconds() {
        return expireSeconds;
    }

    public void setExpireSeconds(Long expireSeconds) {
        this.expireSeconds = expireSeconds;
    }
}
