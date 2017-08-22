package de.onetwotree.margaux.dao;

import de.onetwotree.margaux.entity.MainCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by SebUndefined on 19/08/17.
 */
@SuppressWarnings("unused")
@Repository
public interface MainCompanyRepository extends BaseRepository<MainCompany> {
}
