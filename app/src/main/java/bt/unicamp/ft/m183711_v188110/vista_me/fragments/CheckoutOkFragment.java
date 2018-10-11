package bt.unicamp.ft.m183711_v188110.vista_me.fragments;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import bt.unicamp.ft.m183711_v188110.vista_me.R;
import bt.unicamp.ft.m183711_v188110.vista_me.interfaces.FragmentManagerActivity;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class CheckoutOkFragment extends Fragment {

    private FragmentManagerActivity fragmentManagerActivity;
    private View fragment;
    private TextView Order;
    private String o;

    @SuppressLint("ValidFragment")
    public CheckoutOkFragment(FragmentManagerActivity fragmentManagerActivity, String Order) {
        this.fragmentManagerActivity = fragmentManagerActivity;
        o = Order;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragment = inflater.inflate(R.layout.fragment_checkout_ok, container, false);

        Order = fragment.findViewById(R.id.Order);

        Order.setText(o);


        Handler handler = new Handler();

        handler.postDelayed(
                new Runnable()
                {
                    @Override
                    public void run()
                    {
                        fragmentManagerActivity.BackToFirstFragment();

                    }
                }, 5000);

        return fragment;
    }

}
