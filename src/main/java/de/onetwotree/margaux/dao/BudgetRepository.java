package de.onetwotree.margaux.dao;

import de.onetwotree.margaux.entity.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by SebUndefined on 22/08/17.
 */
@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {
}
