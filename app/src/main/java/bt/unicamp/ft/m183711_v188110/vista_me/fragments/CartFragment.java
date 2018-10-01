package bt.unicamp.ft.m183711_v188110.vista_me.fragments;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

import bt.unicamp.ft.m183711_v188110.vista_me.Cart;
import bt.unicamp.ft.m183711_v188110.vista_me.Login;
import bt.unicamp.ft.m183711_v188110.vista_me.R;
import bt.unicamp.ft.m183711_v188110.vista_me.entities.Product;
import bt.unicamp.ft.m183711_v188110.vista_me.entities.User;
import bt.unicamp.ft.m183711_v188110.vista_me.interfaces.CartNotificChangeListProducts;
import bt.unicamp.ft.m183711_v188110.vista_me.interfaces.FragmentManagerActivity;
import bt.unicamp.ft.m183711_v188110.vista_me.interfaces.MyOnLongItemClickListener;
import bt.unicamp.ft.m183711_v188110.vista_me.myFirstAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class CartFragment extends Fragment implements CartNotificChangeListProducts,MyOnLongItemClickListener, View.OnClickListener {

    private Cart cart;
    private Login login;
    private RecyclerView mRecyclerView;
    private myFirstAdapter mAdapter;
    private FragmentManagerActivity fragmentManagerActivity;
    private TextView total;
    private View fragment;
    private Button checkout;

    @SuppressLint("ValidFragment")
    public CartFragment(Cart cart, FragmentManagerActivity fragmentManagerActivity,Login login) {
        this.cart = cart;
        this.fragmentManagerActivity = fragmentManagerActivity;
        this.login = login;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragment = inflater.inflate(R.layout.fragment_cart, container, false);
        RecyclerView mRecyclerView = fragment.findViewById(R.id.mRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAdapter = new myFirstAdapter(cart.itens,R.layout.adapter_layout_cart);

        mAdapter.setMyOnLongItemClickListener(this);
        cart.setCartNotificChangeListProducts(this);

        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        total = fragment.findViewById(R.id.Total);

        checkout = fragment.findViewById(R.id.Checkout);

        checkout.setOnClickListener(this);

        updateTotal();

        return fragment;
    }

    private void updateTotal(){
        Locale localeBR = new Locale( "pt", "BR" );
        NumberFormat dinheiroBR = NumberFormat.getCurrencyInstance(localeBR);
        System.out.println();

        total.setText(dinheiroBR.format(cart.Total()));
    }

    @Override
    public void notificChangeList() {
        mAdapter.notifyDataSetChanged();
        updateTotal();
    }

    @Override
    public void MyOnLongItemClick(View view, Object obj, int pos) {
        deletar((Product) obj,pos);
    }

    public void deletar(Product p, final int position){
        new AlertDialog.Builder(getActivity())
                .setTitle("Removendo o item")
                .setMessage("Tem certeza que deseja remover este item?")
                .setPositiveButton("sim",
                        new DialogInterface.OnClickListener() {
                            @Override

                            public void onClick(DialogInterface dialogInterface, int i) {
                               cart.Remove(position);
                            }
                        })
                .setNegativeButton("não", null)
                .show();
    }

    @Override
    public void onClick(View v) {
        CheckoutFragment checkoutFragment = new CheckoutFragment(cart, login,fragmentManagerActivity);
        if(login.isLogged())
            fragmentManagerActivity.OpenFragment(checkoutFragment,"checkout",true);
        else {
            LoginFragment loginFragment = new LoginFragment(login,fragmentManagerActivity,checkoutFragment);
            fragmentManagerActivity.OpenFragment(loginFragment,"login",true);
        }
    }
}
