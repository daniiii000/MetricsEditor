package com.example.metricseditor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.metricseditor.Metric;

@Repository("metricrepository")
public interface MetricRepository extends JpaRepository<Metric,Long> {

}
