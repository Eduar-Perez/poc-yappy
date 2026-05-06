package co.com.periferia.payment.yappy.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "billing", schema = "public")
@Getter
@Setter
public class BillingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "discount")
    private String discount;

    @Column(name = "taxes")
    private double taxes;

    @Column(name = "subtotal")
    private double subtotal;

    @Column(name = "total")
    private double total;

    @Column(name = "value_min")
    private double valueMin;

    @Column(name = "value_max")
    private double valueMax;

    @OneToOne
    @JoinColumn(name = "tx_id", referencedColumnName = "id")
    private TxYampyEntity tx;
}