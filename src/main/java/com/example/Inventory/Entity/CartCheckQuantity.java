package com.example.Inventory.Entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = CartCheckQuantity.TABLE_NAME)
public class CartCheckQuantity {

    public static final String TABLE_NAME = "CartCheckQuantity";


    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name="uuid",strategy = "uuid2")
    private String checkQuantity;

    private String merchantId;
    private String productId;
    private Integer quantity;
    private Boolean status;
    private String msg;

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getCheckQuantity() {
        return checkQuantity;
    }

    public void setCheckQuantity(String checkQuantity) {
        this.checkQuantity = checkQuantity;
    }
}
