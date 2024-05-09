package com.template.core.config;


import com.fasterxml.jackson.core.type.TypeReference;
import com.template.core.utils.JsonUtil;
import com.template.core.utils.StringUtils;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class OrderServiceConfig implements InitializingBean {

    @Value("${order.service.config:}")
    private String orderServiceConfig;

//    private List<TypeService> typeServices;

//    public BigDecimal getAmount(String typeCode, String code) {
//        for (TypeService typeService : typeServices) {
//            if (Objects.equals(typeService.getTypeCode(), typeCode)) {
//                for (Service service : typeService.getServices()) {
//                    if (Objects.equals(service.getCode(), code)) {
//                        return service.getAmount();
//                    }
//                }
//            }
//        }
//        return null;
//    }

    public Service getServiceByCode(String typeCode, String code) {
        for (TypeService typeService : getTypeServices()) {
            if (Objects.equals(typeService.getTypeCode(), typeCode)) {
                for (Service service : typeService.getServices()) {
                    if (Objects.equals(service.getCode(), code)) {
                        return service;
                    }
                }
            }
        }
        return null;
    }

    public List<Service> getService(String typeCode) {
        for (TypeService typeService : getTypeServices()) {
            if (Objects.equals(typeService.getTypeCode(), typeCode)) {
                return typeService.getServices();
            }
        }
        return null;
    }

    private List<TypeService> getTypeServices() {
        List<TypeService> typeServices = new ArrayList<>();
        if (StringUtils.isNotBlank(orderServiceConfig)) {
            typeServices = JsonUtil.fromJson(orderServiceConfig, new TypeReference<List<TypeService>>() {
            });
        }
        return typeServices;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
//        InputStream is = OrderServiceConfig.class.getClassLoader().getResourceAsStream("orderService.json");
//        String json = IOUtils.toString(is, StandardCharsets.UTF_8);
//        typeServices = JsonUtil.fromJson(json, new TypeReference<List<TypeService>>() {
//        });
//        if (StringUtils.isNotBlank(orderServiceConfig)) {
//            typeServices = JsonUtil.fromJson(orderServiceConfig, new TypeReference<List<TypeService>>() {
//            });
//        }
    }

    @Data
    public static class TypeService {
        private String typeCode;

        private String typeName;

        private List<Service> services;
    }

    @Data
    public static class Service {

        private String code;

        private String name;

        private BigDecimal amount;

        private String desc;
    }
}
