package com.evalution.rewards.service;

import com.evalution.rewards.entity.Transaction;
import com.evalution.rewards.model.Rewards;
import com.evalution.rewards.repository.MockRepo;
import com.evalution.rewards.repository.TrasactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.DateFormatSymbols;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RewardsService {

    @Autowired
    public TrasactionRepo trasactionRepo;

    @Autowired
    public MockRepo mockRepo;

    @Value("${data.mock : false}")
    private boolean mockData;

    public List<Rewards> getRewards() {
        List<Transaction> transactions;
        if(mockData) {
            transactions =  mockRepo.findAll();
        } else {
            transactions =  trasactionRepo.findAll();
        }
        return calculateRewards(transactions);
    }

    public List<Rewards> getRewards(boolean isMock) {
        List<Transaction> transactions;
        if(isMock) {
            transactions =  mockRepo.findAll();
        } else {
            transactions =  trasactionRepo.findAll();
        }
        return calculateRewards(transactions);
    }

    /**
     *
     * @param transactions
     * @return
     */
    public List<Rewards> calculateRewards(List<Transaction> transactions) {
         Map<String, List<Transaction>> customerTrasactions = transactions.stream().collect(Collectors.groupingBy(t -> t.getCustomerID()));
        List<Rewards> customerRewards = new ArrayList<Rewards>();
        for (String customer : customerTrasactions.keySet()) {
            Map<String, List<Transaction>> monthlyTransactions = customerTrasactions.get(customer).stream().collect(
                    Collectors.groupingBy(t -> new DateFormatSymbols().getMonths()[t.getTransactionDate().getMonth()]));
            Rewards rewards = new Rewards();
            Map<String, Long> rewardsMap = new TreeMap<>();
            rewards.setCustomerName(customer);
            Long totalRewards = 0L;
            for (String month : monthlyTransactions.keySet()) {
                Long montlyRewards = calculateMontlyRewards(monthlyTransactions.get(month));
                totalRewards += montlyRewards;
                rewardsMap.put(month, montlyRewards);
            }
            rewardsMap.put("Total Rewards", totalRewards);
            rewards.setRewards(rewardsMap);
            customerRewards.add(rewards);
        }
        return customerRewards;
    }



    /**
     * This method is to calculate reward points for the given list of transactions.
     * @param transactions
     * @return
     */
    private Long calculateMontlyRewards(List<Transaction> transactions) {
        Long rewards = 0L;
        for (Transaction transaction : transactions) {
            Long amount = transaction.getAmount();
            long rewardsAmount = amount -100;
            if(rewardsAmount > 0) {
                rewards += 50 + rewardsAmount * 2;
            } else if(amount > 50) {
                rewards = amount -50;
            }
        }
        return rewards;
    }
}
