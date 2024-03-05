package cycleTest.cycleDemo.controller;

import cycleTest.cycleDemo.dto.PageableDTO;
import cycleTest.cycleDemo.dto.PromoPackageDTO;
import cycleTest.cycleDemo.service.PromoPackageService;
import cycleTest.cycleDemo.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/api/promoPackage")
@Slf4j
public class PromoPackageController {

    @Autowired
    PromoPackageService promoPackageService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/page")
    public ResponseEntity<?> getAllPromoPackages(@Param("name") String name, @PageableDefault Pageable pageable){

        log.debug("start getting promoPackage pages::");
        PageableDTO pageableDTO=promoPackageService.getAllPackages(name,pageable);
        log.debug("end getting promoPackage pages::");
        return new ResponseEntity<>(pageableDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addPromoPackage(@RequestBody PromoPackageDTO promoPackageDTO, Errors errors){

        log.debug("start adding promoPackage::");
        if (errors.hasErrors()){
            return new ResponseEntity<>(CommonUtils.getFieldErrors(errors), HttpStatus.BAD_REQUEST);
        }
        promoPackageService.addPromoPackage(promoPackageDTO);
        log.debug("end adding promoPackage::");
        return new ResponseEntity<>(CommonUtils.returnSuccessfulMessage("successfully added promoPackage."), HttpStatus.OK);
    }


    @PutMapping
    public ResponseEntity<?> updatePromoPackage(@RequestBody PromoPackageDTO promoPackageDTO, Errors errors){

        log.debug("start updating promoPackage::");
        if (errors.hasErrors()){
            return new ResponseEntity<>(CommonUtils.getFieldErrors(errors), HttpStatus.BAD_REQUEST);
        }
        promoPackageService.updatePromoPackage(promoPackageDTO);
        log.debug("end updating promoPackage::");
        return new ResponseEntity<>(CommonUtils.returnSuccessfulMessage("successfully updated promoPackage."), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPromoPackageDetails(@PathVariable("id") Long id){

        log.debug("start getting promoPackage By Id::");
        PromoPackageDTO promoPackageDTO=promoPackageService.getPromoPackageDetails(id);
        log.debug("end getting promoPackage By Id::");
        return new ResponseEntity<>(promoPackageDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/active")
    public ResponseEntity<?> getAllActivePromoPackages(){

        log.debug("start getting active promoPackages::");
        List<PromoPackageDTO> promoPackageDTOList=promoPackageService.getAllActivePromoPackages();
        log.debug("end getting active promoPackages:;");
        return new ResponseEntity<>(promoPackageDTOList, HttpStatus.OK);
    }
}

