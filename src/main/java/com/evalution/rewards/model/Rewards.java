package com.evalution.rewards.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public class Rewards {


    private String customerName;
    private Map<String, Long> rewards;
}
