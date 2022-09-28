package com.example.metricseditor;

import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public interface MetricService {

    public abstract List<Metric> getAllMetrics();

    public void addMetric(HttpServletRequest request) throws IOException;

    public abstract Metric getMetricById(Long metricId) throws ResourceNotFoundException;

    public abstract Metric deleteMetric(Long metricId) throws ResourceNotFoundException, IOException;

    public String getPropertiesFile(Long metricId) throws Exception;

    public abstract Metric updateMetric(Long metricId, HttpServletRequest request, String name_before) throws ResourceNotFoundException, IOException;

    public String getQueryFile(Long metricId) throws Exception;
}
