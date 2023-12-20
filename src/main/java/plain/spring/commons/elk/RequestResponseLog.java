package plain.spring.commons.elk;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestResponseLog {
    private String traceId;

    private String className;

    private String httpMethod;

    private String uri;

    private String method;

    private Map<String, String> params;

    private String logTime;

    private String serverIp;

    private String deviceType;

    private Object requestBody;

    private Object responseBody;

    private String elapsedTime;


}