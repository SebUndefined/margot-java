package de.onetwotree.margaux.entity;

import org.hibernate.validator.constraints.NotEmpty;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by SebUndefined on 20/11/17.
 */
@Entity
@Table(name = "db_country",
        uniqueConstraints = @UniqueConstraint(name="country_name_uc", columnNames = {"name"}))
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(
            name = "name",
            nullable = false
    )
    @NotNull(message = "Name Cannot be null ! ")
    @NotEmpty(message = "Please enter a name")
    @Size(min = 3, max = 155, message = "Size should be between 1 and 155 characteres")
    private String name;
    @NotNull(message = "Flag cannot be null ! ")
    @NotEmpty(message = "Please upload a file !")
    private String flag;

    public Country() {
    }
    public Country(String name, String flag) {
        this.name = name;
        this.flag = flag;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
