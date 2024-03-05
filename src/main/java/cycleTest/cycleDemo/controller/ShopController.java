package cycleTest.cycleDemo.controller;

import cycleTest.cycleDemo.dto.PageableDTO;
import cycleTest.cycleDemo.dto.ShopDTO;
import cycleTest.cycleDemo.entity.Shop;
import cycleTest.cycleDemo.service.ShopService;
import cycleTest.cycleDemo.utils.CommonUtils;
import jakarta.validation.Valid;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/shop")
public class ShopController {

    @Autowired
    ShopService shopService;

    @GetMapping
    public ResponseEntity<?> getAllShops(@Param("name") String name
            , @Param("userId") Long userId
            , @Param("showInHome") Boolean showInHome
            , @PageableDefault(size = 10) Pageable pageable) {

        PageableDTO pageableDTO = shopService.getAllShops(name, userId, showInHome, pageable);
        return new ResponseEntity<>(pageableDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addShop(@Valid @RequestBody ShopDTO shopDTO, Errors errors) {

        if (errors.hasErrors()) {
            return new ResponseEntity<>(CommonUtils.getFieldErrors(errors), HttpStatus.BAD_REQUEST);
        }

        shopService.addShop(shopDTO);
        return new ResponseEntity<>(CommonUtils.returnSuccessfulMessage("Successfully add a shop."), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateShop(@Valid @RequestBody ShopDTO shopDTO,Errors errors){

        if (errors.hasErrors()){
            return  new ResponseEntity<>(CommonUtils.getFieldErrors(errors),HttpStatus.BAD_REQUEST);
        }

        shopService.updateShop(shopDTO);
        return new ResponseEntity<>(CommonUtils.returnSuccessfulMessage("Successfully update a shop."),HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getShopByUser(@PathVariable("userId") Long userId,Errors errors){

        if (errors.hasErrors()){
            return new ResponseEntity<>(CommonUtils.getFieldErrors(errors),HttpStatus.BAD_REQUEST);
        }

        ShopDTO shopDTO=shopService.getShopByUser(userId);
        return new ResponseEntity<>(shopDTO,HttpStatus.OK);
    }

    @GetMapping("/{townshipId}")
    public ResponseEntity<?> getShopsByTownship (@PathVariable("townshipId") Long townshipId,Errors errors){

        if (errors.hasErrors()){
            return  new ResponseEntity<>(CommonUtils.getFieldErrors(errors),HttpStatus.BAD_REQUEST);
        }

        List<Shop> shopList=shopService.getShopsByTownship(townshipId);
        return new ResponseEntity<>(shopList, HttpStatus.OK);

    }


}
