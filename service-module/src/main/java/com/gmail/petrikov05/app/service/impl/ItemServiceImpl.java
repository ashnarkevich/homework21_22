package com.gmail.petrikov05.app.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.transaction.Transactional;

import com.gmail.petrikov05.app.repository.ItemRepository;
import com.gmail.petrikov05.app.repository.ShopRepository;
import com.gmail.petrikov05.app.repository.model.Item;
import com.gmail.petrikov05.app.repository.model.ItemDetails;
import com.gmail.petrikov05.app.repository.model.Shop;
import com.gmail.petrikov05.app.service.ItemService;
import com.gmail.petrikov05.app.service.model.ItemDTO;
import com.gmail.petrikov05.app.service.model.ShopDTO;
import com.gmail.petrikov05.app.service.util.PageUtil;
import org.springframework.stereotype.Service;

import static com.gmail.petrikov05.app.service.constant.PageConstant.NUMBER_BY_PAGE;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ShopRepository shopRepository;

    public ItemServiceImpl(ItemRepository itemRepository, ShopRepository shopRepository) {
        this.itemRepository = itemRepository;
        this.shopRepository = shopRepository;
    }

    @Override
    @Transactional
    public List<ItemDTO> getItems(Integer page) {
        Integer startPosition = PageUtil.getStarterPosition(page);
        List<Item> items = itemRepository.findWithPagination(startPosition, NUMBER_BY_PAGE);
        return items.stream()
                .map(this::convertObjectToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void add(ItemDTO itemDTO) {
        Item item = convertDTOToObject(itemDTO);
        itemRepository.persist(item);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Item item = itemRepository.findById(id);
        itemRepository.remove(item);
    }

    @Override
    @Transactional
    public ItemDTO getItem(Long id) {
        Item item = itemRepository.findById(id);
        return convertObjectToDTOWithShops(item);
    }

    @Override
    public Long getQuantityPage() {
        Long quantityRow = itemRepository.getQuantityRow();
        Long quantityPages = quantityRow / NUMBER_BY_PAGE;
        if (quantityRow % NUMBER_BY_PAGE != 0) {
            quantityPages++;
        }
        return quantityPages;
    }

    @Override
    @Transactional
    public List<ItemDTO> findItemsByName(String name, Integer page) {
        Integer startPosition = PageUtil.getStarterPosition(page);
        List<Item> items = itemRepository.findItemsByName(name, startPosition, NUMBER_BY_PAGE);
        return items.stream()
                .map(this::convertObjectToDTO)
                .collect(Collectors.toList());
    }

    private ItemDTO convertObjectToDTOWithShops(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(item.getId());
        itemDTO.setName(item.getName());
        itemDTO.setDescription(item.getDescription());
        itemDTO.setPrice(item.getItemDetails().getPrice());
        Set<ShopDTO> shopDTOs = new HashSet<>();
        for (Shop shop : item.getShops()) {
            ShopDTO shopDTO = convertShopObjectToDTO(shop);
            shopDTOs.add(shopDTO);
        }
        itemDTO.setShops(shopDTOs);
        return itemDTO;
    }

    private ShopDTO convertShopObjectToDTO(Shop shop) {
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setName(shop.getName());
        shopDTO.setLocation(shop.getLocation());
        return shopDTO;
    }

    private ItemDTO convertObjectToDTO(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(item.getId());
        itemDTO.setName(item.getName());
        itemDTO.setDescription(item.getDescription());

        ItemDetails itemDetails = item.getItemDetails();
        itemDTO.setPrice(itemDetails.getPrice());
        return itemDTO;
    }

    private Item convertDTOToObject(ItemDTO itemDTO) {
        Item item = new Item();
        item.setName(itemDTO.getName());
        item.setDescription(itemDTO.getDescription());

        ItemDetails itemDetails = new ItemDetails();
        itemDetails.setPrice(itemDTO.getPrice());
        itemDetails.setItem(item);
        item.setItemDetails(itemDetails);

        Set<Shop> shops = new HashSet<>();
        for (Long shopId : itemDTO.getShopIds()) {
            Shop shop = shopRepository.findById(shopId);
            shops.add(shop);
        }
        item.setShops(shops);
        return item;
    }

}
