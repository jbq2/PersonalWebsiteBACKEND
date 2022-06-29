package com.personalwebsite.personalwebsite.admin.user.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserHasRole {
    private Long id;
    private Long user_id;
    private Long role_id;
}

