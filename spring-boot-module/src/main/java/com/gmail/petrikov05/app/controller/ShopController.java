package com.gmail.petrikov05.app.controller;

import java.util.List;
import javax.validation.Valid;

import com.gmail.petrikov05.app.service.ShopService;
import com.gmail.petrikov05.app.service.model.ShopDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/shops")
public class ShopController {

    private final ShopService shopService;

    public ShopController(ShopService shopService) {this.shopService = shopService;}

    @GetMapping
    public String showShopsPage(@RequestParam(value = "page", defaultValue = "1") Integer page, Model model) {
        Long pages = shopService.getQuantityPage();
        model.addAttribute("pages", pages);
        model.addAttribute("page", page);
        List<ShopDTO> shops = shopService.getShops(page);
        model.addAttribute("shops", shops);
        return "shops";
    }

    @GetMapping("/add")
    public String showAddPage() {
        return "shop_add";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute(name = "shop") ShopDTO shop,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("shop", shop);
            return "shop_add";
        }
        shopService.add(shop);
        return "redirect:/shops";
    }

    @PostMapping("/filter")
    public String getFilteredShops(
            @RequestParam String location,
            @RequestParam Integer page,
            Model model
    ) {
        Long pages = shopService.getQuantityPage();
        model.addAttribute("pages", pages);
        model.addAttribute("page", page);
        List<ShopDTO> shops = shopService.findShopsByLocation(location);
        model.addAttribute("shops", shops);
        return "shops";
    }

}
