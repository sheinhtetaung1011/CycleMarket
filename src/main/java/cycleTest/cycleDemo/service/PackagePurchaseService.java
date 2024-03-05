package cycleTest.cycleDemo.service;

import cycleTest.cycleDemo.dto.PackagePurchaseDTO;
import cycleTest.cycleDemo.dto.PageableDTO;
import cycleTest.cycleDemo.dto.PromoPackageDTO;
import cycleTest.cycleDemo.entity.PackagePurchase;
import cycleTest.cycleDemo.entity.PromoPackage;
import cycleTest.cycleDemo.entity.User;
import cycleTest.cycleDemo.enums.ProductStatus;
import cycleTest.cycleDemo.enums.PurchaseStatus;
import cycleTest.cycleDemo.enums.Status;
import cycleTest.cycleDemo.enums.UserRole;
import cycleTest.cycleDemo.exception.BadRequestException;
import cycleTest.cycleDemo.repository.PackagePurchaseRepository;
import cycleTest.cycleDemo.security.CustomUserDetails;
import cycleTest.cycleDemo.service.UserService;
import cycleTest.cycleDemo.specs.PackagePurchaseSpecs;
import cycleTest.cycleDemo.utils.CommonUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PackagePurchaseService {

    @Autowired
    PackagePurchaseRepository packagePurchaseRepository;

    @Autowired
    UserService userService;

    @Autowired
    PromoPackageService promoPackageService;

    public List<PackagePurchaseDTO> getAllExpirePackages() {

        List<PackagePurchase> packagePurchases = packagePurchaseRepository.getAllExpirePackages();
        List<PackagePurchaseDTO> purchaseDTOList = CommonUtils.getDTOList(packagePurchases, PackagePurchaseDTO::new);
        return purchaseDTOList;
    }

    public PageableDTO getAllPackages(CustomUserDetails customUserDetails, PurchaseStatus purchaseStatus
            , String packageName, Pageable pageable) {

        Long userId = null;
        User currentUser = userService.findByName(customUserDetails.getUsername()).get();
        if (currentUser.getUserRole().equals(UserRole.USER)) {
            userId = currentUser.getId();
        }

        Specification<PackagePurchase> packagePurchaseSpecs = PackagePurchaseSpecs.getAllPackages(userId, purchaseStatus, packageName);
        Page<PackagePurchase> packagePurchasePage = packagePurchaseRepository.findAll(packagePurchaseSpecs, pageable);
        List<PackagePurchase> packagePurchaseList = packagePurchasePage.getContent();
        List<PackagePurchaseDTO> packagePurchaseDTOS = CommonUtils.getDTOList(packagePurchaseList, PackagePurchaseDTO::new);
        return new PageableDTO(packagePurchaseDTOS, packagePurchasePage);
    }


    public PackagePurchase getPackagePurchaseById(Long id) {
        return CommonUtils.getById(id, packagePurchaseRepository);
    }

    public PackagePurchaseDTO getPackagePurchaseDetail(Long id) {
        return new PackagePurchaseDTO(getPackagePurchaseById(id));
    }

    public Integer getProductPendingCount(){
        return packagePurchaseRepository.countByPurchaseStatus(PurchaseStatus.REQUESTED);
    }

    public void purchasePackage(CustomUserDetails customerUserDetails,@Valid  PackagePurchaseDTO packagePurchaseDTO) {

        User currentUser=userService.findByName(customerUserDetails.getUsername()).get();

        Optional<PackagePurchase> activeUser=packagePurchaseRepository.findByUserIdAndPurchaseStatus(currentUser.getId(),PurchaseStatus.ACTIVE);
        if (activeUser.isPresent()){
            throw new BadRequestException("user has active package..");
        }

        Optional<PackagePurchase> requestUser=packagePurchaseRepository.findByUserIdAndPurchaseStatus(currentUser.getId(),PurchaseStatus.REQUESTED);
        if (requestUser.isPresent()){
            throw new BadRequestException("user had already request a package.");
        }

        PromoPackage promoPackage=promoPackageService.getPromoPackageById(packagePurchaseDTO.getPackageId());

            PackagePurchase packagePurchase=new PackagePurchase();

            packagePurchase.setUser(currentUser);
            packagePurchase.setPackageId(promoPackage.getId());
            packagePurchase.setPackageAmount(promoPackage.getAmount());
            packagePurchase.setPackageDuration(promoPackage.getDuration());
            packagePurchase.setPackageName(promoPackage.getName());
            packagePurchase.setPurchaseStatus(PurchaseStatus.REQUESTED);
            packagePurchase.setTransactionNo(packagePurchaseDTO.getTransactionNo());
            packagePurchaseRepository.save(packagePurchase);
    }

    public void confirmPackage(@Valid PackagePurchaseDTO packagePurchaseDTO){

        PackagePurchase packagePurchase=getPackagePurchaseById(packagePurchaseDTO.getId());

        if (packagePurchase.getPurchaseStatus().equals(PurchaseStatus.REQUESTED)){
            packagePurchase.setPurchaseStatus(packagePurchaseDTO.getPurchaseStatus());
            packagePurchase.setConfirmDate(new Date());
        }

        if (packagePurchase.getPurchaseStatus().equals(PurchaseStatus.ACTIVE)){
            Date expireDate=CommonUtils.addDateByDay(packagePurchaseDTO.getConfimDate(),packagePurchaseDTO.getPackageDuration());
            packagePurchase.setExpireDate(expireDate);
        }

    }

    public List<PackagePurchaseDTO> getAvailablePackages() {

        List<PackagePurchase> packagePurchaseList=packagePurchaseRepository.findByPurchaseStatus(PurchaseStatus.ACTIVE);
        List<PackagePurchaseDTO> packagePurchaseDTOS=CommonUtils.getDTOList(packagePurchaseList,PackagePurchaseDTO::new);

        return packagePurchaseDTOS;
    }
}

