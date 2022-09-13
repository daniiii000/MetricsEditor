package com.example.metricseditor;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("modifierrepository")
public interface ModifierRepository extends CrudRepository<Modifier,Long> {

    public List<Modifier> findAllByMetric(Long metricId);
}
