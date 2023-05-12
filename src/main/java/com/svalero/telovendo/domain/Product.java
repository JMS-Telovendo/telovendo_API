package com.svalero.telovendo.domain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @NotBlank
    private String product_name;

    @Column
    @NotBlank
    private String category;
    @Column
    @NotNull(message = ":This field is required")
    @Positive(message = ":This field can only contain positive numbers")
    private float price;

    @Column
    @NotNull
    private LocalDate sellingDate;
    @Column
    private boolean available;

    @Column
    private float latitude;
    @Column
    private float longitude;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id")
    private User user;
}
