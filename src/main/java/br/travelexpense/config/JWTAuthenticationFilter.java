package br.travelexpense.config;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.apache.commons.io.IOUtils;

import com.auth0.jwt.*;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.travelexpense.utils.ErrorResponse;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final long VADLID_TOKEN_TIME = 604_800_000L;
    public static final String SECRET = "SECRET_KEY";

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;

        setFilterProcessesUrl("/auth/login");
    }

    @SuppressWarnings("unchecked")
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException {
        try {
            InputStream inputStream = req.getInputStream();
            String requestBody = IOUtils.toString(inputStream, StandardCharsets.UTF_8);

            Map<String, Object> body = new ObjectMapper().readValue(requestBody, HashMap.class);

            String cpf = (String) body.getOrDefault("login", "");
            String senha = (String) body.getOrDefault("senha", "");

            UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(cpf, senha);

            try {
                Authentication auth = authenticationManager.authenticate(authReq);
                UserDetailsImpl user = ((UserDetailsImpl) auth.getPrincipal());
                user.setRawPassword(senha);
                return auth;
            } catch (AuthenticationException e) {
                ErrorResponse errorResponse = new ErrorResponse("Usuário ou senha incorretos");
                sendErrorResponse(res, errorResponse);
                return null;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendErrorResponse(HttpServletResponse res, ErrorResponse errorResponse) throws IOException {
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(errorResponse);

        res.getWriter().write(json);
        res.getWriter().flush();
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
            HttpServletResponse res,
            FilterChain chain,
            Authentication auth) throws IOException {
        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
        String token = JWT.create()
                .withClaim("cpf", userDetails.getUsername())
                .withClaim("senha", userDetails.getRawPassword())
                .withExpiresAt(new Date(System.currentTimeMillis() + VADLID_TOKEN_TIME))
                .sign(Algorithm.HMAC512(SECRET.getBytes()));

        Map<String, Object> body = new HashMap<>();

        body.put("token", token);
        body.put("user", ((UserDetailsImpl) auth.getPrincipal()).getUsername());

        String json = new ObjectMapper().writeValueAsString(body);

        res.setContentType("application/json");
        res.getWriter().write(json);
        res.getWriter().flush();

    }
}
