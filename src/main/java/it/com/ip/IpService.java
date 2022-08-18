package it.com.ip;

import it.com.util.IPUtil;
import javax.servlet.http.HttpServletRequest;
import net.dreamlu.mica.ip2region.core.Ip2regionSearcher;
import net.dreamlu.mica.ip2region.core.IpInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @Title:
 * @Description:
 * @date 2022/7/2015:12
 */
@Service
public class IpService {

    @Autowired
    private Ip2regionSearcher regionSearcher;

    public static final String CHINA = "中国";
    public static final String UN_KNOW = "未知";

    /**
     * 根据IP获取地址
     *
     * @param request
     */
    public String getAddress(HttpServletRequest request) {
        String ipAddress = IPUtil.getIpAddress(request);
        return getAddressByIp(ipAddress);
    }

    /**
     * 根据IP获取地址
     *
     * @param ip
     * @return 地址，中国具体到省份，eg: 北京，河北省
     * 外国具体到国家，eg: 美国，法国
     * 无法解析的返回：未知
     */
    public String getAddressByIp(String ip) {
        System.out.println(ip);
        IpInfo ipInfo = regionSearcher.memorySearch(ip);
        if (ipInfo == null) {
            return UN_KNOW; // 没有解析到
        }
        String country = ipInfo.getCountry();
        if (StringUtils.isEmpty(country)) {
            return UN_KNOW; // 没有解析到
        }
        if (CHINA.equals(country)) {
            return ipInfo.getProvince();    // 中国返回省份
        }
        return ipInfo.getCountry(); // 外国返回国家
    }
}
