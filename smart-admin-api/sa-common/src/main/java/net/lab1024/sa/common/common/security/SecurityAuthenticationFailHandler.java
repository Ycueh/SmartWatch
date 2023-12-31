package net.lab1024.sa.common.common.security;

import com.alibaba.fastjson.JSONObject;
import net.lab1024.sa.common.common.code.ErrorCode;
import net.lab1024.sa.common.common.code.UserErrorCode;
import net.lab1024.sa.common.common.domain.ResponseDTO;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SecurityAuthenticationFailHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException {
        this.outputResult(response, UserErrorCode.LOGIN_STATE_INVALID);
    }

    /**
     * Output
     *
     * @param response
     * @param errorCode
     * @throws IOException
     */
    private void outputResult(HttpServletResponse response, ErrorCode errorCode) throws IOException {
        String msg = JSONObject.toJSONString(ResponseDTO.error(errorCode));
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(msg);
        response.flushBuffer();
    }
}
