package com.example.metricseditor;

import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Set;

public interface ConditionService {

    public abstract List<Condition> getAllConditions();

    public abstract Condition getConditionById(Long conditionId) throws ResourceNotFoundException;

    void addConditions(@RequestBody Set<Condition> conditions);
}
