package com.gmail.petrikov05.app.service.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ItemDTO {

    private Long id;
    @NotNull(message = "The field cannot be empty")
    @Size(min = 1, max = 50, message = "The field size cannot be more 50")
    private String name;
    @NotNull(message = "The field cannot be empty")
    @Size(min = 1, max = 255, message = "The field size cannot be more 255")
    private String description;
    @NotNull(message = "The field cannot be empty")
    private BigDecimal price;
    @NotNull(message = "The field cannot be empty")
    private Set<ShopDTO> shops = new HashSet<>();
    private Long[] shopIds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Set<ShopDTO> getShops() {
        return shops;
    }

    public void setShops(Set<ShopDTO> shops) {
        this.shops = shops;
    }

    public Long[] getShopIds() {
        return shopIds;
    }

    public void setShopIds(Long[] shopIds) {
        this.shopIds = shopIds;
    }

}
