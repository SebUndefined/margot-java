package de.onetwotree.margaux.dao;

import de.onetwotree.margaux.entity.PlotResource;
import de.onetwotree.margaux.entity.PlotResourcePK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by SebUndefined on 23/08/17.
 */
@Repository
public interface PlotResourceRepository extends JpaRepository<PlotResource, PlotResourcePK> {

    List<PlotResource> findAllByPlotId(Long plotId);

    @Query("select sum(proportion) " +
            "FROM PlotResource as pr " +
            "where pr.plot.id = :id")
    BigDecimal findSumOfProportionOfPlot(@Param("id") Long idPlot);
}
