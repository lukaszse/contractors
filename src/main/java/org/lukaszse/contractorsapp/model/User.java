package org.lukaszse.contractorsapp.model;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@EqualsAndHashCode(of = "userName")
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "user_name")
    @NotBlank(message = "Login cannot be blank")
    @Pattern(regexp = "^[a-zA-Z]+\\w{1,19}", message = "Login must start with a letter and contain 2 - 20 word characters (digits, letters, _)")
    private String userName;

    @Column(name = "first_name")
    @NotBlank(message = "First name cannot be blank")
    private String firstName;

    @Column(name = "last_name")
    @NotBlank(message = "Last name cannot be blank")
    private String lastName;

    @NotBlank(message = "Password cannot be blank")
    @Pattern(regexp = "^.{6,}", message = "Password must contain at least 6 characters")
    private String password;
}
