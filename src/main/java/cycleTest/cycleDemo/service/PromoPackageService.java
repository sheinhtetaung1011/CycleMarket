package cycleTest.cycleDemo.service;

import cycleTest.cycleDemo.dto.PageableDTO;
import cycleTest.cycleDemo.dto.PromoPackageDTO;
import cycleTest.cycleDemo.entity.PromoPackage;
import cycleTest.cycleDemo.enums.Status;
import cycleTest.cycleDemo.exception.BadRequestException;
import cycleTest.cycleDemo.repository.PromoPackageRepository;
import cycleTest.cycleDemo.specs.PromoPackageSpecs;
import cycleTest.cycleDemo.utils.CommonUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PromoPackageService {

    @Autowired
    PromoPackageRepository promoPackageRepository;

    public PageableDTO getAllPackages(String name, Pageable pageable){

        Specification<PromoPackage> promoPackageSpecs= PromoPackageSpecs.getAllPromoPackages(name);
        Page<PromoPackage> promoPackagePage=promoPackageRepository.findAll(promoPackageSpecs,pageable);
        List<PromoPackage> promoPackageList=promoPackagePage.getContent();
        List<PromoPackageDTO> promoPackageDTOList= CommonUtils.getDTOList(promoPackageList,PromoPackageDTO::new);

        return new PageableDTO(promoPackageDTOList,promoPackagePage);
    }

    @Transactional(rollbackFor = Exception.class)
    public void addPromoPackage(@Valid PromoPackageDTO promoPackageDTO){

        PromoPackage promoPackage=new PromoPackage();
        promoPackage.setName(promoPackageDTO.getName());
        promoPackage.setAmount(promoPackageDTO.getAmount());
        promoPackage.setDuration(promoPackageDTO.getDuration());
        promoPackage.setStatus(promoPackageDTO.getStatus());

        promoPackageRepository.save(promoPackage);
    }


    @Transactional(rollbackFor = Exception.class)
    public void updatePromoPackage(@Valid PromoPackageDTO promoPackageDTO){

        PromoPackage promoPackage=getPromoPackageById(promoPackageDTO.getId());

        promoPackage.setName(promoPackageDTO.getName());
        promoPackage.setAmount(promoPackageDTO.getAmount());
        promoPackage.setDuration(promoPackageDTO.getDuration());
        promoPackage.setStatus(promoPackageDTO.getStatus());

        promoPackageRepository.save(promoPackage);
    }

    public PromoPackageDTO getPromoPackageDetails(Long id){
        return new PromoPackageDTO(getPromoPackageById(id));
    }

    public PromoPackage getPromoPackageById(Long id){
        return CommonUtils.getById(id,promoPackageRepository);
    }


    public List<PromoPackageDTO> getAllActivePromoPackages(){

        List<PromoPackage> promoPackageList=promoPackageRepository.findByStatus(Status.ACTIVE);
        List<PromoPackageDTO> promoPackageDTOList=CommonUtils.getDTOList(promoPackageList,PromoPackageDTO::new);
        return promoPackageDTOList;
    }
}
