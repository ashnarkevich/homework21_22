package com.gmail.petrikov05.app.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;

import com.gmail.petrikov05.app.repository.ShopRepository;
import com.gmail.petrikov05.app.repository.model.Shop;
import com.gmail.petrikov05.app.service.ShopService;
import com.gmail.petrikov05.app.service.model.ShopDTO;
import com.gmail.petrikov05.app.service.util.PageUtil;
import org.springframework.stereotype.Service;

import static com.gmail.petrikov05.app.service.constant.PageConstant.NUMBER_BY_PAGE;

@Service
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;

    public ShopServiceImpl(ShopRepository shopRepository) {this.shopRepository = shopRepository;}

    @Override
    @Transactional
    public List<ShopDTO> getAllShops() {
        List<Shop> shops = shopRepository.findAll();
        return shops.stream()
                .map(this::convertObjectToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<ShopDTO> getShops(Integer page) {
        Integer startPosition = PageUtil.getStarterPosition(page);
        List<Shop> shops = shopRepository.findWithPagination(startPosition, NUMBER_BY_PAGE);
        return shops.stream()
                .map(this::convertObjectToDTO)
                .collect(Collectors.toList());
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
    public Long getQuantityPage() {
        Long quantityRow = shopRepository.getQuantityRow();
        Long quantityPages = quantityRow / NUMBER_BY_PAGE;
        if (quantityRow % NUMBER_BY_PAGE != 0) {
            quantityPages++;
        }
        return quantityPages;
    }

    @Override
    @Transactional
    public List<ShopDTO> findShopsByLocation(String location) {
        List<Shop> shops = shopRepository.getShopsByLocation(location);
        return shops.stream()
                .map(this::convertObjectToDTO)
                .collect(Collectors.toList());
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
