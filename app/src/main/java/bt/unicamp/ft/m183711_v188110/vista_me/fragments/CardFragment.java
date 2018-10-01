package bt.unicamp.ft.m183711_v188110.vista_me.fragments;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import bt.unicamp.ft.m183711_v188110.vista_me.Login;
import bt.unicamp.ft.m183711_v188110.vista_me.R;
import bt.unicamp.ft.m183711_v188110.vista_me.entities.Card;
import bt.unicamp.ft.m183711_v188110.vista_me.interfaces.FragmentManagerActivity;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class CardFragment extends Fragment implements View.OnClickListener {

    private Login login;
    private FragmentManagerActivity fragmentManagerActivity;
    private View fragment;

    private EditText numberCard;
    private EditText codSecurity;
    private EditText name;
    private EditText month;
    private EditText year;
    private TextView message;
    private Button confirm;

    @SuppressLint("ValidFragment")
    public CardFragment(Login login, FragmentManagerActivity fragmentManagerActivity) {
        this.fragmentManagerActivity = fragmentManagerActivity;
        this.login = login;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragment = inflater.inflate(R.layout.fragment_card, container, false);

        numberCard = fragment.findViewById(R.id.numberCard);
        codSecurity = fragment.findViewById(R.id.codSecurity);
        name = fragment.findViewById(R.id.name);
        month = fragment.findViewById(R.id.month);
        year = fragment.findViewById(R.id.year);
        message = fragment.findViewById(R.id.message);
        confirm = fragment.findViewById(R.id.confirm);

        confirm.setOnClickListener(this);

        return fragment;
    }

    @Override
    public void onClick(View v) {
        String number = numberCard.getText().toString();
        String cod = codSecurity.getText().toString();
        String nameCard = name.getText().toString();
        String monthCard = month.getText().toString();
        String yearCard = year.getText().toString();

        if(number.isEmpty() || cod.isEmpty() || nameCard.isEmpty() || monthCard.isEmpty() || yearCard.isEmpty()){
            message.setText("Preencha os campos corretamente");
        }else {
            Card card = new Card(number,cod,nameCard,Integer.parseInt(monthCard),Integer.parseInt(yearCard));

            if(card.isValid()){
                if(login.getUser().getCards() == null)
                    login.getUser().setCards(new ArrayList<Card>());
                login.getUser().getCards().add(card);
                fragmentManagerActivity.RemoveFragment(this);
            }else{
                message.setText("Dados invalidos corrija para continuar");
            }
        }
    }
}
