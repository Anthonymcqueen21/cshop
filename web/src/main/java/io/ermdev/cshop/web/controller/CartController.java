package io.ermdev.cshop.web.controller;

import io.ermdev.cshop.data.service.ItemService;
import io.ermdev.cshop.data.entity.Item;
import io.ermdev.cshop.exception.EntityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes({"cartItems"})
public class CartController {

    private ItemService itemService;

    @Autowired
    public CartController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("cart/add")
    public String addToCart(@RequestParam("itemId") Long itemId, ModelMap modelMap) {
        List<Item> items = new ArrayList<>();
        Object sessionObject = modelMap.get("cartItems");
        if(sessionObject != null) {
            for (Object item : (ArrayList) sessionObject) {
                items.add((Item) item);
            }
        }
        try {
            items.add(itemService.findById(itemId));
        } catch (EntityException e) {
            e.printStackTrace();
        }
        modelMap.addAttribute("cartItems", items);
        return "header";
    }

    @PostMapping("cart/remove")
    public String removeToCart(@RequestParam("itemId") Long itemId, ModelMap modelMap) {
        List<Item> items = new ArrayList<>();
        Object sessionObject = modelMap.get("cartItems");
        if(sessionObject != null) {
            for (Object object : (ArrayList) sessionObject) {
                final Item item = (Item) object;
                if(!item.getId().equals(itemId))
                    items.add((Item) item);
            }
        }
        modelMap.addAttribute("cartItems", items);
        return "header";
    }
}
