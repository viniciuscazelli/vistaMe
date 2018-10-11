package bt.unicamp.ft.m183711_v188110.vista_me.fragments;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import bt.unicamp.ft.m183711_v188110.vista_me.Cart;
import bt.unicamp.ft.m183711_v188110.vista_me.Login;
import bt.unicamp.ft.m183711_v188110.vista_me.Products;
import bt.unicamp.ft.m183711_v188110.vista_me.R;
import bt.unicamp.ft.m183711_v188110.vista_me.entities.Card;
import bt.unicamp.ft.m183711_v188110.vista_me.entities.Order;
import bt.unicamp.ft.m183711_v188110.vista_me.entities.Product;
import bt.unicamp.ft.m183711_v188110.vista_me.entities.User;
import bt.unicamp.ft.m183711_v188110.vista_me.interfaces.FragmentManagerActivity;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class CheckoutFragment extends Fragment implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    private RadioGroup radioGroup;
    private RadioGroup RadioGroupCards;

    private Button addCardButton;
    private Button Checkout;

    private View fragment;
    private Cart cart;
    private Locale localeBR = new Locale("pt", "BR");
    private NumberFormat dinheiroBR = NumberFormat.getCurrencyInstance(localeBR);
    private TextView total;
    private TextView frete;
    private TextView totalGeral;
    private int dividerSelected = 1;
    private Card cardSelected = null;
    private Login login;

    private TextView addresstxt;
    private TextView numbertxt;
    private TextView districtxt;
    private TextView citytxt;
    private TextView countrytxt;
    private TextView CEPtxt;
    private TextView TotalItens;

    private RadioGroup RadioNoCards;

    private FragmentManagerActivity fragmentManagerActivity;

    @SuppressLint("ValidFragment")
    public CheckoutFragment(Cart cart, Login login, FragmentManagerActivity fragmentManagerActivity) {
        this.cart = cart;
        this.login = login;
        this.fragmentManagerActivity = fragmentManagerActivity;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragment = inflater.inflate(R.layout.fragment_checkout, container, false);

        addCardButton = fragment.findViewById(R.id.addCardButton);
        addCardButton.setOnClickListener(this);

        RadioGroupCards = fragment.findViewById(R.id.RadioGroupCards);
        RadioButton button;
        RadioNoCards = fragment.findViewById(R.id.RadioNoCards);


        if (login.getUser().getCards() == null || login.getUser().getCards().size() < 1) {
            RadioNoCards.setVisibility(View.VISIBLE);
            RadioGroupCards.setVisibility(View.INVISIBLE);
        } else {
            RadioNoCards.setVisibility(View.INVISIBLE);
            RadioGroupCards.setVisibility(View.VISIBLE);
            boolean first = true;
            for (Card card : login.getUser().getCards()) {
                button = new RadioButton(getActivity());
                button.setTag(card);
                button.setText("(" + card.getCardTypeName() + ") " + card.getNumber(true));
                RadioGroupCards.addView(button);
                if (first) {
                    RadioGroupCards.check(button.getId());
                    first = false;
                    cardSelected = card;
                }
            }
        }

        RadioGroupCards.setOnCheckedChangeListener(this);

        radioGroup = fragment.findViewById(R.id.RadioGroup);


        for (int i = 1; i <= 10; i++) {
            button = new RadioButton(getActivity());
            button.setText("Em " + i + "x de " + dinheiroBR.format((cart.TotalGeral()) / i) + " sem juros");
            button.setTag(i);
            radioGroup.addView(button);
            if (i == 1)
                radioGroup.check(button.getId());
        }

        radioGroup.setOnCheckedChangeListener(this);

        total = fragment.findViewById(R.id.Total);
        frete = fragment.findViewById(R.id.frete);
        totalGeral = fragment.findViewById(R.id.TotalGeral);


        addresstxt = fragment.findViewById(R.id.addresstxt);
        numbertxt = fragment.findViewById(R.id.numbertxt);
        districtxt = fragment.findViewById(R.id.districtxt);
        citytxt = fragment.findViewById(R.id.citytxt);
        countrytxt = fragment.findViewById(R.id.countrytxt);
        CEPtxt = fragment.findViewById(R.id.CEPtxt);
        TotalItens = fragment.findViewById(R.id.TotalItens);

        Checkout = fragment.findViewById(R.id.Checkout);

        Checkout.setOnClickListener(this);

        total.setText(dinheiroBR.format(cart.Total()));
        frete.setText(dinheiroBR.format(cart.Frete()));
        totalGeral.setText(dinheiroBR.format(cart.TotalGeral()));
        TotalItens.setText(cart.itens.size() + (cart.itens.size() > 1 ? " itens" : " item"));

        addresstxt.setText(login.getUser().getAddress());
        numbertxt.setText(login.getUser().getNumber());
        districtxt.setText(login.getUser().getDistric());
        citytxt.setText(login.getUser().getCity());
        countrytxt.setText(login.getUser().getCountry());
        CEPtxt.setText(login.getUser().getCEP());

        Checkout.setClickable(dividerSelected > 0 && cardSelected != null);

        return fragment;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        if (group.getId() == R.id.RadioGroup)
            dividerSelected = (int) fragment.findViewById(checkedId).getTag();
        else {
            cardSelected = (Card) fragment.findViewById(checkedId).getTag();
        }

        Checkout.setClickable(dividerSelected > 0 && cardSelected != null);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.addCardButton) {
            CardFragment cardFragment = new CardFragment(login, fragmentManagerActivity);
            fragmentManagerActivity.OpenFragment(cardFragment, "Card", true);
        }else {
            Random rand = new Random();



            Order order = new Order(cart.itens, dividerSelected, cardSelected, new Date(), String.format("%08d",rand.nextInt(99999999)),"Em aprovação",cart.Frete());

            login.getUser().addOrder(order);

            cart.itens = new ArrayList<Product>();

            CheckoutOkFragment checkoutOkFragment = new CheckoutOkFragment(fragmentManagerActivity, order.getNumber());

            fragmentManagerActivity.OpenFragment(checkoutOkFragment, "checkoutOk", true);
        }
    }
}
