package de.onetwotree.margaux.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SebUndefined on 02/08/17.
 */
public class ResourceTypeTemp {

    private Long id;
    private String description;
    private BigDecimal total;
    private List<Harvest> harvests;


    public ResourceTypeTemp(Long id, String descritpion, BigDecimal total) {
        this.id = id;
        this.description = descritpion;
        this.total = total;
        this.harvests = new ArrayList<>();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescritpion() {
        return description;
    }

    public void setDescritpion(String descritpion) {
        this.description= descritpion;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
    public List<Harvest> getHarvests() {
        return harvests;
    }

    public void setHarvests(List<Harvest> harvests) {
        this.harvests = harvests;
    }
}
