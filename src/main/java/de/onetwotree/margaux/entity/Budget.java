package de.onetwotree.margaux.entity;

import javax.money.MonetaryAmount;
import java.util.GregorianCalendar;

/**
 * Created by SebUndefined on 10/07/17.
 */
public class Budget extends MainEntity {

    private GregorianCalendar begindate;
    private GregorianCalendar endDate;
    private MonetaryAmount $ammount;

    public Budget() {
    }

    public GregorianCalendar getBegindate() {
        return begindate;
    }

    public void setBegindate(GregorianCalendar begindate) {
        this.begindate = begindate;
    }

    public GregorianCalendar getEndDate() {
        return endDate;
    }

    public void setEndDate(GregorianCalendar endDate) {
        this.endDate = endDate;
    }

    public MonetaryAmount get$ammount() {
        return $ammount;
    }

    public void set$ammount(MonetaryAmount $ammount) {
        this.$ammount = $ammount;
    }
}
