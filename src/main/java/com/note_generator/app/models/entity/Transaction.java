package com.note_generator.app.models.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "amount_in_cents")
    private Integer amountInCents;
    private String currency;
    private String reference;
    private Integer installments;

    @Column(name = "payment_source_id")
    private Integer paymentSourceId;

    @Column(name = "create_at")
    private Date createAt;

    @Column(name = "update_at")
    private Date updateAt;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ride_id", referencedColumnName = "id")
    private Ride ride;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pay_method_id", referencedColumnName = "id")
    private PayMethod payMethod;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public Ride getRide() {
        return ride;
    }

    public void setRide(Ride ride) {
        this.ride = ride;
    }

    public Integer getAmountInCents() {
        return amountInCents;
    }

    public void setAmountInCents(Integer amountInCents) {
        this.amountInCents = amountInCents;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Integer getInstallments() {
        return installments;
    }

    public void setInstallments(Integer installments) {
        this.installments = installments;
    }

    public Integer getPaymentSourceId() {
        return paymentSourceId;
    }

    public void setPaymentSourceId(Integer paymentSourceId) {
        this.paymentSourceId = paymentSourceId;
    }

}
