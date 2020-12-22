package com.niazi.opoap.api.controllers.error;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
public class MyErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(Model model, HttpServletResponse response) {
        model.addAttribute("msg", response.getStatus());
        return "error";
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}