package com.unc.hbs.productos;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.widget.CheckBox;

import com.unc.hbs.productos.Model.Producto;


/**
 * Created by hbs on 1/10/16.
 */
import java.util.List;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CustomArrayAdapter extends ArrayAdapter<Producto> implements
        View.OnClickListener {
    private TextView resultadoTodal;
    private LayoutInflater layoutInflater;

    public CustomArrayAdapter(Context context, List<Producto> objects, View v) {
        super(context, 0, objects);
        layoutInflater = LayoutInflater.from(context);
        resultadoTodal= (TextView) v;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // holder pattern
        Holder holder = null;

        if (convertView == null) {
            holder = new Holder();

            convertView = layoutInflater.inflate(R.layout.content_productos, parent, false);
            holder.setTextViewTitle((TextView) convertView
                    .findViewById(R.id.textView));
            holder.setTextViewPrice((TextView) convertView
                    .findViewById(R.id.textPrecio));
            holder.setCheckBox((CheckBox) convertView
                    .findViewById(R.id.checkbox));
            holder.setImage((ImageView)convertView.findViewById(R.id.imageView));
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        final Producto row = getItem(position);
        holder.getTextViewTitle().setText(row.getNombre());
        holder.getTextViewSubtitle().setText("S/. "+String.valueOf(row.getPrecio()));
        holder.getCheckBox().setTag(position);
        holder.getCheckBox().setChecked(row.isChecked());
        holder.getCheckBox().setOnClickListener(this);
        holder.getImageView().setImageResource(row.getIdimage());
        changeBackground(getContext(), holder.getCheckBox());

        return convertView;
    }


    @Override
    public void onClick(View v) {

        CheckBox checkBox = (CheckBox) v;
        int position = (Integer) v.getTag();
        getItem(position).setChecked(checkBox.isChecked());

        changeBackground(CustomArrayAdapter.this.getContext(), checkBox);
        String msg = "Agregado";
        if(!checkBox.isChecked())
        {
            msg="Quitado";
        }
        Toast.makeText(this.getContext(), msg, Toast.LENGTH_SHORT).show();
        resultadoTodal.setText("Son: S/. "+String.valueOf(MainActivity.calcularTotal()));
    }


    /**
     * Set the background of a row based on the value of its checkbox value.
     * Checkbox has its own style.
     */
    @SuppressWarnings("deprecation")
    private void changeBackground(Context context, CheckBox checkBox) {
        View row = (View) checkBox.getParent();
        Drawable drawable;
        if (checkBox.isChecked()) {
            drawable = context.getResources().getDrawable(
                    R.drawable.listview_selector_checked);
        } else {
            drawable = context.getResources().getDrawable(
                    R.drawable.listview_selector);
        }
        row.setBackgroundDrawable(drawable);
    }


    static class Holder
    {
        TextView textViewTitle;
        TextView textViewSubtitle;
        CheckBox checkBox;
        ImageView image;

        public TextView getTextViewTitle()
        {
            return textViewTitle;
        }

        public void setTextViewTitle(TextView textViewTitle)
        {
            this.textViewTitle = textViewTitle;
        }

        public TextView getTextViewSubtitle()
        {
            return textViewSubtitle;
        }

        public void setTextViewPrice(TextView textViewSubtitle)
        {
            this.textViewSubtitle = textViewSubtitle;
        }
        public CheckBox getCheckBox()
        {
            return checkBox;
        }
        public void setCheckBox(CheckBox checkBox)
        {
            this.checkBox = checkBox;
        }

        public ImageView getImageView()
        {
            return image;
        }
        public void setImage(ImageView i){
            this.image=i;
        }

    }
}