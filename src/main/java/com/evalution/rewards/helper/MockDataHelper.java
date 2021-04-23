package com.evalution.rewards.helper;

import com.evalution.rewards.entity.Transaction;
import com.evalution.rewards.util.DateUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class MockDataHelper {

    public static List<Transaction> findAll() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            TypeReference<List<Transaction>> typeRef
                    = new TypeReference<List<Transaction>>() {};
            List<Transaction> transactions =
                    objectMapper.readValue(new ClassPathResource("./mock/data.json").getFile(), typeRef);
            return transactions;
        }catch(IOException e) {
            return Collections.emptyList();
        }
    }

    public static List<Transaction> mockTrasactions() {
        List<Transaction> trasactions = new ArrayList<Transaction>();
        Transaction trasaction1 = new Transaction();
        trasaction1.setAmount(100l); // 50
        trasaction1.setCustomerID("CUST1");
        trasaction1.setTransactionRef("REF1");
        trasaction1.setTransactionDate(DateUtil.getFormatDate("2020-01-01"));
        trasactions.add(trasaction1);

        Transaction trasaction2 = new Transaction();
        trasaction2.setAmount(120l); // 20 * 2 + 50 = 90
        trasaction2.setCustomerID("CUST1");
        trasaction2.setTransactionRef("REF1");
        trasaction2.setTransactionDate(DateUtil.getFormatDate("2020-01-02"));
        trasactions.add(trasaction2);


        Transaction trasaction3 = new Transaction();
        trasaction3.setAmount(40l); // 0
        trasaction3.setCustomerID("CUST1");
        trasaction3.setTransactionRef("REF1");
        trasaction3.setTransactionDate(DateUtil.getFormatDate("2020-01-03"));
        trasactions.add(trasaction3);
        //140 rewards
        return trasactions;
    }


    public static Transaction createTrasaction(String id,
                                               String ref,
                                               String trasactionDate,Long amount) {
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setCustomerID(id);
        transaction.setTransactionRef(ref);
        transaction.setTransactionDate(DateUtil.getFormatDate(trasactionDate));
        return transaction;
    }
}
