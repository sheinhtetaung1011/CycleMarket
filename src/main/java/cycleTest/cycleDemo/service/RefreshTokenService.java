package cycleTest.cycleDemo.service;

import cycleTest.cycleDemo.dto.UserDTO;
import cycleTest.cycleDemo.entity.RefreshToken;
import cycleTest.cycleDemo.entity.User;
import cycleTest.cycleDemo.exception.BadRequestException;
import cycleTest.cycleDemo.exception.InternalServerException;
import cycleTest.cycleDemo.repository.RefreshTokenRepository;
import cycleTest.cycleDemo.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.sql.Ref;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {

    @Autowired
    RefreshTokenRepository refreshTokenRepository;


    final int EXPIRE_TIME=86400000;

    public String getRefreshTokenFromUser(User user) {

        RefreshToken refreshToken = refreshTokenRepository.findByUserId(user.getId())
                .orElseThrow(() -> new BadRequestException("Invalid UserId."));
        return refreshToken.getToken();
    }

    public void updateRefreshToken(User user) {

        RefreshToken refreshToken=new RefreshToken();
        Optional<RefreshToken> refreshTokenExist = refreshTokenRepository.findByUserId(user.getId());
        if (refreshTokenExist.isPresent()){
            refreshToken=refreshTokenExist.get();
        }else {
            refreshToken.setToken(UUID.randomUUID().toString());
            refreshToken.setUser(user);
            refreshToken.setExpireDate(new Date(new Date().getTime() + EXPIRE_TIME));
        }

        refreshTokenRepository.save(refreshToken);
    }

    public User getUserFromRefreshToken(String token) {

        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new BadRequestException("Invalid token."));

        if (refreshToken.getExpireDate().before(new Date())) {
            throw new BadRequestException("Please login again.");
        }
        refreshToken.setExpireDate(new Date(new Date().getTime() + EXPIRE_TIME));
        refreshTokenRepository.save(refreshToken);

        return refreshToken.getUser();
    }


    public void createRefreshToken(User user){

        RefreshToken refreshToken=new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setUser(user);
        refreshToken.setExpireDate(new Date(new Date().getTime()+EXPIRE_TIME));
        refreshTokenRepository.save(refreshToken);
    }

}
