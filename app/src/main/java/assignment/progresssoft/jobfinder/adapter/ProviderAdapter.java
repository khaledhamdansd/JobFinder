package assignment.progresssoft.jobfinder.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import assignment.progresssoft.jobfinder.R;
import assignment.progresssoft.jobfinder.models.ProviderItem;

public class ProviderAdapter extends ArrayAdapter<ProviderItem> {

    private ArrayList<ProviderItem> items;
    private Context context;

    public ProviderAdapter(Context context, ArrayList<ProviderItem> items) {
        super(context,0,items);
        this.context=context;
        this.items = items;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @Nullable ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Nullable
    @Override
    public View getView(int position, @Nullable View convertView, @Nullable ViewGroup parent) {
        return initView(position, convertView, parent);
    }


    private View initView(int position,View convertView,ViewGroup parent){
        if (convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.item_spinner,parent,false);
        }
        ImageView imageViewLogo=convertView.findViewById(R.id.spinner_logo);
        TextView textViewName=convertView.findViewById(R.id.text_view_name);

        ProviderItem providerItem=items.get(position);
        Glide.with(context)
                .load(providerItem.getProviderImage())
                .into(imageViewLogo);
        textViewName.setText(providerItem.getProviderName());

        return convertView;

    }
}
