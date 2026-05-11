package co.com.periferia.payment.yappy.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "customer", schema = "public")
@Getter
@Setter
@RequiredArgsConstructor
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
    
    @Column(name = "role")
    private String role;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TxYampyEntity> tx;
}