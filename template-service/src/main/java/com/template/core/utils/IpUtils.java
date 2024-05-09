package com.template.core.utils;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * IP 工具类
 *
 * @author xiong.canwei
 * @version V1.0
 * @date 2020/2/19 22:16
 */
@Slf4j
public class IpUtils {

    /**
     * 无地址
     */
    public static final String UNKNOWN_ADDR = "unKnown";

    public static final String IP4_LOCALHOST = "127.0.0.1";

    public static final String IP6_LOCALHOST = "0:0:0:0:0:0:0:1";

    private static final String IP_PATTERN = "^(?:(?:[01]?\\d{1,2}|2[0-4]\\d|25[0-5])\\.){3}(?:[01]?\\d{1,2}|2[0-4]\\d|25[0-5])\\b";

    /**
     * 获取客户端ip
     * <p>获取客户端的IP地址的方法是：request.getRemoteAddr()。
     * 但是在通过了Apache，Squid等反向代理软件就不能获取到客户端的真实IP地址。
     * 如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，
     * 而是一串ip值，例如：192.168.1.110， 192.168.1.120， 192.168.1.130， 192.168.1.100。
     * 其中第一个192.168.1.110才是用户真实的ip
     * </p>
     *
     * @param request {@link HttpServletRequest}
     * @return {@code String} 客户端IP，在极少数情况下可能会返回null
     */
    public static String getRealIp(HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        String ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.isBlank(ip) || UNKNOWN_ADDR.equalsIgnoreCase(ip)) {
            ip = request.getHeader("x-forwarded-for");
        }
        if (StringUtils.isBlank(ip) || UNKNOWN_ADDR.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip) || UNKNOWN_ADDR.equalsIgnoreCase(ip)) {
            ip = request.getHeader("proxy-client-ip");
        }
        if (StringUtils.isBlank(ip) || UNKNOWN_ADDR.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isBlank(ip) || UNKNOWN_ADDR.equalsIgnoreCase(ip)) {
            ip = request.getHeader("wl-proxy-client-ip");
        }
        if (StringUtils.isBlank(ip) || UNKNOWN_ADDR.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StringUtils.isBlank(ip) || UNKNOWN_ADDR.equalsIgnoreCase(ip)) {
            ip = request.getHeader("http_client_ip");
        }
        if (StringUtils.isBlank(ip) || UNKNOWN_ADDR.equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StringUtils.isBlank(ip) || UNKNOWN_ADDR.equalsIgnoreCase(ip)) {
            ip = request.getHeader("http_x_forwarded_for");
        }
        if (StringUtils.isBlank(ip) || UNKNOWN_ADDR.equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (StringUtils.isBlank(ip) || UNKNOWN_ADDR.equalsIgnoreCase(ip)) {
            ip = request.getHeader("x-real-ip");
        }
        if (StringUtils.isBlank(ip) || UNKNOWN_ADDR.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if (IP4_LOCALHOST.equals(ip) || IP6_LOCALHOST.equals(ip)) {
                //根据网卡取本机配置的IP
                try {
                    InetAddress net = InetAddress.getLocalHost();
                    ip = net.getHostAddress();
                } catch (UnknownHostException e) {
                    log.error("getRealIp occurs error, caused by: ", e);
                }
            }
        }
        //多次反向代理后会有多个ip值，第一个ip才是真实ip
        if (StringUtils.isNotBlank(ip) && !UNKNOWN_ADDR.equalsIgnoreCase(ip)) {
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            return (index > -1) ? ip.substring(0, index) : ip;
        }
        return null;

    }

    /**
     * 获取服务器IP
     *
     * @return {@code String} 服务器ip，在获取网卡异常时，会返回null
     */
    public static String getServiceIp() {
        try {
            // 获取网卡信息
            Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
            while (netInterfaces.hasMoreElements()) {
                NetworkInterface ni = netInterfaces.nextElement();
                Enumeration<InetAddress> ips = ni.getInetAddresses();
                Pattern pattern = Pattern.compile(IP_PATTERN);
                while (ips.hasMoreElements()) {
                    String ip = ips.nextElement().getHostAddress();
                    Matcher matcher = pattern.matcher(ip);
                    if (matcher.matches() && !IP4_LOCALHOST.equals(ip)) {
                        return ip;
                    }
                }
            }
        } catch (Exception e) {
            log.error("getServiceIp occurs error, caused by: ", e);
        }

        return null;
    }

}
