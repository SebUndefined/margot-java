package de.onetwotree.margaux.dao;

import de.onetwotree.margaux.entity.Harvest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by SebUndefined on 22/08/17.
 */
@Repository
public interface HarvestRepository extends BaseRepository<Harvest> {


    @Query("SELECT  resource.name, sum(h.quantity) " +
            "FROM Harvest as h " +
            "JOIN h.resource resource " +
            "JOIN resource.resourceType resourceType " +
            "WHERE resourceType.id = :resourceTypeId " +
            "group by h.resource")
    List sumByResourceWhereIdResourceType(@Param("resourceTypeId") Long resourceTypeId);

    @Query("SELECT h  " +
            "FROM Harvest as h " +
            "JOIN h.resource resource " +
            "JOIN resource.resourceType resourceType " +
            "WHERE resourceType.id = :resourceTypeId " +
            "group by h.resource")
    List<Harvest[]> findAllByResourceWhereIdResourceType(@Param("resourceTypeId") Long resourceTypeId);


    @Query(value = "SELECT h FROM Harvest as h " +
            "JOIN h.resource resource " +
            "JOIN h.plot plot " +
            "JOIN plot.project project " +
            "JOIN project.company company " +
            "JOIN company.mainCompany mainCompany " +
            "WHERE mainCompany.id = :idMainCompany " +
            "ORDER BY h.date ASC ",
    countQuery = "SELECT count(h) from Harvest as h " +
            "JOIN h.plot plot " +
            "JOIN plot.project project " +
            "JOIN project.company company " +
            "JOIN company.mainCompany mainCompany " +
            "WHERE mainCompany.id = :idMainCompany ")
    Page<Harvest> findAllByMainCompanyId(@Param("idMainCompany") Long mainCompanyId, Pageable pageRequest);

    @Query("SELECT h FROM Harvest as h " +
            "JOIN h.resource resource " +
            "JOIN h.plot plot " +
            "JOIN plot.project project " +
            "JOIN project.company company " +
            "JOIN company.mainCompany mainCompany " +
            "WHERE mainCompany.id = :idMainCompany " +
            "AND resource.resourceType.id = :idResourceType " +
            "ORDER BY h.date ASC ")
    List<Harvest> findAllByMainCompanyIdAnAndResourceTypeId(
            @Param("idMainCompany") Long idMainCompany,
            @Param("idResourceType") Long idResourceType);


    @Query("SELECT h FROM Harvest as h " +
            "join h.resource resource " +
            "where resource.resourceType.id = :idResourceType " +
            "order by h.date asc")
    List<Harvest> findAllWhereIdResourceTypeOrderByDate(@Param("idResourceType") Long idResourceType);

    @Query("SELECT h from Harvest as h " +
            "JOIN h.resource resource " +
            "WHERE h.plot.id = :idPlot " +
            "AND resource.resourceType.id = :idResourceType " +
            "ORDER BY h.date asc ")
    List<Harvest> findAllByWherePlotIdAndResourceTypeIdOrderByDate(@Param("idPlot") Long idPlot, @Param("idResourceType") Long idResourceType);

}
