package cycleTest.cycleDemo.security;

import java.io.IOException;

import cycleTest.cycleDemo.payloads.ApiErrorResponse;
import cycleTest.cycleDemo.security.JwtService;
import cycleTest.cycleDemo.security.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    JwtService jwtService;

    @Autowired
    UserDetailServiceImpl userDetailsServiceImpl;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {

            String token = getJwtFromRequest(request);

            // authentication key missing
            if (!StringUtils.hasText(token)) {
                setErrorResponse(response, "JWT token missing.");
                return;
            }

            // invalid token
            String username = jwtService.extractUserName(token);
            if (username == null) {
                setErrorResponse(response, "Invalid JWT.");
                return;
            }
            UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);
            if (!jwtService.validateToken(token, userDetails)) {
                setErrorResponse(response, "Invalid JWT.");
                return;
            }

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
                    null, userDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (ExpiredJwtException e) {
            setErrorResponse(response, "JWT expired.");
            return;

        } catch (Exception e) {
            e.printStackTrace();
            setErrorResponse(response, "Could not set user authentication in security context.");
            return;
        }

        filterChain.doFilter(request, response);

    }

    @Override
    public boolean shouldNotFilter(HttpServletRequest request) {
        if (request.getRequestURI().contains("/api/") && !request.getRequestURI().contains("/auth/")) {
            return false;
        }
        return true;
    }

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }

    private HttpServletResponse setErrorResponse(HttpServletResponse response, String message) throws IOException {
        log.error(message);

        ApiErrorResponse error = ApiErrorResponse.builder().error(true).message(message).build();

        response.setContentType("application/json");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(error.toString());

        return response;
    }

}
