package com.kodilla.ecommercee.domain;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDto {

    private Long id;
    private Long addressId;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private String email;


}
