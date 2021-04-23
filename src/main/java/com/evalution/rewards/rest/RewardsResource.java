package com.evalution.rewards.rest;

import com.evalution.rewards.entity.Transaction;
import com.evalution.rewards.model.Rewards;
import com.evalution.rewards.service.RewardsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rewards")
public class RewardsResource {

    @Autowired
    private RewardsService rewardsService;

    @ApiOperation(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping("/getRewards")
    public ResponseEntity getRewards(@RequestParam("isMock") boolean isMock) {
        List<Rewards> rewards = rewardsService.getRewards(isMock);
        return new ResponseEntity(rewards, HttpStatus.OK);

    }

    @ApiOperation(value = "/calculateRewards", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PostMapping("/calculateRewards")
    public ResponseEntity calculateRewards(@RequestBody List<Transaction> transactions) {
        List<Rewards> rewards = rewardsService.calculateRewards(transactions);
        return new ResponseEntity(rewards, HttpStatus.OK);
    }
}
