package de.onetwotree.margaux.entity;


import javax.persistence.Entity;

/**
 * Created by sebby on 10/07/17.
 */
@Entity
public abstract class MainEntity {

    protected long id;
    protected User manager;

    protected MainEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

}
