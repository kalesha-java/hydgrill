package com.example.restaurant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.restaurant.model.OrderEntity;

@Repository
public interface OrderRespository extends JpaRepository<OrderEntity, Long>{

	@Query(nativeQuery=true, value="select * from orders where user_id=:userId")
	List<OrderEntity> findByUserId(Long userId);

	
}
