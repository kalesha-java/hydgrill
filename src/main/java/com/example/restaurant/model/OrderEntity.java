package com.example.restaurant.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class OrderEntity {

	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    private Long userId;
	    
	    private String orderJson;
	    
	    private Double orderPrice;
	    
	    private Date createdDate;

		public Long getUserId() {
			return userId;
		}

		public void setUserId(Long userId) {
			this.userId = userId;
		}

		public String getOrderJson() {
			return orderJson;
		}

		public void setOrderJson(String orderJson) {
			this.orderJson = orderJson;
		}

		public Double getOrderPrice() {
			return orderPrice;
		}

		public void setOrderPrice(Double orderPrice) {
			this.orderPrice = orderPrice;
		}

		public Date getCreatedDate() {
			return createdDate;
		}

		public void setCreatedDate(Date createdDate) {
			this.createdDate = createdDate;
		}
	    
	    
}
