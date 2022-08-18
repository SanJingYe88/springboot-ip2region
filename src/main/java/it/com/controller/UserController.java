package it.com.controller;

import it.com.ip.IpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @Title:
 * @Description:
 * @date 2022/7/2015:16
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private IpService ipService;

    @GetMapping("test")
    public String test(HttpServletRequest request) {
        String address = ipService.getAddress(request);
        System.out.println(address);
        return address;
    }

    @GetMapping("test2")
    public String test() {
        List<String> ips = new ArrayList<>();
        ips.add("123.123.234.124");
        ips.add("34.150.54.177");
        ips.add("47.75.115.233");
        ips.add("124.223.85.30");
        ips.add("223.109.148.139");
        ips.add("103.216.152.58");
        ips.add("20.205.7.27");
        ips.add("184.95.51.82");
        ips.add("119.13.95.147");
        ips.add("45.64.55.97");
        ips.add("154.85.42.37");
        ips.forEach(x -> {
            String address = ipService.getAddressByIp(x);
            System.out.println(address);
        });
        return "success";
    }
}
