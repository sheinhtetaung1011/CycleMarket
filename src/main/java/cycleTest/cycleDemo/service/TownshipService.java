package cycleTest.cycleDemo.service;

import cycleTest.cycleDemo.dto.PageableDTO;
import cycleTest.cycleDemo.dto.TownshipDTO;
import cycleTest.cycleDemo.entity.City;
import cycleTest.cycleDemo.entity.Township;
import cycleTest.cycleDemo.exception.BadRequestException;
import cycleTest.cycleDemo.exception.ResourceNotFoundException;
import cycleTest.cycleDemo.repository.CityRepository;
import cycleTest.cycleDemo.repository.TownshipRepository;
import cycleTest.cycleDemo.utils.CommonUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

@Service
public class TownshipService {

    @Autowired
    TownshipRepository townshipRepository;

    @Autowired
    private CityService cityService;

    public PageableDTO getTownshipsByCity(Long cityId, Pageable pageable) {

        Page<Township> townshipPage = townshipRepository.findByCityId(cityId, pageable);
        List<Township> townshipList = townshipPage.getContent();
        List<TownshipDTO> townshipDTOList = CommonUtils.getDTOList(townshipList, TownshipDTO::new);

        PageableDTO pageableDTO = new PageableDTO(townshipDTOList, townshipPage);
        return pageableDTO;
    }

    public List<TownshipDTO> getAllTownshipByCity(Long cityId) {

        List<Township> townshipList = townshipRepository.findByCityId(cityId);
        List<TownshipDTO> townshipDTOList = CommonUtils.getDTOList(townshipList, TownshipDTO::new);
        return townshipDTOList;

    }


    @Transactional(rollbackFor = Exception.class)
    public void addTownship(TownshipDTO townshipDTO) {

        String name=townshipDTO.getName();
        Long cityId=townshipDTO.getCityId();

        City city=cityService.getCityById(cityId);

        checkTownshipDuplicate(name,cityId,null);

        Township township=new Township();
        township.setCity(city);
        township.setName(name);
        townshipRepository.save(township);
    }

    public void checkTownshipDuplicate(String name,Long cityId,Long id) {

        Optional<Township> township = townshipRepository.findByCityIdAndName(cityId, name);
        CommonUtils.checkDuplicate(township,id,"Township");
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateTownship( TownshipDTO townshipDTO){
        Long id=townshipDTO.getId();
        Township township=CommonUtils.getById(id,townshipRepository);

        checkTownshipDuplicate(townshipDTO.getName(),townshipDTO.getCityId(),id);

        township.setName(townshipDTO.getName());
        townshipRepository.save(township);
    }

    public void checkTownshipValidate(Township township,Long cityId){

        if(township.getCity().getId()!=cityId){
            throw  new BadRequestException("City is not supported in this City");
        }
    }


    public Township getTownshipById(Long id){
        return CommonUtils.getById(id,townshipRepository);
    }

    public TownshipDTO getTownship(Long id){

        Township township=CommonUtils.getById(id,townshipRepository);
        return  new TownshipDTO(township);
    }
}
