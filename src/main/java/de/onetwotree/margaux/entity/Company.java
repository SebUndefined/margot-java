package de.onetwotree.margaux.entity;



import java.util.ArrayList;
import java.util.List;

/**
 * Created by sebby on 10/07/17.
 */
public class Company extends MainEntity {
    private String name;

    private MainCompany mainCompany;
    private List<Project> projects = new ArrayList<Project>();

    public Company() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MainCompany getMainCompany() {
        return mainCompany;
    }

    public void setMainCompany(MainCompany mainCompany) {
        this.mainCompany = mainCompany;
    }

}
