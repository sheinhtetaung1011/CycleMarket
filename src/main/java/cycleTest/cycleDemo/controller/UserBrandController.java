package cycleTest.cycleDemo.controller;

import cycleTest.cycleDemo.dto.PageableDTO;
import cycleTest.cycleDemo.dto.UserBrandDTO;
import cycleTest.cycleDemo.repository.UserBrandRepository;
import cycleTest.cycleDemo.service.UserBrandService;
import cycleTest.cycleDemo.utils.CommonUtils;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/userBrand")
@Slf4j
public class UserBrandController {

    @Autowired
    UserBrandService userBrandService;

    @GetMapping
    public ResponseEntity<?> getAllUserBrands(@Param("name") String name, @PageableDefault(size = 10) Pageable pageable){

        PageableDTO pageableDTO=userBrandService.getAllUserBrands(name,pageable);
        return new ResponseEntity<>(pageableDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/allList")
    public ResponseEntity<?> getUserBrandList(){

        List<UserBrandDTO> userBrandDTOList=userBrandService.getUserBrandList();
        return new ResponseEntity<>(userBrandDTOList,HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<?> addUserBrand(@Valid @RequestBody UserBrandDTO userBrandDTO, Errors errors){

        log.debug("start adding userBrand.");
        if (errors.hasErrors()){
            return new ResponseEntity<>(CommonUtils.getFieldErrors(errors),HttpStatus.BAD_REQUEST);
        }
        userBrandService.addUserBrand(userBrandDTO);

        log.debug("end adding userBrand");
        return new ResponseEntity<>(CommonUtils.returnSuccessfulMessage("UserBrand is added"),HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateUserBrand(@Valid @RequestBody UserBrandDTO userBrandDTO,Errors errors){

        log.debug("start updating userBrand.");
        if (errors.hasErrors()){
            return  new ResponseEntity<>(CommonUtils.getFieldErrors(errors),HttpStatus.BAD_REQUEST);
        }
        userBrandService.updateUserBrand(userBrandDTO);

        log.debug("end updating userBrand.");
        return new ResponseEntity<>(CommonUtils.returnSuccessfulMessage("UserBrand is updated"),HttpStatus.OK);
    }

    @GetMapping("/{brandId}")
    public ResponseEntity<?> getUserBrand(@PathVariable(value = "brandId")Long brandId){

        log.debug("start getting userBrand By id.");

        UserBrandDTO userBrandDTO=userBrandService.getUserBrand(brandId);

        log.debug("end getting userBrand By id.");
        return  new ResponseEntity<>(userBrandDTO,HttpStatus.OK);
    }


}
