package cycleTest.cycleDemo.controller;

import cycleTest.cycleDemo.dto.CityDTO;
import cycleTest.cycleDemo.dto.PageableDTO;
import cycleTest.cycleDemo.entity.City;
import cycleTest.cycleDemo.payloads.ApiErrorResponse;
import cycleTest.cycleDemo.service.CityService;
import cycleTest.cycleDemo.utils.CommonUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/city")
public class CityController {

    @Autowired
    CityService cityService;

    @GetMapping
    public ResponseEntity<?> getAllCities(@Param("name") String name, @PageableDefault(size = 10) Pageable pageable){

        PageableDTO pageableDTO=cityService.getAllCities(name,pageable);
        return new ResponseEntity<>(pageableDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addCity(@Valid @RequestBody CityDTO cityDTO,Errors errors){

        if(errors.hasErrors())
            return new ResponseEntity<>(CommonUtils.getFieldErrors(errors),HttpStatus.BAD_REQUEST);

        cityService.saveCity(cityDTO);
        return new ResponseEntity<>(CommonUtils.returnSuccessfulMessage("City is added"),HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<?> updateCity(@Valid @RequestBody CityDTO cityDTO,Errors errors){
        CommonUtils.validateIdForUpdate(cityDTO.getId(),errors);
        if (errors.hasErrors())
            return new ResponseEntity<>(CommonUtils.getFieldErrors(errors),HttpStatus.BAD_REQUEST);
        cityService.updateCity(cityDTO);
        return new ResponseEntity<>(CommonUtils.returnSuccessfulMessage("City is updated"),HttpStatus.OK);
    }

    @GetMapping("/{cityId}")
    public ResponseEntity<?> getCity (@PathVariable(value = "cityId") Long id){

        CityDTO cityDTO=cityService.getCity(id);
        return new ResponseEntity<>(cityDTO,HttpStatus.OK);
    }



}
