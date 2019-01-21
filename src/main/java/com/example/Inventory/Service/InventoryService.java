package com.example.Inventory.Service;
import com.example.Inventory.Entity.CartCheckQuantity;
import com.example.Inventory.Entity.Inventory;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InventoryService {

    //Get the list of all products based on index and size i.e. For Homepage.

    List<Inventory> getProducts(int index, int size);

    Boolean decreaseStock(List<JSONObject> listOfItems);

    List<CartCheckQuantity> checkItems(List<CartCheckQuantity> cartCheckQuantities);
}
