package de.onetwotree.margaux.dao;

import de.onetwotree.margaux.entity.Harvest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by SebUndefined on 22/08/17.
 */
@Repository
public interface HarvestRepository extends JpaRepository<Harvest, Long> {


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

    /*
    ###################DAO For All Harvest depending of the entity (pageable)#####################
     */

    /**
     * Find all harvest for a main Company
     * @param mainCompanyId
     * @param pageRequest
     * @return
     */
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

    /**
     * Find all harvests for a Company
     * @param companyId
     * @param pageRequest
     * @return
     */
    @Query(value = "SELECT h FROM Harvest as h " +
            "JOIN h.resource resource " +
            "JOIN h.plot plot " +
            "JOIN plot.project project " +
            "JOIN project.company company " +
            "WHERE company.id = :idCompany " +
            "ORDER BY h.date ASC ",
            countQuery = "SELECT count(h) from Harvest as h " +
                    "JOIN h.plot plot " +
                    "JOIN plot.project project " +
                    "JOIN project.company company " +
                    "WHERE company.id = :idCompany ")
    Page<Harvest> findAllByCompanyId(@Param("idCompany") Long companyId, Pageable pageRequest);

    /**
     * Find All Harvest for a Project
     * @param projectId
     * @param pageRequest
     * @return
     */
    @Query(value = "SELECT h FROM Harvest as h " +
            "JOIN h.resource resource " +
            "JOIN h.plot plot " +
            "JOIN plot.project project " +
            "WHERE project.id = :idProject " +
            "ORDER BY h.date ASC ",
            countQuery = "SELECT count(h) from Harvest as h " +
                    "JOIN h.plot plot " +
                    "JOIN plot.project project " +
                    "WHERE project.id = :idProject ")
    Page<Harvest> findAllByProjectId(@Param("idProject") Long projectId, Pageable pageRequest);

    /**
     *
     * @param plotId
     * @param pageRequest
     * @return
     */
    Page<Harvest> findAllByPlotId(@Param("dPlot") Long plotId, Pageable pageRequest);

    Page<Harvest> findAllByResourceId(@Param("idResource") Long resourceId, Pageable pageable);

    List<Harvest> findAllByResourceId(@Param("idResource") Long resourceId);
    /*
    ############################DAO FOR ENTITY AND RESOURCE TYPE#########################
     */


    /**
     * Find all by main company id and resource Type id
     * @param idMainCompany
     * @param idResourceType
     * @return
     */
    @Query("SELECT h FROM Harvest as h " +
            "JOIN h.resource resource " +
            "JOIN h.plot plot " +
            "JOIN plot.project project " +
            "JOIN project.company company " +
            "JOIN company.mainCompany mainCompany " +
            "WHERE mainCompany.id = :idMainCompany " +
            "AND resource.resourceType.id = :idResourceType " +
            "ORDER BY h.date ASC ")
    List<Harvest> findAllByMainCompanyIdAndResourceTypeId(
            @Param("idMainCompany") Long idMainCompany,
            @Param("idResourceType") Long idResourceType);

    /**
     * Find all by company id and resource Type id
     * @param idCompany
     * @param idResourceType
     * @return
     */
    @Query("SELECT h FROM Harvest as h " +
            "JOIN h.resource resource " +
            "JOIN h.plot plot " +
            "JOIN plot.project project " +
            "JOIN project.company company " +
            "WHERE company.id = :idCompany " +
            "AND resource.resourceType.id = :idResourceType " +
            "ORDER BY h.date ASC ")
    List<Harvest> findAllByCompanyIdAndResourceTypeId(
            @Param("idCompany") Long idCompany,
            @Param("idResourceType") Long idResourceType);
    /**
     * Find all by Project id and resource Type id
     * @param idProject
     * @param idResourceType
     * @return
     */
    @Query("SELECT h FROM Harvest as h " +
            "JOIN h.resource resource " +
            "JOIN h.plot plot " +
            "JOIN plot.project project " +
            "WHERE project.id = :idProject " +
            "AND resource.resourceType.id = :idResourceType " +
            "ORDER BY h.date ASC ")
    List<Harvest> findAllByProjectIdAndResourceTypeId(
            @Param("idProject") Long idProject,
            @Param("idResourceType") Long idResourceType);


    /**
     * Find all by Plot id and resource Type id
     * @param idPlot
     * @param idResourceType
     * @return
     */
    @Query("SELECT h FROM Harvest as h " +
            "JOIN h.resource resource " +
            "JOIN h.plot plot " +
            "WHERE plot.id = :idPlot " +
            "AND resource.resourceType.id = :idResourceType " +
            "ORDER BY h.date ASC ")
    List<Harvest> findAllByPlotIdAndResourceTypeId(@Param("idPlot") Long idPlot,
                                                   @Param("idResourceType") Long idResourceType);
    /**
     * @param idResourceType
     * @return
     */
    @Query("SELECT h FROM Harvest as h " +
            "join h.resource resource " +
            "where resource.resourceType.id = :idResourceType " +
            "order by h.date asc")
    List<Harvest> findAllWhereIdResourceTypeOrderByDate(@Param("idResourceType") Long idResourceType);


}
