package com.example.ecommerce.repository;

import antlr.collections.List;
import com.example.ecommerce.domain.OrdersItem;
import com.example.ecommerce.model.ChartProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersItemRepository extends JpaRepository<OrdersItem,Long> {

    @Query("select distinct b.name , count (a) as SL from OrdersItem a join  Product b on b.id = a.product.id " +
            " group by b.name order by SL desc")
    Object[] countProductTop();
}
