package cycleTest.cycleDemo.service;

import cycleTest.cycleDemo.dto.ModelDTO;
import cycleTest.cycleDemo.dto.PageableDTO;
import cycleTest.cycleDemo.entity.Model;
import cycleTest.cycleDemo.entity.UserBrand;
import cycleTest.cycleDemo.entity.UserBrand_;
import cycleTest.cycleDemo.repository.ModelRepository;
import cycleTest.cycleDemo.specs.ModelSpecs;
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
public class ModelService {

    @Autowired
    ModelRepository modelRepository;

    @Autowired
    UserBrandService userBrandService;

    public PageableDTO getAllModels(String name, Long userBrandId, Pageable pageable){

        Specification<Model> modelSpecs= ModelSpecs.getAllModels(name,userBrandId);
        Page<Model> modelPage=modelRepository.findAll(modelSpecs,pageable);
        List<Model> modelList=modelPage.getContent();
        List<ModelDTO> modelDTOList= CommonUtils.getDTOList(modelList,ModelDTO::new);
        PageableDTO pageableDTO=new PageableDTO(modelDTOList,modelPage);
        return pageableDTO;
    }

    public List<ModelDTO> getModelLists(Long userBrandId){

        List<Model> modelList=modelRepository.findByUserBrandId(userBrandId);
        List<ModelDTO> modelDTOList=CommonUtils.getDTOList(modelList,ModelDTO::new);

        return modelDTOList;
    }

    @Transactional(rollbackFor = Exception.class)
    public void addModel(@Valid ModelDTO modelDTO){

        UserBrand userBrand=userBrandService.getUserBrandById(modelDTO.getUserBrandId());
        Model model=new Model();
        model.setName(modelDTO.getName());
        model.setUserBrand(userBrand);
        modelRepository.save(model);
    }


    public Model getModelById(Long id){
        return CommonUtils.getById(id,modelRepository);
    }

    public ModelDTO getModelDetails(Long id){
        return new ModelDTO(getModelById(id));
    }
}
