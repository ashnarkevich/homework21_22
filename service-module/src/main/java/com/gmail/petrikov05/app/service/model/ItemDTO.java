package com.gmail.petrikov05.app.service.model;

import java.math.BigDecimal;
import java.util.Set;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.gmail.petrikov05.app.repository.model.Shop;

public class ItemDTO {

    private Long id;
    @NotNull(message = "The field cannot be empty")
    @Size(min = 1, max = 50, message = "The field size cannot be more 50")
    private String name;
    @NotNull(message = "The field cannot be empty")
    @Size(min = 1, max = 255, message = "The field size cannot be more 50")
    private String description;
    @NotNull(message = "The field cannot be empty")
    private BigDecimal price;
    @NotNull(message = "The field cannot be empty")
    private Long shopId;
    private Set<Shop> shops;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setShops(Set<Shop> shops) {
        this.shops = shops;
    }

    public Set<Shop> getShops() {
        return shops;
    }

}
