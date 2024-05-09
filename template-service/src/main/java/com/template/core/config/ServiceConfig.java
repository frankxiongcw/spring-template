package com.template.core.config;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Configuration
@ComponentScan(basePackages = {"com.fookoo.template.server.service"})
public class ServiceConfig {

    @Bean
    public IDGenerator getIDGenerator() {
        return new IDGenerator();
    }

    @Bean
    HttpClient httpClient() {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        return client;
    }

    @Bean
    HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory(HttpClient httpClient) {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setHttpClient(httpClient);
        factory.setConnectTimeout(10000);
        factory.setReadTimeout(40000);
        return factory;
    }

    @Bean
    RestTemplate restTemplate(HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory) {
        RestTemplate restTemplate = new RestTemplate(httpComponentsClientHttpRequestFactory);
        Charset utf8 = StandardCharsets.UTF_8;
        List<HttpMessageConverter<?>> list = restTemplate.getMessageConverters();
        for (HttpMessageConverter<?> httpMessageConverter : list) {
            if (httpMessageConverter instanceof StringHttpMessageConverter) {
                ((StringHttpMessageConverter) httpMessageConverter).setDefaultCharset(utf8);
            }
            if (httpMessageConverter instanceof FormHttpMessageConverter) {
                ((FormHttpMessageConverter) httpMessageConverter).setCharset(utf8);
            }
        }
        return restTemplate;
    }
}
