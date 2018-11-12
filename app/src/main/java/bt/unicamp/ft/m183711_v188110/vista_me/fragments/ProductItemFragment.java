package bt.unicamp.ft.m183711_v188110.vista_me.fragments;


import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import bt.unicamp.ft.m183711_v188110.vista_me.entities.Product;
import bt.unicamp.ft.m183711_v188110.vista_me.R;
import bt.unicamp.ft.m183711_v188110.vista_me.interfaces.BuyItemEventListener;
import bt.unicamp.ft.m183711_v188110.vista_me.interfaces.FragmentManagerActivity;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class ProductItemFragment extends Fragment {

    private Product product;
    private View fragment;
    private ImageView imageView;
    private TextView primaryTextView;
    private TextView dividerNumberTextView;
    private TextView dividerValueTextView;
    private TextView valorTextView;
    private TextView DescriptionProductItem;
    private FragmentManagerActivity fragmentManagerActivity;
    private BuyItemEventListener buyItemEventListener;
    private Button buttonBuyProductItem;


    @SuppressLint("ValidFragment")
    public ProductItemFragment(Product product,FragmentManagerActivity fragmentManagerActivity,BuyItemEventListener buyItemEventListener) {
       this.product = product;
       this.fragmentManagerActivity = fragmentManagerActivity;
       this.buyItemEventListener = buyItemEventListener;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragment = inflater.inflate(R.layout.fragment_product_item, container, false);
        imageView = fragment.findViewById(R.id.imageViewProductItem);
        primaryTextView = fragment.findViewById(R.id.primaryTextViewProductItem);
        dividerNumberTextView = fragment.findViewById(R.id.dividerNumberTextViewProductItem);
        dividerValueTextView = fragment.findViewById(R.id.dividerValueTextViewProductItem);
        valorTextView = fragment.findViewById(R.id.valorTextViewProductItem);
        DescriptionProductItem = fragment.findViewById(R.id.DescriptionProductItem);
        buttonBuyProductItem = fragment.findViewById(R.id.buttonBuyProductItem);

        buttonBuyProductItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(buyItemEventListener != null){
                    buyItemEventListener.BuyItem(product);
                }
            }
        });

        LoadData();
        return fragment;
    }

    private void LoadData(){
        imageView.setImageBitmap(product.getFoto());
        imageView.buildLayer();
        primaryTextView.setText(product.getNome());
        dividerNumberTextView.setText(product.getDividerValue()+"x");
        dividerValueTextView.setText(" R$ "+String.format( "%.2f", product.getDividerValue() ));
        valorTextView.setText(" R$ "+String.format( "%.2f", product.getValor() ));
        DescriptionProductItem.setText(product.getDescricao());
    }




}
