package com.example.metricseditor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("conditionrepository")
public interface ConditionRepository extends JpaRepository<Condition,Long> {

}
