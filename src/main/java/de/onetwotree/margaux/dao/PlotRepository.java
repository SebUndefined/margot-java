package de.onetwotree.margaux.dao;

import de.onetwotree.margaux.entity.Plot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by SebUndefined on 21/08/17.
 */
@Repository
public interface PlotRepository extends BaseRepository<Plot> {
    @Query("SELECT p FROM Plot as p " +
            "JOIN p.project project " +
            "JOIN project.company company " +
            "JOIN company.holding h " +
            "where h.id = :id")
    List<Plot> findAllByHoldingId(@Param("id") Long id);

    @Query("SELECT p FROM Plot as p " +
            "JOIN p.project project " +
            "JOIN project.company company " +
            "where company.id = :id")
    List<Plot> findAllByCompanyId(@Param("id") Long id);

    List<Plot> findAllByProjectId(Long id);
}


