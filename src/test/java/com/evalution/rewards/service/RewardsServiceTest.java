package com.evalution.rewards.service;

import com.evalution.rewards.entity.Transaction;
import com.evalution.rewards.helper.MockDataHelper;
import com.evalution.rewards.model.Rewards;
import com.evalution.rewards.repository.MockRepo;
import com.evalution.rewards.repository.TrasactionRepo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import java.util.List;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class RewardsServiceTest {

    @InjectMocks
    RewardsService rewardsService;

    @Mock
    public TrasactionRepo trasactionRepo;

    @Mock
    public MockRepo mockRepo;

    @Before
    public void setUp() {

    }

    @Test
    public void testGetRewards() {
        List<Transaction> trasactions = MockDataHelper.findAll();
        when(trasactionRepo.findAll()).thenReturn(trasactions);
        List<Rewards> rewards = rewardsService.getRewards();
        Assert.assertNotNull(rewards);
        System.out.println(rewards);
    }

    @Test
    public void testGetRewardsCase1() {
        List<Transaction> trasactions = MockDataHelper.mockTrasactions();
        when(trasactionRepo.findAll()).thenReturn(trasactions);
        List<Rewards> rewards = rewardsService.getRewards();
        Assert.assertNotNull(rewards);
        Assert.assertEquals(rewards.size(),1);
        Assert.assertNotNull(rewards.get(0));
        Assert.assertEquals(rewards.get(0).getCustomerName(),"CUST1");
        Long actualRewards = rewards.get(0).getRewards().get("January");
        Long totalRewards = rewards.get(0).getRewards().get("Total Rewards");
        Assert.assertEquals(140l,actualRewards.longValue());
        Assert.assertEquals(140l,totalRewards.longValue());
    }

}
