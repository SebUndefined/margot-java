package de.onetwotree.margaux.entity;



import javax.money.MonetaryAmount;
import java.util.GregorianCalendar;

/**
 * Created by SebUndefined on 10/07/17.
 */
public class Sale extends MainEntity {
    private GregorianCalendar date;
    private MonetaryAmount amount;

    public Sale() {
    }

    public GregorianCalendar getDate() {
        return date;
    }

    public void setDate(GregorianCalendar date) {
        this.date = date;
    }

    public MonetaryAmount getAmount() {
        return amount;
    }

    public void setAmount(MonetaryAmount amount) {
        this.amount = amount;
    }
}
