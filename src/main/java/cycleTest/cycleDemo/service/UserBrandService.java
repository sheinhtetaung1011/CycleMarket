package cycleTest.cycleDemo.service;

import cycleTest.cycleDemo.dto.PageableDTO;
import cycleTest.cycleDemo.dto.UserBrandDTO;
import cycleTest.cycleDemo.dto.UserDTO;
import cycleTest.cycleDemo.entity.User;
import cycleTest.cycleDemo.entity.UserBrand;
import cycleTest.cycleDemo.entity.UserBrand_;
import cycleTest.cycleDemo.repository.UserBrandRepository;
import cycleTest.cycleDemo.repository.UserRepository;
import cycleTest.cycleDemo.specs.UserBrandSpecs;
import cycleTest.cycleDemo.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class UserBrandService {

    @Autowired
    UserBrandRepository userBrandRepository;

    @Autowired
    UserService userService;

    public PageableDTO getAllUserBrands(String name, Pageable pageable) {

        Specification<UserBrand> userBrandSpecs = UserBrandSpecs.getAllBrands(name);
        Page<UserBrand> userBrandPage = userBrandRepository.findAll(userBrandSpecs,pageable);
        List<UserBrand> userBrandList = userBrandPage.getContent();
        List<UserBrandDTO> userBrandDTOList = CommonUtils.getDTOList(userBrandList, UserBrandDTO::new);
        return new PageableDTO(userBrandDTOList,userBrandPage);
    }


    public List<UserBrandDTO> getUserBrandList(){

        List<UserBrand> userBrandList=userBrandRepository.findAll();

        List<UserBrandDTO> userBrandDTOList=CommonUtils.getDTOList(userBrandList,UserBrandDTO::new);
        return userBrandDTOList;
    }


    @Transactional(rollbackFor = Exception.class)
    public void addUserBrand(UserBrandDTO userBrandDTO){

        String name=userBrandDTO.getName();

        Optional<UserBrand> userBrandExist=userBrandRepository.findByName(name);
        CommonUtils.checkDuplicate(userBrandExist,null,"UserBrand");

        User user=userService.getUserById(userBrandDTO.getUserId());

        UserBrand userBrand=new UserBrand();
        userBrand.setName(userBrandDTO.getName());
        userBrand.setUser(user);
        userBrandRepository.save(userBrand);
    }


    @Transactional(rollbackFor = Exception.class)
    public void updateUserBrand(UserBrandDTO userBrandDTO){

        UserBrand userBrand=userBrandRepository.findById(userBrandDTO.getId()).get();

        User user=userService.getUserById(userBrandDTO.getUserId());


        userBrand.setName(userBrandDTO.getName());
        userBrand.setUser(user);

        userBrandRepository.save(userBrand);
    }


    public UserBrand getUserBrandById(Long id){
        return CommonUtils.getById(id,userBrandRepository);
    }

    public UserBrandDTO getUserBrand(Long id){

        UserBrand userBrand=getUserBrandById(id);
        UserBrandDTO userBrandDTO= new UserBrandDTO(userBrand);
        return userBrandDTO;
    }

}
