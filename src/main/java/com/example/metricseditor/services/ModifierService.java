package com.example.metricseditor.services;

import com.example.metricseditor.models.Modifier;
import com.example.metricseditor.exceptions.ResourceNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Set;

public interface ModifierService {

    public abstract List<Modifier> getAllModifiers();

    public abstract Modifier getModifierById(Long modifierId) throws ResourceNotFoundException;

    void addModifiers(@RequestBody Set<Modifier> modifiers);

    List<Modifier> getModifiers(Long metricId) throws ResourceNotFoundException;
}
