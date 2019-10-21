package com.liandi.system.service.dto;

import java.util.Set;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author czg
 * @date 2019/8/2 11:11
 * @description 用户DTO
 */
@Data
@Accessors(chain = true)
public class UserDTO {

    private Long id;

    private String userCode;

    private String userName;

    private String loginName;

    private String pswd;

    private Long organizationId;

    private String phone;

    private String email;

    private String organizationName;

    private Set<String> roleSet;

    private Set<String> powerSet;

}
