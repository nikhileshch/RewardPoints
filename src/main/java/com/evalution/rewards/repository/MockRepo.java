package com.evalution.rewards.repository;

import com.evalution.rewards.entity.Transaction;
import com.evalution.rewards.helper.MockDataHelper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import java.util.List;

@Component
public class MockRepo {


    public List<Transaction> findAll() {
        return MockDataHelper.findAll();
    }

}
