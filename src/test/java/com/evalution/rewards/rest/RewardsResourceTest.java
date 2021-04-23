package com.evalution.rewards.rest;


import com.evalution.rewards.entity.Transaction;
import com.evalution.rewards.helper.MockDataHelper;
import com.evalution.rewards.model.Rewards;
import com.evalution.rewards.repository.MockRepo;
import com.evalution.rewards.repository.TrasactionRepo;
import com.evalution.rewards.service.RewardsService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RewardsResource.class)
public class RewardsResourceTest {

    @TestConfiguration
    static class RewardsApiEndToEndTestContextConfiguration {

        @Bean
        public RewardsService rewardsService() {
            return new RewardsService();
        }

    }

    @Autowired
    private MockMvc mvc;

    @Autowired
    RewardsService blockListSvcImpl;


    @MockBean
    public TrasactionRepo trasactionRepo;

    @MockBean
    public MockRepo mockRepo;

    @Before
    public void setUp() {
        List<Transaction> trasactions = MockDataHelper.mockTrasactions();
        when(trasactionRepo.findAll()).thenReturn(trasactions);
        when(mockRepo.findAll()).thenReturn(trasactions);
    }

    @Test
    public void testGetRewards() throws Exception
    {
        List<Transaction> trasactions = MockDataHelper.mockTrasactions();
        when(mockRepo.findAll()).thenReturn(trasactions);
        ObjectMapper mapper = new ObjectMapper();
        MvcResult result = mvc.perform( MockMvcRequestBuilders
                .get("/api/rewards/getRewards?isMock=true")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        result.getResponse().getContentAsString();
        TypeReference<List<Rewards>> typeRef
                = new TypeReference<List<Rewards>>() {};
        List<Rewards> rewards =
                mapper.readValue(result.getResponse().getContentAsString(), typeRef);
        Assert.assertNotNull(rewards);
        Assert.assertNotNull(rewards.get(0));
        Assert.assertEquals(rewards.get(0).getCustomerName(),"CUST1");
        Long actualRewards = rewards.get(0).getRewards().get("January");
        Long totalRewards = rewards.get(0).getRewards().get("Total Rewards");
        Assert.assertEquals(140l,actualRewards.longValue());
        Assert.assertEquals(140l,totalRewards.longValue());
    }

    @Test
    public void testGetRewardsIsMockFalse() throws Exception
    {
        List<Transaction> trasactions = MockDataHelper.mockTrasactions();
        when(trasactionRepo.findAll()).thenReturn(trasactions);
        ObjectMapper mapper = new ObjectMapper();
        MvcResult result = mvc.perform( MockMvcRequestBuilders
                .get("/api/rewards/getRewards?isMock=false")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        result.getResponse().getContentAsString();
        TypeReference<List<Rewards>> typeRef
                = new TypeReference<List<Rewards>>() {};
        List<Rewards> rewards =
                mapper.readValue(result.getResponse().getContentAsString(), typeRef);
        Assert.assertNotNull(rewards);
        Assert.assertNotNull(rewards.get(0));
        Assert.assertEquals(rewards.get(0).getCustomerName(),"CUST1");
        Long actualRewards = rewards.get(0).getRewards().get("January");
        Long totalRewards = rewards.get(0).getRewards().get("Total Rewards");
        Assert.assertEquals(140l,actualRewards.longValue());
        Assert.assertEquals(140l,totalRewards.longValue());
    }


    @Test
    public void testCalculateRewards() throws Exception
    {
        List<Transaction> trasactions = MockDataHelper.mockTrasactions();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(trasactions);

        MvcResult result = mvc.perform( MockMvcRequestBuilders
                .post("/api/rewards/calculateRewards")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isOk()).andReturn();
        result.getResponse().getContentAsString();
        TypeReference<List<Rewards>> typeRef
                = new TypeReference<List<Rewards>>() {};
        List<Rewards> rewards =
                mapper.readValue(result.getResponse().getContentAsString(), typeRef);
        Assert.assertNotNull(rewards);
        Assert.assertNotNull(rewards.get(0));
        Assert.assertEquals(rewards.get(0).getCustomerName(),"CUST1");
        Long actualRewards = rewards.get(0).getRewards().get("January");
        Long totalRewards = rewards.get(0).getRewards().get("Total Rewards");
        Assert.assertEquals(140l,actualRewards.longValue());
        Assert.assertEquals(140l,totalRewards.longValue());
    }
}
