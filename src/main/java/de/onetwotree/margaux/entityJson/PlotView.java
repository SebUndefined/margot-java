package de.onetwotree.margaux.entityJson;


/**
 * Created by SebUndefined on 02/08/17.
 */
public class PlotView {
    public static class PlotBasic {}
    public static class PlotWithUser extends PlotBasic implements MainEntityView.WithUser {}
    public static class PlotWithUserAndCompany extends PlotWithUser implements MainEntityView.WithUser {}
}
