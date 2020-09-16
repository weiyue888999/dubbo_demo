package pers.xiaomo.demo.dubboboot.controller;

import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.xiaomo.demo.dubbo.service.MessageService;

import java.util.Map;

@Controller
@RequestMapping("dubbo")
public class DubboTestController {

    @Reference
    private MessageService messageService;

    @GetMapping("index")
    public String index(){
        System.out.println("index");
        System.out.println("...");
        return "dubbo";
    }

    @PostMapping("call")
    @ResponseBody
    public Map<String,String> call(String msg){
        return null;
    }


}
