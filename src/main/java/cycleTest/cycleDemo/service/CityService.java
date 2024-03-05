package cycleTest.cycleDemo.service;

import cycleTest.cycleDemo.dto.CityDTO;
import cycleTest.cycleDemo.dto.PageableDTO;
import cycleTest.cycleDemo.entity.City;
import cycleTest.cycleDemo.repository.CityRepository;
import cycleTest.cycleDemo.specs.CitySpecs;
import cycleTest.cycleDemo.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    @Autowired
    CityRepository cityRepository;

    public PageableDTO getAllCities(String name, Pageable pageable) {
        Specification<City> citySpecs = CitySpecs.getAllCities(name);
        Page<City> cityPage = cityRepository.findAll(citySpecs, pageable);
        List<City> cityList = cityPage.getContent();

        List<CityDTO> cityDTOList = CommonUtils.getDTOList(cityList, CityDTO::new);

        PageableDTO pageableDTO = new PageableDTO(cityDTOList, cityPage);
        return pageableDTO;
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveCity(CityDTO cityDTO){

        Optional<City> cityExist=cityRepository.findByName(cityDTO.getName());
        CommonUtils.checkDuplicate(cityExist,cityDTO.getId(),"City name");

        City city=new City();
        city.setName(cityDTO.getName());
        cityRepository.save(city);
    }



    @Transactional(rollbackFor = Exception.class)
    public void updateCity(CityDTO cityDTO) {
        Long id = cityDTO.getId();

        Optional<City> cityExist = cityRepository.findByName(cityDTO.getName());
        CommonUtils.checkDuplicate(cityExist, id, "City's name");

        City city = getCityById(id);

        city.setName(cityDTO.getName());
        cityRepository.save(city);
    }


    public CityDTO getCity(Long id) {

        City city = getCityById(id);
        return new CityDTO(city);
    }


    public City getCityById(Long id) {
        return CommonUtils.getById(id, cityRepository);
    }

}
