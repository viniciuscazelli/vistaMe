package bt.unicamp.ft.m183711_v188110.vista_me.fragments;


import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import bt.unicamp.ft.m183711_v188110.vista_me.R;
import bt.unicamp.ft.m183711_v188110.vista_me.interfaces.FragmentManagerActivity;
import bt.unicamp.ft.m183711_v188110.vista_me.interfaces.WaitLoadFragment;


@SuppressLint("ValidFragment")
public class LoadingFragment extends android.support.v4.app.Fragment {

    private android.support.v4.app.Fragment thisFragment = this;
    private View fracment;
    private WaitLoadFragment waitLoadFragment;
    private FragmentManagerActivity fragmentManagerActivity;


    public LoadingFragment(WaitLoadFragment waitLoadFragment,FragmentManagerActivity fragmentManagerActivity) {

        this.waitLoadFragment = waitLoadFragment;
        this.fragmentManagerActivity = fragmentManagerActivity;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fracment = inflater.inflate(R.layout.fragment_loading, container, false);

        waitLoadFragment.execute();
        fragmentManagerActivity.RemoveFragment( thisFragment);


        return fracment;
    }




}
