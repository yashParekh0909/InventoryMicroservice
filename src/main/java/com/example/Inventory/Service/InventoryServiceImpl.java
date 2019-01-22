package com.example.Inventory.Service;

import com.example.Inventory.Entity.CartCheckQuantity;
import com.example.Inventory.Entity.Inventory;
import com.example.Inventory.Repository.InventoryRepository;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService{

    @Autowired
    InventoryRepository inventoryRepository;

    @Override
    public List<Inventory> getProducts(int index, int size) {

        int offsetValue = index * size;

        return inventoryRepository.getInventoryList(size, offsetValue);

    }

    @Override
    public Boolean decreaseStock(List<JSONObject> listOfItems) {
        String merchantId, productId;
        int purchasedQuantity;
        try {
            for (JSONObject jsonObject : listOfItems) {

                merchantId = (String) jsonObject.get("merchantId");
                productId = (String) jsonObject.get("productId");
                purchasedQuantity = (int) jsonObject.get("purchasedQuantity");

                inventoryRepository.decreaseStock(merchantId, productId, purchasedQuantity);
            }
            return true;
        } catch (Exception ex){
            return false;
        }
    }

    @Override
    public List<CartCheckQuantity> checkItems(List<CartCheckQuantity> cartCheckQuantities) {

        List<CartCheckQuantity> list = new ArrayList<>();

        for(CartCheckQuantity checkQuantity: cartCheckQuantities){
            String merchantId = checkQuantity.getMerchantId();
            String productId = checkQuantity.getProductId();
            Integer quantity = checkQuantity.getQuantity();

            try {
                Integer stock = inventoryRepository.checkStock(merchantId, productId);


                if(quantity <= stock){
                    checkQuantity.setStatus(true);
                } else {
                    checkQuantity.setStatus(false);
                    checkQuantity.setMsg("Product out of stock.");
                }

            } catch (NullPointerException ex){
                checkQuantity.setStatus(false);
                checkQuantity.setMsg("No product exist.");
            }
            list.add(checkQuantity);
        }
        return list;
    }
}




/*
GET REQUEST FUNCTION :

 public static void MyGETRequest() throws IOException {
    URL urlForGetRequest = new URL("https://jsonplaceholder.typicode.com/posts/1");
    String readLine = null;
    HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
    conection.setRequestMethod("GET");
    conection.setRequestProperty("userId", "a1bcdef"); // set userId its a sample here
    int responseCode = conection.getResponseCode();
    if (responseCode == HttpURLConnection.HTTP_OK) {
        BufferedReader in = new BufferedReader(
            new InputStreamReader(conection.getInputStream()));
        StringBuffer response = new StringBuffer();
        while ((readLine = in .readLine()) != null) {
            response.append(readLine);
        } in .close();
        // print result
        System.out.println("JSON String Result " + response.toString());
        //GetAndPost.POSTRequest(response.toString());
    } else {
        System.out.println("GET NOT WORKED");
    }
}
--------------------------------------------------------------------------------
GET REQUEST OUTPUT :

JSON String Result {
    "userId": 1,
    "id": 1,
    "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
    "body": "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
}

-----------------------------------------------------------------------------------

POST REQUEST FUNCTION :

public static void POSTRequest() throws IOException {
    final String POST_PARAMS = "{\n" + "\"userId\": 101,\r\n" +
        "    \"id\": 101,\r\n" +
        "    \"title\": \"Test Title\",\r\n" +
        "    \"body\": \"Test Body\"" + "\n}";
    System.out.println(POST_PARAMS);
    URL obj = new URL("https://jsonplaceholder.typicode.com/posts");
    HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
    postConnection.setRequestMethod("POST");
    postConnection.setRequestProperty("userId", "a1bcdefgh");
    postConnection.setRequestProperty("Content-Type", "application/json");
    postConnection.setDoOutput(true);
    OutputStream os = postConnection.getOutputStream();
    os.write(POST_PARAMS.getBytes());
    os.flush();
    os.close();
    int responseCode = postConnection.getResponseCode();
    System.out.println("POST Response Code :  " + responseCode);
    System.out.println("POST Response Message : " + postConnection.getResponseMessage());
    if (responseCode == HttpURLConnection.HTTP_CREATED) { //success
        BufferedReader in = new BufferedReader(new InputStreamReader(
            postConnection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in .readLine()) != null) {
            response.append(inputLine);
        } in .close();
        // print result
        System.out.println(response.toString());
    } else {
        System.out.println("POST NOT WORKED");
    }
}

-------------------------------------------------------------------------------------

POST REQUEST OUTPUT :


"userId": 101,
    "id": 101,
    "title": "Test Title",
    "body": "Test Body"
}
POST Response Code :  201
POST Response Message : Created
{  "userId": 101,  "id": 101,  "title": "Test Title",  "body": "Test Body"}

----------------------------------------------------------------------------------------
* */
