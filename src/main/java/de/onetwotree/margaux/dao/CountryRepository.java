package de.onetwotree.margaux.dao;

import de.onetwotree.margaux.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by SebUndefined on 20/11/17.
 */
@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {


}
