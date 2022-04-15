package com.fc.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginInfo {
    private String username;
    private String password;
    private boolean isAutoLogin;
    private boolean isRemPassword;
    private boolean IsRemUsername;

}
