package com.example.Inventory.Repository;

import com.example.Inventory.Entity.Inventory;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface InventoryRepository extends CrudRepository<Inventory, String> {

    @Query(name = "getProducts", value = "select * from Inventory limit :size Offset :offsetValue", nativeQuery = true)
    List<Inventory> getInventoryList(@Param("size") int size, @Param("offsetValue") int offsetValue);

    @Transactional
    @Modifying
    @Query(name = "decreaseStock", value = "update Inventory set stock = (select stock from Inventory where merchant_id = :merchantId and product_id = :productId) - :purchasedQuantity where merchant_id = :merchantId and product_id = :productId", nativeQuery = true)
    void decreaseStock(@Param("merchantId") String merchantId,@Param("productId") String productId,@Param("purchasedQuantity") int purchasedQuantity);

    @Query(name = "checkStock", value = "select stock from Inventory where merchant_id = :merchantId and product_id = :productId", nativeQuery = true)
    Integer checkStock(@Param("merchantId") String merchantId, @Param("productId") String productId);

}
