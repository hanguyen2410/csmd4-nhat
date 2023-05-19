package com.cg.service.withdraw;

import com.cg.model.Withdraw;
import com.cg.repository.WithdrawRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class WithdrawServiceImpl implements IWithdrawService {

//    @Autowired
    private WithdrawRepository withdrawRepository;




    @Override
    public List<Withdraw> findAll() {
        return null;
    }

    @Override
    public Optional<Withdraw> findById(Number id) {
        return Optional.empty();
    }

    @Override
    public Withdraw save(Withdraw withdraw) {
        return null;
    }



    @Override
    public void delete(Withdraw withdraw) {
    }

    @Override
    public void deleteById(Number id) {

    }
}