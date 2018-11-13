package bt.unicamp.ft.m183711_v188110.vista_me.database;

import android.support.annotation.NonNull;

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
import bt.unicamp.ft.m183711_v188110.vista_me.entities.Product;
import bt.unicamp.ft.m183711_v188110.vista_me.enumerations.e_Sexo;
import bt.unicamp.ft.m183711_v188110.vista_me.interfaces.DBExecute;
import bt.unicamp.ft.m183711_v188110.vista_me.interfaces.DbExecuteWithReturn;

public class Cards {

    public static void AddCard(final DBExecute callback, String userId, final Card card) {

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Create a new cardMap with a first and last name
        Map<String, Object> cardMap = new HashMap<>();
        cardMap.put("documentNumber", card.getDocumentNumber());
        cardMap.put("NameHolder", card.getNameHolder());
        cardMap.put("Number", card.getNumber());
        cardMap.put("month", card.getMonth());
        cardMap.put("year", card.getYear());

        db.collection("users").document(userId).collection("cards").add(cardMap)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(final DocumentReference documentReference) {

                        card.setId(documentReference.getId());

                        callback.onReturn();

                    }
                });
    }

    public static void DeleteCard(final DBExecute callback, String userId, String cardId){

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("users").document(userId).collection("cards").document(cardId).delete().
                addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                callback.onReturn();
            }
        });

    }

}
