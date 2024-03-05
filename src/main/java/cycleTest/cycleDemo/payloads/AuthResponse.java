package cycleTest.cycleDemo.payloads;

import cycleTest.cycleDemo.dto.UserDTO;
import lombok.Data;

import java.io.Serializable;

@Data
public class AuthResponse implements Serializable {

    private static final long serialVersionUID=1L;

    private String accessToken;
    private String tokenType="Bearer";
    private String refreshToken;
    private UserDTO userDTO;

    public AuthResponse(String accessToken,String refreshToken,UserDTO userDTO){
        this.accessToken=accessToken;
        this.refreshToken=refreshToken;
        this.userDTO=userDTO;
    }
}
