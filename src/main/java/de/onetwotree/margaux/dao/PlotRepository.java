package de.onetwotree.margaux.dao;

import de.onetwotree.margaux.entity.Plot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by SebUndefined on 21/08/17.
 */
public interface PlotRepository extends JpaRepository<Plot, Long> {
    @Query("SELECT p FROM Plot as p " +
            "JOIN p.project project " +
            "JOIN project.company company " +
            "JOIN company.mainCompany mc " +
            "where mc.id = :id")
    List<Plot> findAllByMainCompanyId(@Param("id") Long id);
}
