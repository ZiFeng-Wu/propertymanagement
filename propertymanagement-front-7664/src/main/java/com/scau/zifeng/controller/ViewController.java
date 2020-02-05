package com.scau.zifeng;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {
    @RequestMapping(value="/login")
    public String getIndex(){
        return "login";
    }

}
