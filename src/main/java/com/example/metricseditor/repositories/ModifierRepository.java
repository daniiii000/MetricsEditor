package com.example.metricseditor.repositories;

import com.example.metricseditor.models.Modifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("modifierrepository")
public interface ModifierRepository extends CrudRepository<Modifier,Long> {
    public Modifier findByMetricId(Long metricId);

    public List<Modifier> findAllByMetric(Long metricId);
}
