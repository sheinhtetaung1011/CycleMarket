package cycleTest.cycleDemo.controller;

import cycleTest.cycleDemo.dto.UserDTO;
import cycleTest.cycleDemo.entity.User;
import cycleTest.cycleDemo.exception.BadRequestException;
import cycleTest.cycleDemo.exception.ResourceNotFoundException;
import cycleTest.cycleDemo.payloads.AuthResponse;
import cycleTest.cycleDemo.payloads.LoginRequest;
import cycleTest.cycleDemo.repository.UserRepository;
import cycleTest.cycleDemo.security.JwtService;
import cycleTest.cycleDemo.service.RefreshTokenService;
import cycleTest.cycleDemo.service.UserService;
import cycleTest.cycleDemo.utils.CommonUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;

    @Autowired
    RefreshTokenService refreshTokenService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request, Errors errors) {
        String username = request.getUsername();
        String password = request.getPassword();
        if (errors.hasErrors()) {
            return new ResponseEntity<>(CommonUtils.getFieldErrors(errors), HttpStatus.BAD_REQUEST);
        }
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        SecurityContext securityContext=SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);

        String token = jwtService.generateToken(username);
        User user=userRepository.findByName(username).get();

        UserDTO userDTO = new UserDTO(user);
        refreshTokenService.updateRefreshToken(user);
        String refreshToken=refreshTokenService.getRefreshTokenFromUser(user);
        AuthResponse response = new AuthResponse(token, refreshToken, userDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@Valid @RequestBody UserDTO userDTO, Errors errors) {

        if (ObjectUtils.isEmpty(userDTO.getPassword())){
            errors.rejectValue("password","error.name","Password must not be blank");
        }

        if (errors.hasErrors()){
            return new ResponseEntity<>(CommonUtils.getFieldErrors(errors),HttpStatus.BAD_REQUEST);
        }

        Optional<User> userExist=userRepository.findByName(userDTO.getName());
        if (userExist.isPresent()){
            return new ResponseEntity<>(CommonUtils.getFieldErrors(errors),HttpStatus.BAD_REQUEST);
        }

        User user=userService.signup(userDTO);

        Authentication authentication=authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userDTO.getName(), userDTO.getPassword()));
        SecurityContext securityContext=SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);

        refreshTokenService.createRefreshToken(user);
        String refreshToken=refreshTokenService.getRefreshTokenFromUser(user);

        String token=jwtService.generateToken(userDTO.getName());
        UserDTO userDTO1=new UserDTO(userRepository.findByName(userDTO.getName()).get());
        AuthResponse authResponse=new AuthResponse(token,refreshToken,userDTO1);

        return new ResponseEntity<>(authResponse,HttpStatus.OK);

    }


}
