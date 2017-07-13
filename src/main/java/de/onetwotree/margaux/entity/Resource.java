package de.onetwotree.margaux.entity;


/**
 * Created by SebUndefined on 10/07/17.
 */
public class Resource extends MainEntity {
    private String name;
    private String unity;

    public Resource() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnity() {
        return unity;
    }

    public void setUnity(String unity) {
        this.unity = unity;
    }
}
