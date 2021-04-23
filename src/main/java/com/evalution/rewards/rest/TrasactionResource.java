package com.evalution.rewards.rest;

import com.evalution.rewards.entity.Transaction;
import com.evalution.rewards.model.Rewards;
import com.evalution.rewards.service.RewardsService;
import com.evalution.rewards.service.TrasactionalService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/trasaction")
public class TrasactionResource {

    @Autowired
    private TrasactionalService trasactionalService;

    @ApiOperation(value = "/save trasnaction", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping
    public ResponseEntity<String> save(@RequestBody Transaction transaction) {
        trasactionalService.save(transaction);
        return new ResponseEntity<String>("Created Successfully", HttpStatus.OK);

    }

    @ApiOperation(value = "/findAll trasnaction", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("/findAll")
    public ResponseEntity findAll(){
        List<Transaction> transactions = trasactionalService.findAll();
        return new ResponseEntity<List<Transaction>>(transactions, HttpStatus.OK);

    }
}
