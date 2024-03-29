package com.example.metricseditor.serviceImpl;

import com.example.metricseditor.exceptions.ResourceNotFoundException;
import com.example.metricseditor.models.Condition;
import com.example.metricseditor.repositories.ConditionRepository;
import com.example.metricseditor.services.ConditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Set;

@Service("conditionservice")
public class ConditionServiceImpl implements ConditionService {

    @Autowired
    @Qualifier("conditionrepository")
    private ConditionRepository conditionRepository;

    @Override
    public List<Condition> getAllConditions() {
        return (List<Condition>) conditionRepository.findAll();
    }

    @Override
    public Condition getConditionById(Long conditionId) throws ResourceNotFoundException {
        Condition condition = conditionRepository.findById(conditionId).orElseThrow(() -> new ResourceNotFoundException("Condition not found for this id :: " + conditionId));
        return condition;
    }

    @Override
    public void addConditions(@RequestBody Set<Condition> conditions) {
        conditionRepository.saveAll(conditions);
    }

    @Override
    public List<Condition> getConditions(Long metricId) throws ResourceNotFoundException {
        //List<Condition> conditions = conditionRepository.findAllByMetric(metricId).orElseThrow(() -> new ResourceNotFoundException("Condition not found for this id :: " + metricId));
        List<Condition> conditions = conditionRepository.findAllByMetric(metricId);
        return conditions;
    }

}