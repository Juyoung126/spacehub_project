package com.spring.client.payment.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.spring.client.reservation.domain.Reservation;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@Table(name = "spacehub_payment")
@SequenceGenerator(name = "spacehub_payment_generator", sequenceName = "spacehub_payment_seq", initialValue = 1, allocationSize = 1)
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "spacehub_payment_generator")
    @Column(name = "p_no")
    private Long pNo;

    @Column(name = "member_no", nullable = false)
    private Long memberNo;

    @ManyToOne
    @JoinColumn(name = "r_no", nullable = false)
    private Reservation reservation;

    @Column(name = "p_amount", nullable = false)
    private int pAmount;

    @Column(name = "p_method", length = 50, nullable = false)
    private String pMethod;

    @Column(name = "p_state", length = 50, nullable = false)
    private String pState;

    @CreationTimestamp
    @Column(name = "p_created_at", nullable = false)
    private LocalDateTime pCreatedAt;

    @UpdateTimestamp
    @Column(name = "p_update_at")
    private LocalDateTime pUpdateAt;

    @Column(name = "p_tid", nullable = false)
    private String pTid;
}
