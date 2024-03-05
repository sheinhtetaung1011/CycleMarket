package cycleTest.cycleDemo.controller;

import cycleTest.cycleDemo.dto.PageableDTO;
import cycleTest.cycleDemo.dto.TownshipDTO;
import cycleTest.cycleDemo.entity.Township;
import cycleTest.cycleDemo.service.TownshipService;
import cycleTest.cycleDemo.utils.CommonUtils;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
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

@RestController
@Slf4j
@RequestMapping(value = "/api/township")
public class TownshipController {

    @Autowired
    TownshipService townshipService;

    @GetMapping()
    public ResponseEntity<?> getTownshipsFromCity(@Param(value = "cityId") Long cityId
            ,@PageableDefault Pageable pageable){

        log.debug("All townships from city");
        PageableDTO  pageableDTO=townshipService.getTownshipsByCity(cityId, pageable);

        log.debug("return all townships pageable");
        return new ResponseEntity<>(pageableDTO, HttpStatus.OK);
    }

    @GetMapping("/{cityId}")
    public ResponseEntity<?> getTownshipListFromCity(@PathVariable(name = "cityId") Long cityId){

        log.debug("start getting townshipDTOList");
        List<TownshipDTO> townshipDTOList=townshipService.getAllTownshipByCity(cityId);

        log.debug("End getting townshipDTOList");
        return new ResponseEntity<>(townshipDTOList,HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> addTownship(@Valid @RequestBody TownshipDTO townshipDTO, Errors errors){

        log.debug("Start adding township");
        if(errors.hasErrors())
            return new ResponseEntity<>(CommonUtils.getFieldErrors(errors),HttpStatus.BAD_REQUEST);

        townshipService.addTownship(townshipDTO);
        log.debug("End adding township");
        return new ResponseEntity<>(CommonUtils.returnSuccessfulMessage("successfully added a township"),HttpStatus.OK);

    }

    @PatchMapping()
    public ResponseEntity<?> updateTownship(@Valid @RequestBody TownshipDTO townshipDTO,Errors errors){

        log.debug("start updating a township");
        if(errors.hasErrors())
            return new ResponseEntity<>(CommonUtils.getFieldErrors(errors),HttpStatus.BAD_REQUEST);

        townshipService.updateTownship(townshipDTO);
        log.debug("End updating a township");
        return new ResponseEntity<>(CommonUtils.returnSuccessfulMessage("successfully update a township"),HttpStatus.OK);
    }

    @GetMapping(value = "/{townshipId}")
    public  ResponseEntity<?> getByTownshipId(@PathVariable("townshipId") Long id){

        log.debug("start getting township by Id::");
        Township township=townshipService.getTownshipById(id);
        log.debug("end getting township by id::");
        return new ResponseEntity<>(township,HttpStatus.OK);
    }
}
