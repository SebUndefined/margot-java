package de.onetwotree.margaux.dao;

import de.onetwotree.margaux.entity.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Created by SebUndefined on 19/08/17.
 */
@SuppressWarnings("unused")
@Repository
public interface CompanyRepository extends BaseRepository<Company> {

    Page<Company> findByHoldingId(Long holdingId, Pageable pageable);
}
