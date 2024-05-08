package com.template.exception;


import com.template.pojo.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public class ServiceException extends RuntimeException {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceException.class);
    private static Map<Integer, String> SERVICE_EXCEPTION_MAP = null;
    private static final String SERVICE_EXCEPTION_FILE = "META-INF/exception_def.properties";
    private Integer code;
    private String message;

    public Integer getCode() {
        return this.code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ServiceException(ResultCode code, String message) {
        super(message);
        this.code = code.getCode();
        this.message = message;
    }

    public ServiceException(ResultCode code) {
        super(code.getDesc());
        this.code = code.getCode();
        this.message = code.getDesc();
    }

    public ServiceException(ResultCode code, Throwable cause) {
        super(code.getDesc(), cause);
        this.code = code.getCode();
        this.message = code.getDesc();
    }

    private synchronized void loadExceptionDef() {
        LOGGER.info("load exception definition");
        SERVICE_EXCEPTION_MAP = new ConcurrentHashMap();

        try {
            Enumeration exceptionDefs = ClassLoader.getSystemResources("META-INF/exception_def.properties");

            while(exceptionDefs.hasMoreElements()) {
                URL exceptionDef = (URL)exceptionDefs.nextElement();
                LOGGER.info("load exception definition from {}", exceptionDef);
                InputStream in = new BufferedInputStream(exceptionDef.openStream());
                Properties properties = new Properties();
                properties.load(new InputStreamReader(in, StandardCharsets.UTF_8));
                Iterator var5 = properties.stringPropertyNames().iterator();

                while(var5.hasNext()) {
                    String name = (String)var5.next();
                    Integer eCode = null;

                    try {
                        eCode = Integer.valueOf(name);
                    } catch (Exception var9) {
                        LOGGER.warn("{} not a int code!", name);
                    }

                    if (eCode != null) {
                        SERVICE_EXCEPTION_MAP.put(eCode, properties.getProperty(name));
                    }
                }
            }
        } catch (IOException var10) {
            LOGGER.error("load exception definition error", var10);
        }

        LOGGER.info("all service exception {}", SERVICE_EXCEPTION_MAP);
    }

    public String matchMessage(Integer code) {
        if (SERVICE_EXCEPTION_MAP == null) {
            this.loadExceptionDef();
        }

        String msg = (String)SERVICE_EXCEPTION_MAP.get(code);
        if ("".equals(msg) || msg == null) {
            LOGGER.warn("undefined exception message! code = {}", code);
            msg = "undefined exception message!";
        }

        return msg;
    }

    public ServiceException(Integer code) {
        this.code = code;
        this.message = this.matchMessage(code);
    }

    public ServiceException(Integer code, String message) {
        this.code = code;
        this.message = message;
        if ("".equals(this.message) || this.message == null) {
            this.message = this.matchMessage(code);
        }

    }

    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
        this.code = ResultCode.C500.getCode();
        this.message = message;
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
        this.code = ResultCode.C500.getCode();
        this.message = message;
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }
}

