package io.ermdev.ecloth.web.controller;

import io.ermdev.ecloth.data.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"cartItems"})
@RequestMapping
public class HomeController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    public String showHomePage(ModelMap modelMap) {
        return "home";
    }
}