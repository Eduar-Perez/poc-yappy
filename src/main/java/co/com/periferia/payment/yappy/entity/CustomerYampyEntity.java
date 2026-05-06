package co.com.periferia.payment.yappy.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "customer", schema = "public")
@Getter
@Setter
public class CustomerYampyEntity {

    @Id
    @Column(name = "cc", nullable = false, length = 50)
    private String cc;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "document_type")
    private String documentType;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private TxYampyEntity tx;
}