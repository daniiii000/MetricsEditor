package com.example.metricseditor.repositories;

import com.example.metricseditor.models.Metric;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository("metricrepository")
public interface MetricRepository extends CrudRepository<Metric,Long> {
}
