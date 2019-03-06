package com.harmonycloud.service;

import com.harmonycloud.dto.ResponseDto;
import com.harmonycloud.entity.Encounter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class SyncService {

    private final Logger logger = LoggerFactory.getLogger(SyncService.class);

    @Autowired
    private  RestProxyTemplate restProxyTemplate;

    public ResponseDto save(URI uri, Encounter encounter) throws RestClientException,URISyntaxException {
        try {
            HttpEntity<Object> request = new HttpEntity<>(encounter);
            ResponseEntity<ResponseDto> response=restProxyTemplate.getRestTemplate().postForEntity(uri, request, ResponseDto.class);
            return response.getBody();
        } catch (RestClientException e) {
            throw e;
        }
    }
}
