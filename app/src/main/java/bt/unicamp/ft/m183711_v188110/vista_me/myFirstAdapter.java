package bt.unicamp.ft.m183711_v188110.vista_me;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import bt.unicamp.ft.m183711_v188110.vista_me.interfaces.MyOnItemClickListener;
import bt.unicamp.ft.m183711_v188110.vista_me.interfaces.MyOnLongItemClickListener;

public class myFirstAdapter extends RecyclerView.Adapter {

    private ArrayList<Product> products;
    private MyOnItemClickListener myOnItemClickListener;
    private MyOnLongItemClickListener myOnLongItemClickListener;

    public myFirstAdapter(ArrayList<Product> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder  onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.adapter_layout, parent, false);
        return new MyFirstViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        ((MyFirstViewHolder)viewHolder).bind(products.get(i));

        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (myOnLongItemClickListener != null) {
                    myOnLongItemClickListener.MyOnLongItemClick(view,products.get(i),i);
                }
                return true;
            }
        });

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (myOnItemClickListener != null) {
                    myOnItemClickListener.MyOnItemClick(view,products.get(i),i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public void setMyOnItemClickListener(MyOnItemClickListener myOnItemClickListener) {
        this.myOnItemClickListener = myOnItemClickListener;
    }

    public void setMyOnLongItemClickListener(MyOnLongItemClickListener myOnLongItemClickListener) {
        this.myOnLongItemClickListener = myOnLongItemClickListener;
    }


    public class MyFirstViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView primaryTextView;
        private TextView dividerNumberTextView;
        private TextView dividerValueTextView;
        private TextView valorTextView;
        private Product product;
        private LinearLayout content;

        public MyFirstViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            primaryTextView = itemView.findViewById(R.id.primaryTextView);
            dividerNumberTextView = itemView.findViewById(R.id.dividerNumberTextView);
            dividerValueTextView = itemView.findViewById(R.id.dividerValueTextView);
            valorTextView = itemView.findViewById(R.id.valorTextView);
            content = itemView.findViewById(R.id.Item);

        }

        public void bind(final Product product) {
            this.product = product;
            imageView.setImageResource(product.getFoto());
            imageView.buildLayer();
            primaryTextView.setText(product.getNome());
            dividerNumberTextView.setText(product.getDividerValue()+"x");
            dividerValueTextView.setText(" R$ "+String.format( "%.2f", product.getDividerValue() ));
            valorTextView.setText(" R$ "+String.format( "%.2f", product.getValor() ));
        }



    }
}

