package de.onetwotree.margaux.dao;

import de.onetwotree.margaux.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Query(value = "SELECT p FROM Project as p " +
            "JOIN p.company company " +
            "JOIN company.mainCompany mc " +
            "where mc.id = :idMainCompany",
            countQuery = "SELECT count (p) FROM Project as p " +
                    "JOIN p.company company " +
                    "JOIN company.mainCompany mainCompany " +
                    "WHERE mainCompany.id = :idMainCompany")
    Page<Project> findByMainCompanyId(@Param("idMainCompany") Long id, Pageable pageable);


    Page<Project> findAllByCompanyId(Long id, Pageable pageable);
}
