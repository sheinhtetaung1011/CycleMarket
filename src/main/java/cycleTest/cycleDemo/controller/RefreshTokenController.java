package cycleTest.cycleDemo.controller;

import cycleTest.cycleDemo.annotation.CurrentUser;
import cycleTest.cycleDemo.dto.UserDTO;
import cycleTest.cycleDemo.entity.User;
import cycleTest.cycleDemo.payloads.AuthResponse;
import cycleTest.cycleDemo.repository.RefreshTokenRepository;
import cycleTest.cycleDemo.security.JwtService;
import cycleTest.cycleDemo.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/refresh-Token")
public class RefreshTokenController {

    @Autowired
    RefreshTokenService refreshTokenService;

    @Autowired
    JwtService jwtService;

    @GetMapping("/{token}")
    public ResponseEntity<?> getTokenFromRefreshToken
            (@PathVariable("token") String token){

        User user=refreshTokenService.getUserFromRefreshToken(token);
        UserDTO userDTO=new UserDTO(user);
        String newToken=jwtService.generateToken(userDTO.getName());

        AuthResponse response=new AuthResponse(newToken,token,userDTO);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
