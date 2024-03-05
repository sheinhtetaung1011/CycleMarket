package cycleTest.cycleDemo.service;

import cycleTest.cycleDemo.dto.PageableDTO;
import cycleTest.cycleDemo.dto.ShopDTO;
import cycleTest.cycleDemo.entity.City;
import cycleTest.cycleDemo.entity.Shop;
import cycleTest.cycleDemo.entity.Township;
import cycleTest.cycleDemo.entity.User;
import cycleTest.cycleDemo.exception.ResourceNotFoundException;
import cycleTest.cycleDemo.repository.ShopRepository;
import cycleTest.cycleDemo.repository.TownshipRepository;
import cycleTest.cycleDemo.specs.ShopSpecs;
import cycleTest.cycleDemo.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ShopService {

    @Autowired
    ShopRepository shopRepository;

    @Autowired
    CityService cityService;

    @Autowired
    TownshipService townshipService;

    @Autowired
    UserService userService;

    @Autowired
    TownshipRepository townshipRepository;

    public PageableDTO getAllShops(String name, Long userId, Boolean showInHome, Pageable pageable) {

        Specification<Shop> shopSpecs = ShopSpecs.getAllShops(name, userId, showInHome);
        Page<Shop> shopPage = shopRepository.findAll(shopSpecs, pageable);
        List<Shop> shopList = shopPage.getContent();

        List<ShopDTO> shopDTOList = CommonUtils.getDTOList(shopList, ShopDTO::new);

        return new PageableDTO(shopDTOList, shopPage);
    }


    public ShopDTO getShopByUser(Long userId) {
        ShopDTO shopDTO = null;
        Optional<Shop> shop = shopRepository.findByUserId(userId);
        if (shop.isPresent()) {
            shopDTO=new ShopDTO(shop.get());
        }
        return shopDTO;
    }


    public ShopDTO getShop(Long id) {

        Shop shop = CommonUtils.getById(id, shopRepository);
        return new ShopDTO(shop);
    }

    @Transactional(rollbackFor = Exception.class)
    public void addShop(ShopDTO shopDTO){

        City city=cityService.getCityById(shopDTO.getCityId());
        Township township=townshipService.getTownshipById(shopDTO.getTownshipId());
        User user=userService.getUserById(shopDTO.getUserId());

        Shop shop =new Shop();
        shop.setName(shopDTO.getName());
        shop.setCity(city);
        shop.setTownship(township);
        shop.setUser(user);
        shop.setAddress(shopDTO.getAddress());
        shop.setDescription(shopDTO.getDescription());
        shop.setShowInHome(shopDTO.getShowInHome());
        shop.setImageUrl(shopDTO.getImageUrl());

        shopRepository.save(shop);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateShop(ShopDTO shopDTO) {

        City city=cityService.getCityById(shopDTO.getCityId());
        Township township=townshipService.getTownshipById(shopDTO.getTownshipId());
        User user=userService.getUserById(shopDTO.getUserId());

        Long id=shopDTO.getId();
        Shop shop=CommonUtils.getById(id,shopRepository);
        shop.setName(shopDTO.getName());
        shop.setUser(user);
        shop.setCity(city);
        shop.setTownship(township);
        shop.setDescription(shopDTO.getDescription());
        shop.setAddress(shopDTO.getAddress());
        shop.setImageUrl(shopDTO.getImageUrl());
        shop.setShowInHome(shopDTO.getShowInHome());
        shopRepository.save(shop);
    }

    public List<Shop> getShopsByTownship(Long townshipId){

        Township township=CommonUtils.getById(townshipId,townshipRepository);

        List<Shop> shopList=township.getShopList();
        return shopList;

    }
}
