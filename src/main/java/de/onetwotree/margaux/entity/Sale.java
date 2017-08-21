package de.onetwotree.margaux.entity;



import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import de.onetwotree.margaux.entity.MainEntity;

import javax.money.MonetaryAmount;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.GregorianCalendar;

/**
 * Created by SebUndefined on 10/07/17.
 */
@Entity
@Table(name = "db_sale")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@JsonView(MainEntityView.Simple.class)
    protected Long id;
    @Column(name = "sale_date")
    private GregorianCalendar date;
    @Column(name = "sale_amout")
    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "harvest_id")
    private Harvest harvest;
    public Sale() {
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public void setDate(GregorianCalendar date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
