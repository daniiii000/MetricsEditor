package com.example.metricseditor.services;

import com.example.metricseditor.models.Metric;
import com.example.metricseditor.exceptions.ResourceNotFoundException;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public interface MetricService {

    public abstract List<Metric> getAllMetrics();

    public void addMetric(HttpServletRequest request) throws IOException;

    public abstract Metric getMetricById(Long metricId) throws ResourceNotFoundException;

    public abstract Metric deleteMetric(Long metricId) throws ResourceNotFoundException, IOException;

    public String getPropertiesFile(Long metricId) throws Exception;

    public abstract String updateMetric(HttpServletRequest request, String name_before) throws ResourceNotFoundException, IOException;

    public String getQueryFile(Long metricId) throws Exception;
}
