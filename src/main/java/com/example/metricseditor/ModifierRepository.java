package com.example.metricseditor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("modifierrepository")
public interface ModifierRepository extends JpaRepository<Modifier,Long> {

}
