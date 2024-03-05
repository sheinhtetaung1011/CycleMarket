package cycleTest.cycleDemo.service;

import com.sun.security.auth.UserPrincipal;
import cycleTest.cycleDemo.annotation.CurrentUser;
import cycleTest.cycleDemo.dto.PageableDTO;
import cycleTest.cycleDemo.dto.UserDTO;
import cycleTest.cycleDemo.entity.City;
import cycleTest.cycleDemo.entity.Township;
import cycleTest.cycleDemo.entity.User;
import cycleTest.cycleDemo.enums.Status;
import cycleTest.cycleDemo.enums.UserRole;

import cycleTest.cycleDemo.exception.BadRequestException;
import cycleTest.cycleDemo.payloads.PasswordChangeRequest;
import cycleTest.cycleDemo.repository.UserRepository;
import cycleTest.cycleDemo.security.CustomUserDetails;
import cycleTest.cycleDemo.specs.UserSpecs;
import cycleTest.cycleDemo.utils.CommonUtils;
import jakarta.validation.Valid;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.security.Security;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CityService cityService;

    @Autowired
    TownshipService townshipService;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Transactional(rollbackFor = Exception.class)
    public User signup(UserDTO userDTO) {

        User user = new User();
        user.setName(userDTO.getName());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setUserRole(userDTO.getUserRole());
        user.setStatus(userDTO.getStatus());
        user.setPhoneNo(userDTO.getPhoneNo());

        City city = cityService.getCityById(userDTO.getCityId());
        user.setCity(city);

        Township township = townshipService.getTownshipById(userDTO.getTownshipId());
        user.setTownship(township);

        userRepository.save(user);
        return user;
    }


    public PageableDTO getAllUsers(String name, String phoneNo, UserRole userRole, Status status, Pageable pageable) {

        Specification<User> userSpecs = UserSpecs.getAllUsers(name, phoneNo, userRole, status);

        Page<User> userPage = userRepository.findAll(userSpecs, pageable);
        List<User> userList = userPage.getContent();

        List<UserDTO> userDTOList = CommonUtils.getDTOList(userList, UserDTO::new);
        PageableDTO pageableDTO = new PageableDTO(userDTOList, userPage);

        return pageableDTO;
    }

    public UserDTO getUser(Long id) {
        User user = CommonUtils.getById(id, userRepository);
        return new UserDTO(user);
    }

    public User getUserById(Long id) {
        return CommonUtils.getById(id, userRepository);
    }

    public Optional<User> findByName(String username) {
         return userRepository.findByName(username);
    }


    public void checkUserStatus(User user) {
        if (user.getUserRole().equals(UserRole.USER)) {
            if (user.getStatus().equals(Status.INACTIVE)) {
                throw new AccessDeniedException("Your account has been locked.");
            }
        }
    }


    @Transactional(rollbackFor = Exception.class)
    public void addUser(UserDTO userDTO) {

        Township township = townshipService.getTownshipById(userDTO.getTownshipId());
        City city = cityService.getCityById(userDTO.getCityId());

        checkUserNameAlreadyExist(userRepository, userDTO.getName());

        User user = new User();
        user.setName(userDTO.getName());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setPhoneNo(userDTO.getPhoneNo());
        user.setUserRole(userDTO.getUserRole());
        user.setStatus(userDTO.getStatus());
        user.setTownship(township);
        user.setCity(city);

        userRepository.save(user);
    }

    private void checkUserNameAlreadyExist(UserRepository userRepository, String name) {

        Optional<User> user = userRepository.findByName(name);
        if (user.isPresent()) {
            throw new BadRequestException("Username is already used.");
        }

    }


    @Transactional(rollbackFor = Exception.class)
    public void updateUser(CustomUserDetails customUserDetails, UserDTO userDTO) {
        User currentUser = findByName(customUserDetails.getUsername()).get();

        if (!(currentUser.getId() == userDTO.getId())) {
            throw new AccessDeniedException("Permission Denied.");
        }

        Optional<User> user = findByName(userDTO.getName());
        CommonUtils.checkDuplicate(user, userDTO.getId(), "User name ");

        City city = cityService.getCityById(userDTO.getCityId());
        Township township = townshipService.getTownshipById(userDTO.getTownshipId());

        currentUser.setName(userDTO.getName());
        currentUser.setUserRole(userDTO.getUserRole());
        currentUser.setStatus(userDTO.getStatus());
        currentUser.setPhoneNo(userDTO.getPhoneNo());
        currentUser.setTownship(township);
        currentUser.setCity(city);
        userRepository.save(currentUser);

    }


    @Transactional(rollbackFor = Exception.class)
    public void changePassword(CustomUserDetails customUserDetails, PasswordChangeRequest request) {

        User currentUser =findByName(customUserDetails.getUsername()).get();
        System.out.println("User::" + currentUser.toString());

        if (!passwordEncoder.matches(request.getOldPassword(), currentUser.getPassword())) {
            throw new AccessDeniedException("Old password doesnt match.");
        }
        currentUser.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(currentUser);

    }

    @Transactional(rollbackFor = Exception.class)
    public void resetPassword(CustomUserDetails customUserDetails, PasswordChangeRequest request) {

        User currentUser = findByName(customUserDetails.getUsername()).get();
        currentUser.setPassword(passwordEncoder.encode(request.getNewPassword()));
    }

    public List<Long> findByRole(UserRole userRole) {
        return userRepository.findByUserRole(userRole);
    }

    public void validateUser(long id, Long userId) {
        if (id!=userId){
            throw new BadRequestException("userId doesn't match.");
        }
    }
}
