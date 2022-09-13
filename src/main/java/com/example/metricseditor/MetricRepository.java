package com.example.metricseditor;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository("metricrepository")
public interface MetricRepository extends CrudRepository<Metric,Long> {
}
