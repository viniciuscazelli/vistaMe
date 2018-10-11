package bt.unicamp.ft.m183711_v188110.vista_me;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import bt.unicamp.ft.m183711_v188110.vista_me.entities.Order;
import bt.unicamp.ft.m183711_v188110.vista_me.entities.Product;
import bt.unicamp.ft.m183711_v188110.vista_me.interfaces.MyOnItemClickListener;
import bt.unicamp.ft.m183711_v188110.vista_me.interfaces.MyOnLongItemClickListener;

public class AdapteRecyclerViewOrders extends RecyclerView.Adapter {

    private ArrayList<Order> orders;
    private MyOnItemClickListener myOnItemClickListener;
    private MyOnLongItemClickListener myOnLongItemClickListener;


    public AdapteRecyclerViewOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder  onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.adapter_layout_orders, parent, false);
        return new MyFirstViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        ((MyFirstViewHolder)viewHolder).bind(orders.get(i));

        viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (myOnLongItemClickListener != null) {
                    myOnLongItemClickListener.MyOnLongItemClick(view,orders.get(i),i);
                }
                return true;
            }
        });

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (myOnItemClickListener != null) {
                    myOnItemClickListener.MyOnItemClick(view,orders.get(i),i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public void setMyOnItemClickListener(MyOnItemClickListener myOnItemClickListener) {
        this.myOnItemClickListener = myOnItemClickListener;
    }

    public void setMyOnLongItemClickListener(MyOnLongItemClickListener myOnLongItemClickListener) {
        this.myOnLongItemClickListener = myOnLongItemClickListener;
    }


    public class MyFirstViewHolder extends RecyclerView.ViewHolder {

        private TextView pedido;
        private TextView data;
        private TextView totalItens;
        private TextView total;
        private TextView status;
        private Order order;


        public MyFirstViewHolder(@NonNull View itemView) {
            super(itemView);

            pedido = itemView.findViewById(R.id.pedido);
            data = itemView.findViewById(R.id.data);
            totalItens = itemView.findViewById(R.id.totalItens);
            total = itemView.findViewById(R.id.total);
            status = itemView.findViewById(R.id.status);


        }

        public void bind(final Order order) {
            Locale localeBR = new Locale( "pt", "BR" );
            NumberFormat dinheiroBR = NumberFormat.getCurrencyInstance(localeBR);

            this.order = order;
            pedido.setText(order.getNumber());
            data.setText(new SimpleDateFormat("dd/MM/yyyy, Ka").format(order.getDate()));
            totalItens.setText(order.getItens().size() + " Itens");
            total.setText(order.getDivider()+"x "+dinheiroBR.format(order.Total()/order.getDivider()) +" ("+dinheiroBR.format(order.Total())+")");
            status.setText(order.getStatus());

        }



    }
}

