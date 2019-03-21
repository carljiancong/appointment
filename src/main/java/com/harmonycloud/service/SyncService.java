package com.harmonycloud.service;

import com.harmonycloud.result.CimsResponseWrapper;
import com.harmonycloud.util.TraceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

@Service
public class SyncService {

    @Autowired
    private RestProxyTemplate restProxyTemplate;

    @Autowired
    private HttpServletRequest request;

    public CimsResponseWrapper save(URI uri, String token, Object body) throws Exception{
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
        ResponseEntity<CimsResponseWrapper> response = restProxyTemplate.getRestTemplate().postForEntity(uri, request, CimsResponseWrapper.class);
        return response.getBody();
    }
}
