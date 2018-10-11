package bt.unicamp.ft.m183711_v188110.vista_me.fragments;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import bt.unicamp.ft.m183711_v188110.vista_me.entities.Product;
import bt.unicamp.ft.m183711_v188110.vista_me.R;
import bt.unicamp.ft.m183711_v188110.vista_me.interfaces.BuyItemEventListener;
import bt.unicamp.ft.m183711_v188110.vista_me.interfaces.FragmentManagerActivity;
import bt.unicamp.ft.m183711_v188110.vista_me.interfaces.MyOnItemClickListener;
import bt.unicamp.ft.m183711_v188110.vista_me.interfaces.MyOnLongItemClickListener;
import bt.unicamp.ft.m183711_v188110.vista_me.AdapteRecyclerViewProduct;


@SuppressLint("ValidFragment")
public class ProductsFragment extends Fragment  implements MyOnLongItemClickListener, MyOnItemClickListener {

    private RecyclerView mRecyclerView;
    private AdapteRecyclerViewProduct mAdapter;
    private ArrayList<Product> list;
    private View view;
    private FragmentManagerActivity fragmentManagerActivity;
    private BuyItemEventListener buyItemEventListener;



    @SuppressLint("ValidFragment")
    public ProductsFragment(ArrayList<Product> list,FragmentManagerActivity fragmentManagerActivity,BuyItemEventListener buyItemEventListener) {
        this.list = list;
        this.fragmentManagerActivity = fragmentManagerActivity;
        this.buyItemEventListener = buyItemEventListener;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_products, container, false);

        mRecyclerView = view.findViewById(R.id.mRecyclerView);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        mAdapter = new AdapteRecyclerViewProduct(list,R.layout.adapter_layout);
        mAdapter.setMyOnLongItemClickListener(this);
        mAdapter.setMyOnItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        return view;
    }

    @Override
    public void MyOnLongItemClick(View view, Object obj, int pos) {
        Product p = (Product) obj;
        if(buyItemEventListener != null){
            buyItemEventListener.BuyItem(p);
        }
    }

    @Override
    public void MyOnItemClick(View view, Object obj, int pos) {
        Product p = (Product) obj;
        ProductItemFragment productItemFragment = new ProductItemFragment(p, fragmentManagerActivity,buyItemEventListener);
        fragmentManagerActivity.OpenFragment(productItemFragment,"ProductInfo",true);
    }
}
