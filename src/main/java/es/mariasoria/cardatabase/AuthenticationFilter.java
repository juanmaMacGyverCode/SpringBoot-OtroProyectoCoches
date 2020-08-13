package es.mariasoria.cardatabase;

import es.mariasoria.cardatabase.service.AuthenticationService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

// GenericFilterBean is a generic superclass for any type of filter
// This class will handle authentication in all other endpoints, except /login
// AuthenticationFilter uses the getAuthentication method from our service
// class to get a token from the request Authorization head
public class AuthenticationFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        Authentication authentication = AuthenticationService.getAuthentication((HttpServletRequest)request);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}
