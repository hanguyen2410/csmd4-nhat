package com.cg.service.unit;

import com.cg.model.Unit;
import com.cg.repository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UnitServiceImpl implements IUnitService{
    @Autowired
    UnitRepository unitRepository;

    @Override
    public List<Unit> findAll() {
        return unitRepository.findAll();
    }

    @Override
    public Optional<Unit> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Unit save(Unit unit) {
        return null;
    }

    @Override
    public void delete(Unit unit) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
