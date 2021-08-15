package jpabook.jpashop.domain;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
public class OrderItem {

	@Id @GeneratedValue
	@Column(name = "ORDER_ITEM_ID")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "ORDER_ID")
	private Order order;
	
	@ManyToOne
	@JoinColumn(name = "ITEM_ID")
	private Item item;
	
	private int orderPrice;
	private int count;
	
	private LocalDateTime orderDate;
	
	private OrderStatus status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(int orderPrice) {
		this.orderPrice = orderPrice;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
}
