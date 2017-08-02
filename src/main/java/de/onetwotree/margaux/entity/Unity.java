package de.onetwotree.margaux.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by SebUndefined on 02/08/17.
 */
@Entity
@Table(name = "db_resource_unity")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Unity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "unity_id")
    private Long id;
    @NotNull
    @Column(name = "unity_desc")
    private String desc;

    public Unity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
