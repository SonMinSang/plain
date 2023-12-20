package plain.spring.commons.exception;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Order(2)
public class ExceptionHandlerFilter extends OncePerRequestFilter {
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        try{
            System.out.println("ExceptionHandlerFilter");
            filterChain.doFilter(request,response);
        } catch(CustomException e){
            response.sendError(e.getErrorCode().getHttpStatus().value(), e.getMessage());
        } catch (ServletException ex) {
            ex.printStackTrace();
        }
    }
}