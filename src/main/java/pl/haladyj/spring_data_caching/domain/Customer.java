package pl.haladyj.spring_data_caching.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name="customer")
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(nullable = false,
    name = "first_name")
    @Size(max = 50)
    private String firstName;

    @NotNull
    @Column(nullable = false,
    name = "last_name")
    @Size(max = 50)
    private String lastName;
}
