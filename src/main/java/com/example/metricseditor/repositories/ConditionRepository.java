package com.example.metricseditor.repositories;

import com.example.metricseditor.exceptions.ResourceNotFoundException;
import com.example.metricseditor.models.Condition;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("conditionrepository")
public interface ConditionRepository extends CrudRepository<Condition,Long> {
    public Condition findByMetricId(Long metricId) throws ResourceNotFoundException;

    public List<Condition> findAllByMetric(Long metricId);

    public List<Condition> existsAllByMetric(Long metricId);
}
