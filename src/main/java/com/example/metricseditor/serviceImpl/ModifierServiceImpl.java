package com.example.metricseditor.serviceImpl;

import com.example.metricseditor.exceptions.ResourceNotFoundException;
import com.example.metricseditor.models.Modifier;
import com.example.metricseditor.repositories.ModifierRepository;
import com.example.metricseditor.services.ModifierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service("modifierservice")
public class ModifierServiceImpl implements ModifierService {

    @Autowired
    @Qualifier("modifierrepository")
    private ModifierRepository modifierRepository;

    @Override
    public List<Modifier> getAllModifiers() {
        return (List<Modifier>) modifierRepository.findAll();
    }

    @Override
    public Modifier getModifierById(Long modifierId) throws ResourceNotFoundException {
        Modifier modifier = modifierRepository.findById(modifierId).orElseThrow(() -> new ResourceNotFoundException("Modifier not found for this id :: " + modifierId));
        return modifier;
    }

    @Override
    public void addModifiers(Set<Modifier> modifiers) {
        modifierRepository.saveAll(modifiers);
    }

    @Override
    public List<Modifier> getModifiers(Long metricId) throws ResourceNotFoundException{
        List<Modifier> modifiers = modifierRepository.findAllByMetric(metricId);
        return modifiers;
    }

}
