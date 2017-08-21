package de.onetwotree.margaux.dao;

import de.onetwotree.margaux.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by SebUndefined on 21/08/17.
 */
public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query("SELECT p FROM Project as p " +
            "JOIN p.company company " +
            "JOIN company.mainCompany mc " +
            "where mc.id = :id")
    List<Project> findAllByMainCompanyId(@Param("id") Long id);
}
