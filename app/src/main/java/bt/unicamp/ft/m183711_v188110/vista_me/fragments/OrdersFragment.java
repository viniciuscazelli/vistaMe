package bt.unicamp.ft.m183711_v188110.vista_me.fragments;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bt.unicamp.ft.m183711_v188110.vista_me.AdapteRecyclerViewOrders;
import bt.unicamp.ft.m183711_v188110.vista_me.AdapteRecyclerViewProduct;
import bt.unicamp.ft.m183711_v188110.vista_me.Login;
import bt.unicamp.ft.m183711_v188110.vista_me.R;
import bt.unicamp.ft.m183711_v188110.vista_me.interfaces.CartNotificChangeListProducts;
import bt.unicamp.ft.m183711_v188110.vista_me.interfaces.FragmentManagerActivity;
import bt.unicamp.ft.m183711_v188110.vista_me.interfaces.MyOnItemClickListener;
import bt.unicamp.ft.m183711_v188110.vista_me.interfaces.MyOnLongItemClickListener;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class OrdersFragment extends Fragment implements MyOnItemClickListener{

    private View fragment;
    private FragmentManagerActivity fragmentManagerActivity;
    private Login login;
    private RecyclerView mRecyclerView;
    private AdapteRecyclerViewOrders mAdapter;

    @SuppressLint("ValidFragment")
    public OrdersFragment(FragmentManagerActivity fragmentManagerActivity, Login login) {
        this.fragmentManagerActivity = fragmentManagerActivity;
        this.login = login;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragment =  inflater.inflate(R.layout.fragment_orders, container, false);

        RecyclerView mRecyclerView = fragment.findViewById(R.id.mRecyclerViewOrders);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAdapter = new AdapteRecyclerViewOrders(login.getUser().getOrders());

        mAdapter.setMyOnItemClickListener(this);

        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        return fragment;
    }

    @Override
    public void MyOnItemClick(View view, Object obj, int pos) {

    }
}
