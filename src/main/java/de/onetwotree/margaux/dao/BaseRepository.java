package de.onetwotree.margaux.dao;

import de.onetwotree.margaux.entity.MainEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Created by SebUndefined on 22/08/17.
 */
@NoRepositoryBean
public interface BaseRepository<T extends MainEntity> extends JpaRepository<T, Long>{
}
