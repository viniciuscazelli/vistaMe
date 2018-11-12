package bt.unicamp.ft.m183711_v188110.vista_me.database;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import bt.unicamp.ft.m183711_v188110.vista_me.entities.Product;
import bt.unicamp.ft.m183711_v188110.vista_me.enumerations.e_Sexo;
import bt.unicamp.ft.m183711_v188110.vista_me.interfaces.DbExecuteWithReturn;

import static android.support.constraint.Constraints.TAG;

public class Products {


    public static void getProductByID(final DbExecuteWithReturn callback, String idProduct) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("users").document(idProduct).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {

                                Product product = new Product(document.getId());

                                product.setDescricao(document.getString("descricao"));
                                product.setNome(document.getString("nome"));
                                product.setValor(document.getDouble("valor"));
                                product.setMaxDivider(10);//Integer.parseInt(document.getString("maxDivider"))
                                product.setSexo(document.getString("sexo") == "masculino"? e_Sexo.masculino:e_Sexo.feminino);
                                product.setUrl(document.getString("url"));

                                try{
                                    product.setFoto(ImageLoader.baixarImagem(document.getString("url")));
                                }catch (Exception ex){

                                    String erro = ex.toString();
                                    erro  = "erro -> " + erro;
                                }

                                callback.onReturn(product);
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }


                    }
                });
    }

    public static void getAllProducts(final DbExecuteWithReturn callback, e_Sexo sexo){

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Query query = null;

        if(sexo != null)
            query = db.collection("products").whereEqualTo("sexo", sexo == e_Sexo.masculino ? "masculino" : "feminino");
        else
            query = db.collection("products");

        query.get()
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
                                product.setUrl(document.getString("url"));
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

}
