package org.lukaszse.contractorsapp.user.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
public class PasswordChangeRequestDto {

    @NotBlank(message = "Login cannot be blank")
    @Pattern(regexp = "^[a-zA-Z]+\\w{1,19}", message = "Login must start with a letter and contain 2 - 20 word characters (digits, letters, _)")
    private String userName;
    @NotBlank
    private String oldPassword;
    @NotBlank(message = "Password cannot be blank")
    @Pattern(regexp = "^\\w{5,20}", message = "Password must contain 6 - 20 word characters (digits, letters, _)")
    private String newPassword;
    @NotBlank(message = "Password cannot be blank")
    @Pattern(regexp = "^\\w{5,20}", message = "Password must contain 6 - 20 word characters (digits, letters, _)")
    private String newPasswordConfirm;
}
