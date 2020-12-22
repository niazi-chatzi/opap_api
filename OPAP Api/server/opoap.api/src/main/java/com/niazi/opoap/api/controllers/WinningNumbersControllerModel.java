package com.niazi.opoap.api.controllers;

import com.niazi.opoap.api.services.WinningNumbersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("")
public class WinningNumbersControllerModel {
    private final WinningNumbersService winningNumbersService;

    public WinningNumbersControllerModel(WinningNumbersService winningNumbersService) {
        this.winningNumbersService = winningNumbersService;
    }

    @GetMapping(value = {"", "/"})
    public String getWinningNumbersView(Model model, @RequestParam(value = "sort", defaultValue = "") String sort) {
        if(sort.equals("true")) {
            model.addAttribute("winningNumbers", winningNumbersService.getAllSorted());
        } else {
            model.addAttribute("winningNumbers", winningNumbersService.getAll());
        }

        return "winning-numbers-view";
    }

    @GetMapping(value = "/phone")
    public String phoneView() { return "phone-view";}
}
