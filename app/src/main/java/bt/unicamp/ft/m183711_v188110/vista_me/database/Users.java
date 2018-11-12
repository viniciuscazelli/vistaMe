package bt.unicamp.ft.m183711_v188110.vista_me.database;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
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
import bt.unicamp.ft.m183711_v188110.vista_me.entities.Product;
import bt.unicamp.ft.m183711_v188110.vista_me.entities.User;
import bt.unicamp.ft.m183711_v188110.vista_me.enumerations.e_Sexo;
import bt.unicamp.ft.m183711_v188110.vista_me.interfaces.DbExecuteWithReturn;

import static android.support.constraint.Constraints.TAG;

public class Users {

    public static void checkedCPFRegisted(final DbExecuteWithReturn callback, String cpf){

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Query query  = db.collection("users").whereEqualTo("cpf",cpf);

        query.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            callback.onReturn(task.getResult().size() > 0);
                        }
                    }
                });
    }

    public static void checkedUserNameRegisted(final DbExecuteWithReturn callback, String username){

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Query query  = db.collection("users").whereEqualTo("username",username);

        query.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            callback.onReturn(task.getResult().size() > 0);
                        }
                    }
                });
    }

    public static void checkedUserRegisted(final DbExecuteWithReturn callback,String username, String cpf){

        final boolean[] hasCreated = {false};
        final int[] countRetorn = {0};

        checkedCPFRegisted(new DbExecuteWithReturn() {
            @Override
            public void onReturn(Object obj) {
                hasCreated[0] = hasCreated[0] || (boolean)obj;
                countRetorn[0]++;

                if(countRetorn[0] > 1){
                    callback.equals(hasCreated[0]);
                }
            }
        },cpf);

        checkedUserNameRegisted(new DbExecuteWithReturn() {
            @Override
            public void onReturn(Object obj) {
                hasCreated[0] = hasCreated[0] || (boolean)obj;
                countRetorn[0]++;

                if(countRetorn[0] > 1){
                    callback.equals(hasCreated[0]);
                }
            }
        },username);

    }


    public static void getUserById(final DbExecuteWithReturn callback,String UserID){
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("users").document(UserID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        User user = new User(document.getId(),
                                document.getString("name"),
                                document.getString("lastname"),
                                document.getString("CPF"),
                                document.getString("address"),
                                document.getString("number"),
                                document.getString("distric"),
                                document.getString("city"),
                                document.getString("country"),
                                document.getString("CEP"),
                                document.getString("username"),
                                document.getString("password"),
                                null,
                                null,
                                document.getString("sexo"));
                        callback.onReturn(user);
                        return;
                    }
                    callback.onReturn(null);
                }
            }
        });
    }



    public static void validLogin(final DbExecuteWithReturn callback, String username, String password){

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Query query  = db.collection("users").whereEqualTo("username",username).whereEqualTo("password",password);

        query.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            if(task.getResult().size() > 0) {

                                for (DocumentSnapshot document : task.getResult()) {
                                    User user = new User(document.getId(),
                                            document.getString("name"),
                                            document.getString("lastname"),
                                            document.getString("CPF"),
                                            document.getString("address"),
                                            document.getString("number"),
                                            document.getString("distric"),
                                            document.getString("city"),
                                            document.getString("country"),
                                            document.getString("CEP"),
                                            document.getString("username"),
                                            document.getString("password"),
                                            null,
                                            null,
                                            document.getString("sexo"));
                                    callback.onReturn(user);
                                    return;
                                }

                            }else{
                                callback.onReturn(null);
                            }
                        }
                    }
                });
    }

    public static void createUser(final DbExecuteWithReturn callback, String name, String lastname, String CPF, String address, String number,
                                  String distric, String city, String country, String CEP, String username,
                                  String password,  String sexo){

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();
        user.put("name", name);
        user.put("lastname", lastname);
        user.put("CPF", CPF);
        user.put("address", address);
        user.put("number", number);
        user.put("distric", distric);
        user.put("city", city);
        user.put("country", country);
        user.put("CEP", CEP);
        user.put("username", username);
        user.put("password", password);
        user.put("sexo", sexo);



        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        callback.onReturn(documentReference.getId());
                    }
                });
    }

    public static void getCardsByID(final DbExecuteWithReturn callback, String userId, String cardID){

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users").document(userId).collection("cards").document(cardID).get()
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
                                card.setMonth(Integer.parseInt(document.getString("month")));
                                card.setYear(Integer.parseInt(document.getString("year")));

                                callback.onReturn(card);
                            }
                        }
                    }
                });

    }

    public static void getCardsUser(final DbExecuteWithReturn callback, String userId){

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("users").document(userId).collection("cards").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        ArrayList<Card> cards = new ArrayList<>();

                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {

                                Card card = new Card();

                                card.setDocumentNumber(document.getString("documentNumber"));
                                card.setNameHolder(document.getString("NameHolder"));
                                card.setNumber(document.getString("Number"));
                                card.setMonth(Integer.parseInt(document.getString("month")));
                                card.setYear(Integer.parseInt(document.getString("year")));

                                cards.add(card);
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());

                        }

                        if(callback != null){
                            callback.onReturn(cards);
                        }
                    }
                });

    }



}
