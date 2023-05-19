package com.cg.service.customer;

import com.cg.model.*;
import com.cg.model.dto.CustomerCreateReqDTO;
import com.cg.model.dto.CustomerCreateResDTO;
import com.cg.model.dto.CustomerDTO;
import com.cg.model.enums.ERole;
import com.cg.repository.*;
import com.cg.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class CustomerServiceImpl implements ICustomerService{

    @Autowired
    private CustomerRepository customerRepository;


    @Autowired
    private LocationRegionRepository locationRegionRepository;

    @Autowired
    private IUserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private WithdrawRepository withdrawRepository;

    @Autowired
    private TransferRepository transferRepository;

    @Autowired
    private DepositRepository depositRepository;

    @Override
    public List<Customer> findAll() {
        return null;
    }

    @Override
    public List<CustomerDTO> findAllCustomerDTOByDeletedIsFalse() {
        return customerRepository.findAllCustomerDTOByDeletedIsFalse();
    }

    @Override
    public List<CustomerDTO> findALlCustomerDTO() {
        return customerRepository.findAllCustomerDTO();
    }

//    @Override
//    public Optional<CustomerDTO> findCustomerDTOByIdAndDeletedIsFalse(Long id) {
//        return customerRepository.findCustomerDTOByIdAndDeletedIsFalse(id);
//    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Optional<Customer> findByUser(User user) {
        return customerRepository.findByUser(user);
    }

    @Override
    public CustomerCreateResDTO create(CustomerCreateReqDTO customerCreateReqDTO) {

        Role role = roleRepository.findByName(ERole.ROLE_CUSTOMER);

        User user = new User();
        user.setUsername(customerCreateReqDTO.getUsername());
        user.setPassword(customerCreateReqDTO.getPassword());
        user.setRole(role);
        userService.save(user);

        Customer customer = new Customer();
        customer.setPhone(customerCreateReqDTO.getPhone());
        customer.setFullName(customerCreateReqDTO.getFullName());
        customer.setUser(user);
        customerRepository.save(customer);

        CustomerCreateResDTO customerCreateResDTO = new CustomerCreateResDTO();
        customerCreateResDTO.setId(customer.getId());
        customerCreateResDTO.setUsername(user.getUsername());
        customerCreateResDTO.setFullName(customer.getFullName());
        customerCreateResDTO.setPhone(customer.getPhone());

        return customerCreateResDTO;
    }

    @Override
    public Customer save(Customer customer) {
        LocationRegion locationRegion = customer.getLocationRegion();
        locationRegionRepository.save(locationRegion);
        customer.setLocationRegion(locationRegion);

        return customerRepository.save(customer);
    }

    @Override
    public void delete(Customer customer) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public Deposit deposit(Deposit deposit) {
        Customer customer = deposit.getCustomer();
        BigDecimal transactionAmount = deposit.getTransactionAmount();
        customerRepository.incrementBalance(customer.getId(), transactionAmount);

        deposit.setId(null);
        depositRepository.save(deposit);

        customer = customerRepository.findById(customer.getId()).get();
        deposit.setCustomer(customer);

        return deposit;
    }

    @Override
    public Transfer transfer(Transfer transfer) {
        BigDecimal transferAmount = transfer.getTransferAmount();
        BigDecimal transactionAmount = transfer.getTransactionAmount();

        customerRepository.decrementBalance(transfer.getSender().getId(), transactionAmount);

        customerRepository.incrementBalance(transfer.getRecipient().getId(), transferAmount);

        transferRepository.save(transfer);

        Customer sender = customerRepository.findById(transfer.getSender().getId()).get();
        transfer.setSender(sender);

        return transfer;
    }

    @Override
    public Boolean existsByEmailEquals(String email) {
        return customerRepository.existsByEmailEquals(email);
    }

    @Override
    public Withdraw withdraw(Withdraw withdraw) {
        Customer customer = withdraw.getCustomer();
        BigDecimal transactionAmount = withdraw.getTransactionAmount();
        customerRepository.decrementBalance(customer.getId(), transactionAmount);
        withdraw.setId(null);
        withdrawRepository.save(withdraw);
        customer = customerRepository.findById(customer.getId()).get();
        withdraw.setCustomer(customer);
        return withdraw;
    }
}
