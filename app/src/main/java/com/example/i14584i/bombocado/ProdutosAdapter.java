package com.example.i14584i.bombocado;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.i14584i.bombocado.Produto;
import com.example.i14584i.bombocado.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Victor Gabriel on 09/08/2017.
 */

public class ProdutosAdapter extends BaseAdapter {
    List<Produto> produtos;
    Activity activity;
    Picasso picasso;
    public ProdutosAdapter(Activity activity, List<Produto> produtos)
    {
        this.activity = activity;
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
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Produto produto = produtos.get(position);
        ViewHolder holder = null;
        if(view == null)
        {
            holder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(activity);
            view = inflater.inflate(R.layout.linha_produto,null);
            holder.txt_produto = (TextView) view.findViewById(R.id.txt_produto);
            holder.txt_preco = (TextView) view.findViewById(R.id.txt_preco);
            holder.txt_img = (ImageView) view.findViewById(R.id.txt_img);
            view.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) view.getTag();
        }
        holder.txt_produto.setText(produto.getNome_prod());
        holder.txt_preco.setText(produto.getPreco_prod());
        picasso.load("http://bombocado.esy.es/imgs/"+produto.getImg()).resize(600,600).onlyScaleDown().placeholder(R.drawable.cupcake_loading).into(holder.txt_img);

        return view;
    }
}
