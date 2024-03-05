package cycleTest.cycleDemo.payloads;

import lombok.Data;

@Data
public class PasswordChangeRequest {

    private String oldPassword;
    private String newPassword;
}
