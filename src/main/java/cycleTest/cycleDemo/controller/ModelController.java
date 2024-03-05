package cycleTest.cycleDemo.controller;

import cycleTest.cycleDemo.dto.ModelDTO;
import cycleTest.cycleDemo.dto.PageableDTO;
import cycleTest.cycleDemo.service.ModelService;
import cycleTest.cycleDemo.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/model")
public class ModelController {

    @Autowired
    ModelService modelService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public ResponseEntity<?> getAllModels(@Param("name") String name
            , @Param("userBrandId") Long userBrandId
            , @PageableDefault Pageable pageable) {

        log.debug("start getting model pages::");
        PageableDTO pageableDTO=modelService.getAllModels(name,userBrandId,pageable);
        log.debug("end getting model pages::");
        return new ResponseEntity<>(pageableDTO, HttpStatus.OK);
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/userBrand/{userBrandId}")
    public ResponseEntity<?> getModelLists(@PathVariable("userBrandId") Long userBrandId){

        log.debug("start getting model lists::");
        List<ModelDTO> modelDTOList=modelService.getModelLists(userBrandId);
        log.debug("end getting model lists::");
        return new ResponseEntity<>(modelDTOList,HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<?> addModel(@RequestBody ModelDTO modelDTO,Errors errors){

        log.debug("start adding a model::");
        if (errors.hasErrors()){
            return  new ResponseEntity<>(CommonUtils.getFieldErrors(errors), HttpStatus.BAD_REQUEST);
        }
        modelService.addModel(modelDTO);
        log.debug("end adding a model::");
        return new ResponseEntity<>(CommonUtils.returnSuccessfulMessage("successfully added a model."), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getModelDetails(@PathVariable("id")Long id){

        log.debug("start getting model detail::");
        ModelDTO modelDTO=modelService.getModelDetails(id);
        log.debug("end getting model detail::");
        return  new ResponseEntity<>(modelDTO, HttpStatus.OK);
    }
}
