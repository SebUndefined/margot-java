package de.onetwotree.margaux.dao;

import de.onetwotree.margaux.entity.ResourceType;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by SebUndefined on 23/08/17.
 */
public interface ResourceTypeRepository extends JpaRepository<ResourceType, Long> {
}
