package plain.spring.commons.elk;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import plain.spring.commons.exception.CustomException;
import plain.spring.commons.exception.ErrorCode;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@Order(1)
//@Component
@RequiredArgsConstructor
public class RequestFilter extends OncePerRequestFilter {
    private final ObjectMapper objectMapper;
    private static final Logger logger = LoggerFactory.getLogger(RequestFilter.class);

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        System.out.println("RequestFilter");
        String traceId = UUID.randomUUID().toString();
        try {
            CachedBodyHttpServletRequest requestWrapper = new CachedBodyHttpServletRequest(request);
            requestWrapper.setAttribute("traceId", traceId);
            filterChain.doFilter(requestWrapper, response);
        } catch (Exception e) {
            log.error(e.getMessage());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

            CustomException customException = new CustomException(ErrorCode.FORBIDDEN, traceId);

            try {
                response.getWriter().print(objectMapper.writeValueAsString(customException));
                response.getWriter().flush();
            } catch (IOException ioException) {
                log.warn("IOException Occur");
                throw new RuntimeException(ioException);
            }
        }
    }
}
