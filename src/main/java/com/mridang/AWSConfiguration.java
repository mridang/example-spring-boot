package com.mridang;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.http.apache.ApacheHttpClient;
import software.amazon.awssdk.services.s3.S3Client;

@Configuration
class AWSConfiguration {

    @Bean
    S3Client s3Client() {
        return S3Client.builder()
                .httpClientBuilder(ApacheHttpClient.builder()
                        .tcpKeepAlive(true)
                        .maxConnections(300))
                .build();
    }
}
