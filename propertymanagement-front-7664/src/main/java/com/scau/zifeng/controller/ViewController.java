package com.scau.zifeng.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {
    @RequestMapping(value="/login")
    public String login(){
        return "login";
    }

    @RequestMapping(value="/user")
    public String user(){
        return "user";
    }

    @RequestMapping(value="/admin")
    public String admin(){
        return "admin";
    }

    @RequestMapping(value="/management")
    public String management(){
        return "management";
    }

    @RequestMapping(value="/head")
    public String head(){
        return "head";
    }

    @RequestMapping(value="/addBoard")
    public String addBoard(){
        return "addBoard";
    }

    @RequestMapping(value="/addComplaint")
    public String addComplaint(){
        return "addComplaint";
    }

    @RequestMapping(value="/addPay")
    public String addPay(){
        return "addPay";
    }

    @RequestMapping(value="/addPurchase")
    public String addPurchase(){
        return "addPurchase";
    }

    @RequestMapping(value="/addUser")
    public String addUser(){
        return "addUser";
    }

    @RequestMapping(value="/board")
    public String board(){
        return "board";
    }

    @RequestMapping(value="/changePwd")
    public String changePwd(){
        return "changePwd";
    }

    @RequestMapping(value="/complaintDeal")
    public String complaintDeal(){
        return "complaintDeal";
    }

    @RequestMapping(value="/payHistory")
    public String payHistory(){
        return "payHistory";
    }

    @RequestMapping(value="/payList")
    public String payList(){
        return "payList";
    }

    @RequestMapping(value="/payOneList")
    public String payOneList(){
        return "payOneList";
    }

    @RequestMapping(value="/purchase")
    public String purchase(){
        return "purchase";
    }

    @RequestMapping(value="/purchaseDeal")
    public String purchaseDeal(){
        return "purchaseDeal";
    }

    @RequestMapping(value="/purchaseHistory")
    public String purchaseHistory(){
        return "purchaseHistory";
    }

    @RequestMapping(value="/repairDeal")
    public String repairDeal(){
        return "repairDeal";
    }

    @RequestMapping(value="/repairHistory")
    public String repairHistory(){
        return "repairHistory";
    }

    @RequestMapping(value="/repairTime")
    public String repairTime(){
        return "repairTime";
    }

    @RequestMapping(value="/roles")
    public String roles(){
        return "roles";
    }


    @RequestMapping(value="/success")
    public String success(){
        return "success";
    }
    @RequestMapping(value="/unCheck")
    public String unCheck(){
        return "unCheck";
    }
    @RequestMapping(value="/unPayList")
    public String unPayList(){
        return "unPayList";
    }
    @RequestMapping(value="/unPayOneList")
    public String unPayOneList(){
        return "unPayOneList";
    }
    @RequestMapping(value="/users")
    public String users(){
        return "users";
    }
    @RequestMapping(value="/selfMessage")
    public String selfMessage(){
        return "selfMessage";
    }


}
