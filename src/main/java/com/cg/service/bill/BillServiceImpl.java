package com.cg.service.bill;

import com.cg.model.Bill;
import com.cg.model.Customer;
import com.cg.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BillServiceImpl implements IBillService{

    @Autowired
    private BillRepository billRepository;

    @Override
    public List<Bill> findAll() {
        return null;
    }

    @Override
    public Optional<Bill> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void pay(Customer customer) {

    }

    @Override
    public Bill save(Bill bill) {
        return null;
    }

    @Override
    public void delete(Bill bill) {

    }

    @Override
    public void deleteById(Long id) {

    }
}
