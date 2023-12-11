package com.prodevans.BlogSite.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.sun.istack.NotNull;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import jakarta.annotation.security.DenyAll;
import jakarta.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotNull
    private String userName;
    @NotNull
    private String email;
}
