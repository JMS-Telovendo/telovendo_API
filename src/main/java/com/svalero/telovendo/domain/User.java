package com.svalero.telovendo.domain;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @NotBlank
    private String name;

    @Column
    @NotBlank
    private String surname;

    @Column
    @NotBlank
    private String user;

    @Column
    @NotBlank
    private String email;

    @Column
    @NotBlank
    private String password;

    @OneToMany(mappedBy = "user")
    @JsonBackReference(value = "user-product")
    private List<Product> products;
}
