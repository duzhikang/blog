package com.zk.blog.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zk.blog.entity.UserDo;
import com.zk.blog.service.UserService;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义json过滤器
 */
public class CustomJSONLoginFilter extends AbstractAuthenticationProcessingFilter {

    private UserService userService;

    public CustomJSONLoginFilter(String defaultFilterProcessesUrl, UserService userService) {
        super(new AntPathRequestMatcher(defaultFilterProcessesUrl, HttpMethod.POST.name()));
        this.userService = userService;
    }

    protected CustomJSONLoginFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        JSONObject requestBody = getRequestBody(httpServletRequest);
        String username = requestBody.getString("username");
        String password = requestBody.getString("password");
        validateUsernameAndPassword(username, password);
        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        simpleGrantedAuthorities.add(new SimpleGrantedAuthority("USER"));
        return new UsernamePasswordAuthenticationToken(username, password, simpleGrantedAuthorities);
    }

    private JSONObject getRequestBody(HttpServletRequest request) throws AuthenticationServiceException {
        InputStream inputStream = null;
        try {
            StringBuilder stringBuilder = new StringBuilder();
            inputStream = request.getInputStream();
            byte[] bs = new byte[StreamUtils.BUFFER_SIZE];
            int len;
            while ((len = inputStream.read(bs)) != -1) {
                stringBuilder.append(new String(bs, 0, len));
            }
            inputStream.close();
            return JSON.parseObject(stringBuilder.toString());
        }catch (IOException e) {
            System.out.println("");
        }
        throw new AuthenticationServiceException("");
    }

    /**
     * 检验用户名和密码
     * @param username
     * @param password
     */
    private void validateUsernameAndPassword(String username, String password) {
        UserDo userDo = userService.getByUsername(username);
        if (userDo == null) {
            throw new UsernameNotFoundException("user not exist");
        }
        if (!userDo.getPassword().equals(password)) {
            throw new AuthenticationServiceException("error username or password");
        }
    }
}
