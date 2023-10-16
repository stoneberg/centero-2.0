package kr.centero.core.utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class LogUtil {
    private static final Logger log = LoggerFactory.getLogger(LogUtil.class);

    public void displayReq(HttpServletRequest request, Object body) {
        StringBuilder reqHeader = new StringBuilder();
        Map<String, List<String>> headers = this.getRequestHeaders(request);

        if (!headers.isEmpty()) {
            reqHeader.append("\n ▶️ REQUEST API : ");
            reqHeader.append(" method = [").append(request.getMethod()).append("]");
            reqHeader.append(" path = [").append(request.getRequestURI()).append("]");
            reqHeader.append("\n ▶️ RequestHeaders = {");
            headers.forEach((key, valueList) -> valueList.forEach(value -> reqHeader.append("\n ")
                    .append(StringUtils.SPACE).append(StringUtils.SPACE)
                    .append(key).append("=[").append(value).append("]")));
            reqHeader.append("\n").append("}");
            log.info("{}", reqHeader);
        }

        StringBuilder reqParameters = new StringBuilder();
        Map<String, String> parameters = this.getParameters(request);

        if (!parameters.isEmpty()) {
            reqParameters.append("\n ▶️ RequestParams = [").append(parameters).append("] ");
            log.info("{}", reqParameters);
        }

        if (!Objects.isNull(body)) {
            JsonUtil.prettyPrintRequestBody(body);
        }
    }

    public void displayResp(HttpServletRequest request, HttpServletResponse response, Object body) {
        StringBuilder resHeader = new StringBuilder();
        Map<String, String> headers = this.getResponseHeaders(response);
        resHeader.append("\n ◀️️ RESPONSE API : ");
        resHeader.append(" method = [").append(request.getMethod()).append("]");
        resHeader.append(" path = [").append(request.getRequestURI()).append("]");
        resHeader.append("\n ◀️️ ResponseHeaders = [").append(headers).append("]");
        log.info("{}", resHeader);

        if (!Objects.isNull(body)) {
            JsonUtil.prettyPrintResponseBody(body);
        }
    }

    private Map<String, List<String>> getRequestHeaders(HttpServletRequest request) {
        return Collections.list(request.getHeaderNames())
                .stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        header -> Collections.list(request.getHeaders(header))
                ));
    }

    private Map<String, String> getResponseHeaders(HttpServletResponse response) {
        Map<String, String> headers = new HashMap<>();
        Collection<String> headerMap = response.getHeaderNames();
        for (String str : headerMap) {
            headers.put(str, response.getHeader(str));
        }
        return headers;
    }

    private Map<String, String> getParameters(HttpServletRequest request) {
        Map<String, String> parameters = new HashMap<>();
        Enumeration<String> params = request.getParameterNames();
        while (params.hasMoreElements()) {
            String paramName = params.nextElement();
            String paramValue = request.getParameter(paramName);
            parameters.put(paramName, paramValue);
        }
        return parameters;
    }
}
