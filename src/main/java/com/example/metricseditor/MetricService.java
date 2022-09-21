package com.example.metricseditor;

import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface MetricService {

    public abstract List<Metric> getAllMetrics();

    public abstract Metric addMetric(@RequestBody Metric metric);

    public abstract Metric getMetricById(Long metricId) throws ResourceNotFoundException;

    public abstract Metric deleteMetric(Long metricId) throws ResourceNotFoundException;

    public String getPropertiesFile(Long metricId) throws Exception;

    public abstract Metric updateMetric(Long metricId, HttpServletRequest request) throws ResourceNotFoundException;

}
