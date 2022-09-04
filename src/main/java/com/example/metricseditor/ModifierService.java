package com.example.metricseditor;

import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Set;

public interface ModifierService {

    public abstract List<Modifier> getAllModifiers();

    public abstract Modifier getModifierById(Long modifierId) throws ResourceNotFoundException;

    void addModifiers(@RequestBody Set<Modifier> modifiers);
}
