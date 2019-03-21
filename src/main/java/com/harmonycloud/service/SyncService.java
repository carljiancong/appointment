package com.harmonycloud.service;

import com.harmonycloud.dto.ResponseDto;
import com.harmonycloud.exception.AppointmentException;
import com.harmonycloud.util.TraceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;

@Service
public class SyncService {

    @Autowired
    private RestProxyTemplate restProxyTemplate;

    @Autowired
    private HttpServletRequest request;

    public ResponseDto save(URI uri, String token, Object body) throws Exception{
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        if (request.getHeader("user") != null) {
            headers.set("user", request.getHeader("user"));
        }
        if (request.getHeader("clinic") != null) {
            headers.set("clinic", request.getHeader("clinic"));
        }
        headers.set("Authorization", "Bearer " + token);
        TraceUtil.addTraceForHttp(request, headers);
        TraceUtil.printTrace(headers);
        HttpEntity<Object> request = new HttpEntity<>(body, headers);
        ResponseEntity<ResponseDto> response = restProxyTemplate.getRestTemplate().postForEntity(uri, request, ResponseDto.class);
        return response.getBody();
    }
}
