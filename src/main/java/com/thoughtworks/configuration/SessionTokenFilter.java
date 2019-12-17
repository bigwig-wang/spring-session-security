package com.thoughtworks.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.dto.UserInfoDTO;
import com.thoughtworks.entity.User;
import com.thoughtworks.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class SessionTokenFilter extends OncePerRequestFilter {
    private final UserService userService;

    public SessionTokenFilter(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        RequestWrapper req = new RequestWrapper(request);
        log.info("execute sessionToken filter, and request body is", req.getBody());
        if (!request.getRequestURI().equals("/api/user/login")
                || !request.getMethod().equalsIgnoreCase("POST")) {
            chain.doFilter(req, response);
            return;
        }

        UserInfoDTO userInfoDTO = getUserInfoDTO(req);
        User user = this.userService.findByUsernameAndPassword(userInfoDTO);
        UserAuthenticationToken token = new UserAuthenticationToken(user.getUsername(), user.getId());

        SecurityContextHolder.getContext().setAuthentication(token);
        chain.doFilter(req, response);
    }

    private UserInfoDTO getUserInfoDTO(RequestWrapper requestWrapper) throws IOException {
        String bodyString = requestWrapper.getBody();
        return new ObjectMapper().readValue(bodyString, UserInfoDTO.class);
    }
}
