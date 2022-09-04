package com.example.metricseditor;

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
        return conditionRepository.findAll();
    }

    @Override
    public Condition getConditionById(Long conditionId) throws ResourceNotFoundException{
        Condition condition = conditionRepository.findById(conditionId).orElseThrow(() -> new ResourceNotFoundException("Condition not found for this id :: " + conditionId));
        return condition;
    }

    @Override
    public void addConditions(@RequestBody Set<Condition> conditions) {
        conditionRepository.saveAll(conditions);
    }
}