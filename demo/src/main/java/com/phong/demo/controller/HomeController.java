package com.phong.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String index() {
        return "home/home";
    }

    @RequestMapping("/home")
    public String home() {
        return "home/home";
    }

    @RequestMapping("/about")
    public String about() {
        return "home/about";
    }

    @RequestMapping("/company")
    public String company() {
        return "home/company";
    }
    
    @RequestMapping("/service")
    public String service() {
        return "home/service/service";
    }

    @RequestMapping("/price")
    public String price() {
        return "home/service/price";
    }

    @RequestMapping("/area")
    public String area() {
        return "home/service/area";
    }

    @RequestMapping("/qa")
    public String qa() {
        return "home/qa";
    }

    @RequestMapping("/terms")
    public String terms() {
        return "home/terms";
    }

    @RequestMapping("/nondisclosure")
    public String nondisclosure() {
        return "home/nondisclosure";
    }
}
