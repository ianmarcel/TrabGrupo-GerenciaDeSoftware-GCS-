package br.travelexpense.config;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.travelexpense.utils.ErrorResponse;

import static br.travelexpense.config.JWTAuthenticationFilter.SECRET;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

    public JWTAuthorizationFilter(AuthenticationManager authManager) {
        super(authManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
            HttpServletResponse res,
            FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(HEADER_STRING);

        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(req, res);

        if (authentication == null) {
            return;
        }
        Authentication auth = super.getAuthenticationManager().authenticate(authentication);
        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(auth);
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request,
            HttpServletResponse response) {
        String token = request.getHeader(HEADER_STRING);

        if (token != null) {
            try {
                DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(SECRET.getBytes()))
                        .build()
                        .verify(token.replace(TOKEN_PREFIX, ""));

                String cpf = decodedJWT.getClaim("cpf").asString();
                String senha = decodedJWT.getClaim("senha").asString();

                if (cpf != null && senha != null) {
                    UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(cpf, senha,
                            new ArrayList<>());
                    return authReq;
                }

                return null;
            } catch (JWTVerificationException e) {
                ErrorResponse errorResponse = new ErrorResponse("Token inválido");
                sendErrorResponse(response, errorResponse);
                return null;
            }
        }

        return null;
    }

    private void sendErrorResponse(HttpServletResponse response, ErrorResponse errorResponse) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        ObjectMapper objectMapper = new ObjectMapper();
        String json;
        try {
            json = objectMapper.writeValueAsString(errorResponse);
            response.getWriter().write(json);
            response.getWriter().flush();
        } catch (IOException e) {
        }

    }
}