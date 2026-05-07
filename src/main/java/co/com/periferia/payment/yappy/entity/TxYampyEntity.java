package co.com.periferia.payment.yappy.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "tx", schema = "public")
@Getter
@Setter
public class TxYampyEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "merchant_id")
	private String merchantid;

	@Column(name = "order_id")
	private String orderId;

	@Column(name = "payment_date")
	private Date paymentDate;

	@Column(name = "ipn_url")
	private String ipnUrl;

	@Column(name = "domain")
	private String domain;

	@Column(name = "status")
	private String status;

	@OneToOne
	@JoinColumn(name = "customer_cc", referencedColumnName = "cc")
	private CustomerYampyEntity customer;

	@OneToOne(mappedBy = "tx", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private BillingEntity billing;
}