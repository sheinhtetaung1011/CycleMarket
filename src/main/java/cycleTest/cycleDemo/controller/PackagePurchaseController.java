package cycleTest.cycleDemo.controller;

import cycleTest.cycleDemo.annotation.CurrentUser;
import cycleTest.cycleDemo.dto.PackagePurchaseDTO;
import cycleTest.cycleDemo.dto.PageableDTO;
import cycleTest.cycleDemo.enums.PurchaseStatus;
import cycleTest.cycleDemo.security.CustomUserDetails;
import cycleTest.cycleDemo.service.PackagePurchaseService;
import cycleTest.cycleDemo.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/packagePurchase")
@Slf4j
public class PackagePurchaseController {

    @Autowired
    PackagePurchaseService packagePurchaseService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/expirePackage")
    public ResponseEntity<?> getAllExpiredPackages() {

        log.debug("start getting expired packages::");
        List<PackagePurchaseDTO> purchaseDTOList = packagePurchaseService.getAllExpirePackages();
        log.debug("end getting expired packages::");
        return new ResponseEntity<>(purchaseDTOList, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/allPackage")
    public ResponseEntity<?> getAllPackages(@CurrentUser CustomUserDetails customUserDetails
            , @RequestBody PurchaseStatus purchaseStatus
            , @Param("packageName") String packageName
            , @PageableDefault Pageable pageable) {

        log.debug("start getting all packages::");
        PageableDTO pageableDTO = packagePurchaseService.getAllPackages(customUserDetails, purchaseStatus, packageName, pageable);
        log.debug("end getting all packages::");
        return new ResponseEntity<>(pageableDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping
    public ResponseEntity<?> purchasePackage(@CurrentUser CustomUserDetails customUserDetails, @RequestBody PackagePurchaseDTO packagePurchaseDTO, Errors errors) {

        log.debug("start adding packagePurchase::");
        if (errors.hasErrors()) {
            return new ResponseEntity<>(CommonUtils.getFieldErrors(errors), HttpStatus.BAD_REQUEST);
        }
        packagePurchaseService.purchasePackage(customUserDetails, packagePurchaseDTO);
        log.debug("end adding package::");
        return new ResponseEntity<>(CommonUtils.returnSuccessfulMessage("successfully purchase package."), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping
    public ResponseEntity<?> confirmPackage(@RequestBody PackagePurchaseDTO packagePurchaseDTO, Errors errors) {

        log.debug("start confirming::");
        if (Objects.isNull(packagePurchaseDTO.getPurchaseStatus())) {
            errors.rejectValue("purchaseStatus", "error.name", "must not be bull");
        }
        if (errors.hasErrors()) {
            return new ResponseEntity<>(CommonUtils.getFieldErrors(errors), HttpStatus.BAD_REQUEST);
        }
        packagePurchaseService.confirmPackage(packagePurchaseDTO);
        log.debug("end confirming::");
        return new ResponseEntity<>(CommonUtils.returnSuccessfulMessage("confirmed."), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getPurchaseDetails(@PathVariable("id") Long id) {

        log.debug("start getting  package::");
        PackagePurchaseDTO packagePurchaseDTO = packagePurchaseService.getPackagePurchaseDetail(id);
        log.debug("end getting  package::");
        return new ResponseEntity<>(packagePurchaseDTO, HttpStatus.OK);

    }


    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/availablePackage")
    public ResponseEntity<?> getAvailablePackages() {

        log.debug("start getting available packages::");
        List<PackagePurchaseDTO> packagePurchaseDTOList = packagePurchaseService.getAvailablePackages();
        log.debug("end getting available packages::");
        return new ResponseEntity<>(packagePurchaseDTOList, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/pendingCount")
    public ResponseEntity<?> getPendingCount(){
        log.debug("start getting pending count::");
        Integer count=packagePurchaseService.getProductPendingCount();
        log.debug("end getting pending count::");
        return new ResponseEntity<>(count,HttpStatus.OK);
    }
}
