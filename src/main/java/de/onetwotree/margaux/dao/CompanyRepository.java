package de.onetwotree.margaux.dao;

import de.onetwotree.margaux.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by SebUndefined on 19/08/17.
 */
@SuppressWarnings("unused")
@Repository
public interface CompanyRepository extends BaseRepository<Company> {

    List<Company> findCompaniesByMainCompanyId(Long idMainCompany);
}
