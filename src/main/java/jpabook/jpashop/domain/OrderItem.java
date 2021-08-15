package jpabook.jpashop.domain;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
public class OrderItem {

	@Id @GeneratedValue
	@Column(name = "ORDER_ITEM_ID")
	private Long id;
	
	@Column(name = "MEMBER_ID")
	private Long memberId;
	
	private LocalDateTime orderDate;
	
	private OrderStatus status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	
}
