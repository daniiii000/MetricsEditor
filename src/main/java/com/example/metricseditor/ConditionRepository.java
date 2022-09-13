package com.example.metricseditor;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("conditionrepository")
public interface ConditionRepository extends CrudRepository<Condition,Long> {
    public List<Condition> findAllByMetric(Long metricId);
}
