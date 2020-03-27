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
import org.springframework.stereotype.Service;

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
    public List<ItemDTO> getItemList() {
        List<Item> items = itemRepository.findAll();
        return items.stream().map(this::convertObjectToDTO).collect(Collectors.toList());
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
        return getItemDTOFromObject(item);
    }

    private ItemDTO getItemDTOFromObject(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(item.getId());
        itemDTO.setName(item.getName());
        itemDTO.setDescription(item.getDescription());
        itemDTO.setPrice(item.getItemDetails().getPrice());
        itemDTO.setShops(item.getShops());
        return itemDTO;
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
        Shop shop = shopRepository.findById(itemDTO.getShopId());
        shops.add(shop);
        item.setShops(shops);
        return item;
    }

}
