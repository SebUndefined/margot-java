package de.onetwotree.margaux.entity;


import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.SerializationFeature;
import de.onetwotree.margaux.entity.User;
import de.onetwotree.margaux.entityJson.MainEntityView;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;

/**
 * Created by sebby on 10/07/17.
 */
@Entity
@Table(name = "db_main_entity")
@Inheritance(
        strategy = InheritanceType.JOINED
)
public abstract class MainEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "main_entity_id")
    @JsonView(MainEntityView.Simple.class)
    protected Long id;


    protected MainEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }




}
