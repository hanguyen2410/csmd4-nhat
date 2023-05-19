package com.cg.service.unit;


import com.cg.model.Unit;
import com.cg.service.IGeneralService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUnitService extends IGeneralService<Unit, Long> {
}
