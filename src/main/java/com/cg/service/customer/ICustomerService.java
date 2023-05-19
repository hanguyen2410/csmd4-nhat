package com.cg.service.customer;

import com.cg.model.*;
import com.cg.model.dto.CustomerCreateReqDTO;
import com.cg.model.dto.CustomerCreateResDTO;
import com.cg.model.dto.CustomerDTO;
import com.cg.service.IGeneralService;

import java.util.List;
import java.util.Optional;

public interface ICustomerService extends IGeneralService<Customer, Long> {

    Optional<Customer> findByUser(User user);

    CustomerCreateResDTO create(CustomerCreateReqDTO customerCreateReqDTO);

    Deposit deposit(Deposit deposit);

    Withdraw withdraw(Withdraw withdraw);

    Transfer transfer(Transfer transfer);

    Boolean existsByEmailEquals(String email);

    List<CustomerDTO> findAllCustomerDTOByDeletedIsFalse();

    List<CustomerDTO> findALlCustomerDTO();


}
