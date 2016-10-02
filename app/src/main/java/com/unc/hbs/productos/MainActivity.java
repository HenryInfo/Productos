package com.unc.hbs.productos;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.unc.hbs.productos.Model.DBAdapter;
import com.unc.hbs.productos.Model.Producto;
import com.unc.hbs.productos.Model.Usuario;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<Producto> lista= new ArrayList<>();
    private ListView listView;
    private ViewGroup layout;
    private TextView resultado;
    public Context c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lista.clear();
        Producto p;
        c=getBaseContext();

        //Lista de productos
        p= new Producto(1, "Poteito", 20.55, R.drawable.papa, false);
        lista.add(p);
        p= new Producto(2, "Camote", 30.05, R.drawable.camote, false);
        lista.add(p);
        p= new Producto(3, "Chocolate", 50.1, R.drawable.chocolate,false);
        lista.add(p);
        p= new Producto(4, "Arroz", 90.1, R.drawable.arroz,false);
        lista.add(p);
        p= new Producto(5, "Pika", 900, R.drawable.pika,false);
        lista.add(p);
        setContentView(R.layout.activity_main);
        resultado= (TextView) findViewById(R.id.resultado);
        listView=(ListView)findViewById(R.id.content);
        listView.setAdapter(new CustomArrayAdapter(this, lista, resultado));


    }

    public void calcular(View v)
    {
        resultado.setText("Son: S/. "+String.valueOf(calcularTotal()));
    }
    public void ninguno(View v)
    {

        for(int i=0;i<lista.size();i++)
        {
            if(lista.get(i).isChecked())
            {
                lista.get(i).setChecked(false);
            }
        }
        listView.setAdapter(new CustomArrayAdapter(this, lista, resultado));
        resultado.setText("Son: S/. "+String.valueOf(calcularTotal()));
    }
    public void todos(View v)
    {
        for(int i=0;i<lista.size();i++)
        {
            if(!lista.get(i).isChecked())
            {
                lista.get(i).setChecked(true);
            }
        }
        listView.setAdapter(new CustomArrayAdapter(this, lista, resultado));
        resultado.setText("Son: S/. "+String.valueOf(calcularTotal()));    }
    public static double calcularTotal()
    {

        double cantidad=0;
        for (Producto p :
                lista) {
            if(p.isChecked())
            {
                cantidad=cantidad+p.getPrecio();
            }
        }
        return cantidad;
    }

}

