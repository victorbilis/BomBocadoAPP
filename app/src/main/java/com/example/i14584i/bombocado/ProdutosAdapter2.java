package com.example.i14584i.bombocado;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Victor Gabriel on 09/08/2017.
 */

public class ProdutosAdapter2 extends BaseAdapter {
    List<Produto> produtos;
    Activity activity;
    Picasso picasso;
    TextView preco_total;
    double preco;
    String nome = "";
    public ProdutosAdapter2(Activity activity, List<Produto> produtos,TextView preco_total,double preco,String nome)
    {
        this.preco_total = preco_total;
        this.nome = nome;
        this.activity = activity;
        this.preco = preco;
        this.produtos = produtos;
        Picasso.Builder builder = new Picasso.Builder(activity);
        picasso = builder.build();
    }
    @Override
    public int getCount() {
        return produtos.size();
    }

    @Override
    public Object getItem(int position) {
        return produtos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    class ViewHolder{
        TextView txt_produto;
        TextView txt_preco;
        ImageView txt_img;
        Button btn_reservar;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final Produto produto = produtos.get(position);
        ViewHolder holder = null;
        if(view == null)
        {
            holder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(activity);
            view = inflater.inflate(R.layout.linha_reserva,null);
            holder.txt_produto = (TextView) view.findViewById(R.id.txt_produto);
            holder.txt_preco = (TextView) view.findViewById(R.id.txt_preco);
            holder.txt_img = (ImageView) view.findViewById(R.id.txt_img);
            holder.btn_reservar = (Button) view.findViewById(R.id.btn_reservar);
            view.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) view.getTag();
        }
        holder.txt_produto.setText(produto.getNome_prod());
        holder.txt_preco.setText(produto.getPreco_prod());
        holder.btn_reservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder dialog = new AlertDialog.Builder(v.getContext());
                dialog.setTitle("BomBocado - Reservar");;
                dialog.setMessage("\n");
                dialog.setIcon(R.drawable.logo);
                Context context = v.getContext();
                LinearLayout layout = new LinearLayout(context);
                layout.setOrientation(LinearLayout.VERTICAL);

                final EditText titleBox = new EditText(context);

                titleBox.setHint("Quantidade");

                layout.addView(titleBox);

                dialog.setPositiveButton("RESERVAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        int qtd = Integer.parseInt(titleBox.getText().toString());

                        new getProdutos3(activity,nome,"SELECT * FROM produtos WHERE nome_prod='" + produto.getNome_prod() + "'",qtd).execute("");

                    }
                });

                dialog.setNegativeButton("VOLTAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                });
                dialog.setView(layout);
                dialog.show();

               new getPrecoProd2("SELECT * FROM produtos WHERE nome_prod='" + produto.getNome_prod() + "'",activity,preco_total,preco).execute("");

            }
        });
        picasso.load("http://bombocado.esy.es/imgs/"+produto.getImg()).resize(600,600).onlyScaleDown().placeholder(R.drawable.cupcake_loading).into(holder.txt_img);

        return view;
    }
}
