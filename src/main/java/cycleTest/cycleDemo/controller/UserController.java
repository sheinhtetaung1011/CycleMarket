package cycleTest.cycleDemo.controller;

import com.sun.security.auth.UserPrincipal;
import cycleTest.cycleDemo.annotation.CurrentUser;
import cycleTest.cycleDemo.dto.PageableDTO;
import cycleTest.cycleDemo.dto.UserDTO;
import cycleTest.cycleDemo.enums.Status;
import cycleTest.cycleDemo.enums.UserRole;
import cycleTest.cycleDemo.payloads.PasswordChangeRequest;
import cycleTest.cycleDemo.security.CustomUserDetails;
import cycleTest.cycleDemo.service.UserService;
import cycleTest.cycleDemo.utils.CommonUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@RestController
@RequestMapping(value = "/api/user")
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/userPage")
    public ResponseEntity<?> getAllUsers(@Param("name") String name,
                                         @Param("phoneNo") String phoneNo,
                                         @Param("userRole") UserRole userRole,
                                         @Param("status") Status status,
                                         @PageableDefault Pageable pageable) {
        PageableDTO pageableDTO = userService.getAllUsers(name, phoneNo, userRole, status, pageable);

        return new ResponseEntity<>(pageableDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addUser(@Valid @RequestBody UserDTO userDTO, Errors errors) {

        log.debug("start adding a user::");
        if (ObjectUtils.isEmpty(userDTO.getPassword())) {
            errors.rejectValue("password", "error.name", " must not be blank.");
        }
        if (errors.hasErrors()) {
            return new ResponseEntity<>(CommonUtils.getFieldErrors(errors), HttpStatus.BAD_REQUEST);
        }
        userService.addUser(userDTO);
        log.debug("end adding a user::");
        return new ResponseEntity<>(CommonUtils.returnSuccessfulMessage("successfully added a user."), HttpStatus.OK);
    }


    @PatchMapping
    public ResponseEntity<?> updateUser(@CurrentUser CustomUserDetails customUserDetails,@RequestBody UserDTO userDTO, Errors errors) {

        log.debug("start updating a user");
        System.out.println("currentUser::"+customUserDetails.getUsername());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        System.out.println("Username is ;:" + authentication);

        if (errors.hasErrors()) {
            return new ResponseEntity<>(CommonUtils.getFieldErrors(errors), HttpStatus.BAD_REQUEST);
        }


    userService.updateUser(customUserDetails, userDTO);

        log.debug("end updating a user");
        return new ResponseEntity<>(CommonUtils.returnSuccessfulMessage("Successfully update a user"), HttpStatus.OK);
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<?> getUser(@PathVariable("userId") Long userId) {

        UserDTO userDTO = userService.getUser(userId);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }


    @PostMapping(value = "/changePassword")
    public ResponseEntity<?> changePassword(@CurrentUser CustomUserDetails customUserDetails
            , @Valid @RequestBody PasswordChangeRequest request,
                                            Errors errors) {

        log.debug("start changing password::");
        if (errors.hasErrors()) {
            return new ResponseEntity<>(CommonUtils.getFieldErrors(errors), HttpStatus.BAD_REQUEST);
        }

        userService.changePassword(customUserDetails, request);
        log.debug("end changing password::");
        return new ResponseEntity<>(CommonUtils.returnSuccessfulMessage("successfully change password"), HttpStatus.OK);

    }


    @PostMapping(value = "/resetPassword")
    public ResponseEntity<?> resetPassword(@CurrentUser CustomUserDetails customUserDetails
            , @Valid @RequestBody PasswordChangeRequest request, Errors errors) {

        log.debug("start resetting password::");
        if (errors.hasErrors()) {
            return new ResponseEntity<>(CommonUtils.getFieldErrors(errors), HttpStatus.BAD_REQUEST);
        }

        userService.resetPassword(customUserDetails, request);
        log.debug("end resetting password::");
        return new ResponseEntity<>(CommonUtils.returnSuccessfulMessage("successfully reset password"), HttpStatus.OK);

    }


}
