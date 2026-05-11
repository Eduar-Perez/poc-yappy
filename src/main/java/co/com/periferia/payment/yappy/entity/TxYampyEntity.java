package co.com.periferia.payment.yappy.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "tx", schema = "public")
@Getter
@Setter
public class TxYampyEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "order_id")
	private String orderId;

	@Column(name = "payment_date")
	private LocalDateTime paymentDate;

	@Column(name = "ipn_url")
	private String ipnUrl;

	@Column(name = "status")
	private String status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "customer_cc", referencedColumnName = "cc")
	private CustomerYampyEntity customer;

	@OneToOne(mappedBy = "tx", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private BillingEntity billing;
}