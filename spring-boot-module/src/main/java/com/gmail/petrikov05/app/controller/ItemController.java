package com.gmail.petrikov05.app.controller;

import java.util.List;
import javax.validation.Valid;

import com.gmail.petrikov05.app.service.ItemService;
import com.gmail.petrikov05.app.service.ShopService;
import com.gmail.petrikov05.app.service.model.ItemDTO;
import com.gmail.petrikov05.app.service.model.ShopDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;
    private final ShopService shopService;

    public ItemController(ItemService itemService, ShopService shopService) {
        this.itemService = itemService;
        this.shopService = shopService;
    }

    @GetMapping
    public String showItemsPage(@RequestParam Integer page, Model model) {
        model.addAttribute("page", page);
        Long pages = itemService.getQuantityPage();
        model.addAttribute("pages", pages);
        List<ItemDTO> items = itemService.getItems(page);
        model.addAttribute("items", items);
        return "items";
    }

    @GetMapping("/add")
    public String showAddPage(Model model) {
        List<ShopDTO> shops = shopService.getAllShops();
        model.addAttribute("shops", shops);
        return "item_add";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute(name = "item") ItemDTO item,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("item", item);
            return "item_add";
        }
        itemService.add(item);
        return "redirect:/home";
    }

    @GetMapping("/delete/{id}")
    public String deleteItem(@PathVariable Long id) {
        itemService.delete(id);
        return "redirect:/items";
    }

    @GetMapping("/view/{id}")
    public String viewItem(@PathVariable Long id, Model model) {
        ItemDTO item = itemService.getItem(id);
        model.addAttribute("item", item);
        return "item";
    }

    @PostMapping("/filter")
    public String getFilteredShops(
            @RequestParam String name,
            @RequestParam Integer page,
            Model model
    ) {
        Long pages = itemService.getQuantityPage();
        model.addAttribute("pages", pages);
        model.addAttribute("page", page);
        List<ItemDTO> items = itemService.findItemsByName(name, page);
        model.addAttribute("items", items);
        return "items";
    }

}
