package com.evalution.rewards.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@Entity
@Table(name = "trasaction")
public class Transaction extends Auditable {


    @Id
    @Column(name = "t_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pk;

    @Column(name = "CUST_ID")
    private String customerID;

    @Column(name = "trans_ref")
    private String transactionRef;

    @Column(name = "amount")
    private Long amount;

    @Column(name = "trans_date")
    private Date transactionDate;
}
