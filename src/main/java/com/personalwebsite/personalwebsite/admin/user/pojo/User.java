package com.personalwebsite.personalwebsite.admin.user.pojo;

import com.personalwebsite.personalwebsite.admin.user.pojo.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String username;
    private String password;
    private Collection<Role> roles = new ArrayList<>();
}
