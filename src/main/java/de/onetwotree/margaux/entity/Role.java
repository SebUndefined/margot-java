package de.onetwotree.margaux.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "db_role")
public class Role implements GrantedAuthority {

    @Id
    @Column(name = "role_id")
    private String id;


    @ManyToMany(mappedBy = "grantedAuthorities")
    private List<UserCustom> userCustoms;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public List<UserCustom> getUserCustoms() {
        return userCustoms;
    }

    public void setUserCustoms(List<UserCustom> userCustoms) {
        this.userCustoms = userCustoms;
    }

    @Override
    public String getAuthority() {
        return this.getId();
    }
}
