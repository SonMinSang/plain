package plain.spring.commons.elk;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.net.InetAddress;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Slf4j
//@Component
@Aspect
@RequiredArgsConstructor
public class ApiRequestAspect {
    private final ObjectMapper objectMapper;
    private static final Logger logger = LoggerFactory.getLogger(ApiRequestAspect.class);

    @Pointcut("within(plain.spring.*.controller.*)")
    public void apiRestPointCut() {}

    @Around("apiRestPointCut()")
    public Object reqResLogging(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        String traceId = (String) request.getAttribute("traceId");

        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        Map<String, String> params = getParams(request);

        String deviceType = request.getHeader("x-custom-device-type");
        String serverIp = InetAddress.getLocalHost().getHostAddress();

        RequestResponseLog reqResLogging = RequestResponseLog.builder()
                .traceId(traceId)
                .className(className)
                .httpMethod(request.getMethod())
                .uri(request.getRequestURI())
                .method(methodName)
                .params(params)
                .logTime(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME))
                .serverIp(serverIp)
                .deviceType(deviceType)
                .requestBody(objectMapper.readTree(request.getInputStream()))
                .build();

        long start = System.currentTimeMillis();
        try {
            Object result = joinPoint.proceed();
            String elapsedTime = "Method: " + className + "." + methodName + " execution time: " + (System.currentTimeMillis() - start) + "ms";

            if (result instanceof ResponseEntity) {
                ResponseEntity<?> responseEntity = (ResponseEntity<?>) result;
                reqResLogging.setResponseBody(responseEntity.getBody());
            } else {
                reqResLogging.setResponseBody("{}");
            }
            reqResLogging.setElapsedTime(elapsedTime);
            log.info(objectMapper.writeValueAsString(reqResLogging));
            return result;
        } catch (Exception e) {
            log.error("Error: ", e);
            throw e;
        }
    }

    private Map<String, String> getParams(HttpServletRequest request) {
        Map<String, String> params = new HashMap<>();
        Enumeration<String> paramNames = request.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            String replaceParam = paramName.replace(".", "-");
            params.put(replaceParam, request.getParameter(paramName));
        }
        return params;
    }
}
