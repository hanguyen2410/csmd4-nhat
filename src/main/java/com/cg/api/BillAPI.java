package com.cg.api;


import com.cg.exception.UnauthorizedException;
import com.cg.model.Customer;
import com.cg.model.User;
import com.cg.service.bill.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bills")
public class BillAPI {

    @Autowired
    private IBillService billService;

    @PostMapping("/pay")
    public ResponseEntity<?> pay() {


        return new ResponseEntity<>(HttpStatus.OK);
    }
}
