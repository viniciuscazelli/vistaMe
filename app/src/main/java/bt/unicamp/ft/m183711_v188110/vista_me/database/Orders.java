package bt.unicamp.ft.m183711_v188110.vista_me.database;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import bt.unicamp.ft.m183711_v188110.vista_me.entities.Card;
import bt.unicamp.ft.m183711_v188110.vista_me.entities.Order;
import bt.unicamp.ft.m183711_v188110.vista_me.entities.Product;
import bt.unicamp.ft.m183711_v188110.vista_me.enumerations.e_Sexo;
import bt.unicamp.ft.m183711_v188110.vista_me.interfaces.DBExecute;
import bt.unicamp.ft.m183711_v188110.vista_me.interfaces.DbExecuteWithReturn;

import static android.support.constraint.Constraints.TAG;

public class Orders {

    public static void getOrderByUserId(final DbExecuteWithReturn callback, final String idUser){

        final FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("users").document(idUser).collection("orders").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        final ArrayList<Order> orders = new ArrayList<>();
                        final int[] LeftLoadding = {task.getResult().size() * 2};
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {

                                final int[] count = {0};

                                final Order order = new Order();

                                order.setDate(document.getDate("date"));
                                order.setStatus(document.getString("status"));
                                order.setNumber(document.getId());
                                order.setDivider(Integer.valueOf(document.getDouble("divider").intValue()));
                                order.setFrete(document.getDouble("frete"));


                                db.collection("users").document(idUser).collection("orders").document(document.getId()).collection("cards").get()
                                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                                   @Override
                                                                   public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                                       if (task.isSuccessful()) {
                                                                           for (DocumentSnapshot document : task.getResult()) {

                                                                               Card card = new Card();

                                                                               card.setDocumentNumber(document.getString("documentNumber"));
                                                                               card.setNameHolder(document.getString("NameHolder"));
                                                                               card.setNumber(document.getString("Number"));
                                                                               card.setId(document.getId());
                                                                               card.setMonth(Integer.valueOf(document.getDouble("month").intValue()));
                                                                               card.setYear(Integer.valueOf(document.getDouble("year").intValue()));

                                                                               order.setCard(card);

                                                                               count[0]++;
                                                                               if(count[0] == 2)
                                                                                   orders.add(order);

                                                                               LeftLoadding[0]--;
                                                                               if(LeftLoadding[0] == 0){
                                                                                   callback.onReturn(orders);
                                                                               }
                                                                               return;
                                                                           }
                                                                       }
                                                                   }
                                                               });

                                getProductsInOrder(new DbExecuteWithReturn() {
                                    @Override
                                    public void onReturn(Object obj) {

                                        order.setItens((ArrayList<Product>) obj);

                                        count[0]++;
                                        if(count[0] == 2)
                                            orders.add(order);


                                        LeftLoadding[0]--;
                                        if(LeftLoadding[0] == 0){
                                            callback.onReturn(orders);
                                        }
                                    }
                                },idUser,document.getId());

                                //orders.add(order);
                            }

                            //callback.onReturn(orders);
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }

//                        if(callback != null){
//                            callback.onReturn(orders);
//                        }
                    }
                });
    }

    private static void getCardsByID(final DbExecuteWithReturn callback, String userId, String orderID, String cardID){

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users").document(userId).collection("orders").document(orderID).collection("cards").document(cardID).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {

                                Card card = new Card();

                                card.setDocumentNumber(document.getString("documentNumber"));
                                card.setNameHolder(document.getString("NameHolder"));
                                card.setNumber(document.getString("Number"));
                                card.setId(document.getId());
                                card.setMonth(Integer.valueOf(document.getDouble("month").intValue()));
                                card.setYear(Integer.valueOf(document.getDouble("year").intValue()));

                                callback.onReturn(card);
                            }
                        }
                    }
                });

    }

    private static void getProductsInOrder(final DbExecuteWithReturn callback,String idUser,String idOrder){

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users").document(idUser).collection("orders").document(idOrder).collection("Products").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                ArrayList<Product> products = new ArrayList<>();

                if (task.isSuccessful()) {
                    for (DocumentSnapshot document : task.getResult()) {

                        Product product = new Product(document.getId());

                        product.setDescricao(document.getString("descricao"));
                        product.setNome(document.getString("nome"));
                        product.setValor(document.getDouble("valor"));
                        product.setMaxDivider(10);//Integer.parseInt(document.getString("maxDivider"))
                        product.setSexo(document.getString("sexo") == "masculino"? e_Sexo.masculino:e_Sexo.feminino);
                        try{
                            product.setFoto(ImageLoader.baixarImagem(document.getString("url")));
                        }catch (Exception ex){

                            String erro = ex.toString();
                            erro  = "erro -> " + erro;
                        }

                        products.add(product);
                    }
                } else {
                    Log.w(TAG, "Error getting documents.", task.getException());
                }

                if(callback != null){
                    callback.onReturn(products);
                }
            }
        });
    }

    public static void saveOrder(final DBExecute callback, final Order order, final String userID){
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Create a new OrderMap with a first and last name
        Map<String, Object> OrderMap = new HashMap<>();
        OrderMap.put("date", order.getDate());
        OrderMap.put("status", order.getStatus());
        OrderMap.put("divider", order.getDivider());
        OrderMap.put("frete", order.getFrete());

        db.collection("users")
                .document(userID)
                .collection("orders")
                .add(OrderMap)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(final DocumentReference documentReference) {
                        order.setNumber(documentReference.getId());

                        saveProducts(new DBExecute() {
                            @Override
                            public void onReturn() {


                                saveCard(callback,order.getCard(),userID,documentReference.getId());

                            }
                        },order.getItens(),userID,documentReference.getId());


                    }
                });
    }

    private static void saveProducts(final DBExecute callback, final ArrayList<Product> products, String userID, String ordersID){

        final Integer[] count = {0};

        for(final Product product: products){
            FirebaseFirestore db = FirebaseFirestore.getInstance();

            // Create a new productMap with a first and last name
            Map<String, Object> productMap = new HashMap<>();
            productMap.put("descricao", product.getDescricao());
            productMap.put("nome", product.getNome());
            productMap.put("maxDivider", product.getMaxDivider());
            productMap.put("valor", product.getValor());
            productMap.put("sexo", product.getSexo() == e_Sexo.masculino? "masculino":"feminino" );
            productMap.put("url", product.getUrl());

            db.collection("users")
                    .document(userID)
                    .collection("orders")
                    .document(ordersID)
                    .collection("Products")
                    .add(productMap)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {

                            product.setId(documentReference.getId());

                            count[0]++;

                            if(count[0] == products.size())
                                callback.onReturn();
                        }
                    });

        }



    }

    private static void saveCard(final DBExecute callback, final Card card, String userID, String ordersID){
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Create a new cardMap with a first and last name
        Map<String, Object> cardMap = new HashMap<>();
        cardMap.put("documentNumber", card.getDocumentNumber());
        cardMap.put("NameHolder", card.getNameHolder());
        cardMap.put("Number", card.getNumber());
        cardMap.put("month", card.getMonth());
        cardMap.put("year", card.getYear());

        db.collection("users")
                .document(userID)
                .collection("orders")
                .document(ordersID)
                .collection("cards")
                .add(cardMap)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                            callback.onReturn();
                    }
                });

    }
}
