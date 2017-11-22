package de.onetwotree.margaux.form;

import de.onetwotree.margaux.entity.PlotResource;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by SebUndefined on 22/11/17.
 */
public class PlotResourceForm {

    @Valid
    private List<PlotResource> plotResourceList;

    public PlotResourceForm() {
    }

    public PlotResourceForm(List<PlotResource> plotResourceList) {
        this.plotResourceList = plotResourceList;
    }

    public List<PlotResource> getPlotResourceList() {
        return plotResourceList;
    }

    public void setPlotResourceList(List<PlotResource> plotResourceList) {
        this.plotResourceList = plotResourceList;
    }
}
