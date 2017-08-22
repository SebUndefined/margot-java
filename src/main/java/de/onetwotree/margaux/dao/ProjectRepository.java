package de.onetwotree.margaux.dao;

import de.onetwotree.margaux.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by SebUndefined on 21/08/17.
 */
@Repository
public interface ProjectRepository extends BaseRepository<Project> {
    @Query("SELECT p FROM Project as p " +
            "JOIN p.company company " +
            "JOIN company.mainCompany mc " +
            "where mc.id = :id")
    List<Project> findAllByMainCompanyId(@Param("id") Long id);
}
