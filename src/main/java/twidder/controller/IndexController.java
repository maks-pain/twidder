package twidder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by maks-pain on 12/15/15
 */

@Controller
public class IndexController {

    @RequestMapping("/")
    public String indexPage(){
        System.out.println("\n>>>\t\t\tIndex controller call!\n");
        return "/app/app.html";
    }


}
