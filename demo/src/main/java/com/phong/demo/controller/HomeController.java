package com.phong.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String index() {
        return "home";
    }

    @RequestMapping("/home")
    public String home() {
        return "home";
    }

    @RequestMapping("/about")
    public String about() {
        return "about";
    }

    @RequestMapping("/company")
    public String company() {
        return "company";
    }
    
    @RequestMapping("/contact")
    public String contact() {
        return "contact";
    }

    @RequestMapping("/service")
    public String service() {
        return "service/service";
    }

    @RequestMapping("/price")
    public String price() {
        return "service/price";
    }

    @RequestMapping("/area")
    public String area() {
        return "service/area";
    }

    @RequestMapping("/qa")
    public String qa() {
        return "qa";
    }

    @RequestMapping("/terms")
    public String terms() {
        return "terms";
    }

    @RequestMapping("/nondisclosure")
    public String nondisclosure() {
        return "nondisclosure";
    }
}
