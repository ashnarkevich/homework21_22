package com.gmail.petrikov05.app.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;

import com.gmail.petrikov05.app.repository.ShopRepository;
import com.gmail.petrikov05.app.repository.model.Shop;
import com.gmail.petrikov05.app.service.ShopService;
import com.gmail.petrikov05.app.service.model.ShopDTO;
import org.springframework.stereotype.Service;

@Service
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;

    public ShopServiceImpl(ShopRepository shopRepository) {this.shopRepository = shopRepository;}

    @Override
    public List<ShopDTO> getShop() {
        List<Shop> shops = shopRepository.findAll();
        List<ShopDTO> shopsDTO = shops.stream().map(this::convertObjectToDTO).collect(Collectors.toList());
        return shopsDTO;
    }

    @Override
    @Transactional
    public ShopDTO add(ShopDTO shopDTO) {
        Shop shop = convertDTOToObject(shopDTO);
        shopRepository.persist(shop);
        return shopDTO;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Shop shop = shopRepository.findById(id);
        shopRepository.remove(shop);
    }

    private Shop convertDTOToObject(ShopDTO shopDTO) {
        Shop shop = new Shop();
        shop.setName(shopDTO.getName());
        shop.setLocation(shopDTO.getLocation());
        return shop;
    }

    private ShopDTO convertObjectToDTO(Shop shop) {
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setId(shop.getId());
        shopDTO.setName(shop.getName());
        shopDTO.setLocation(shop.getLocation());
        return shopDTO;
    }

}
