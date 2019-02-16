package assignment.progresssoft.jobfinder.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;


import assignment.progresssoft.jobfinder.R;
import assignment.progresssoft.jobfinder.models.jobsApiModel;

public class jobsApiAdapter extends RecyclerView.Adapter<jobsApiAdapter.MyViewHolder> {

    private List<jobsApiModel> data;
    private Context context;

    public jobsApiAdapter(List<jobsApiModel> data, Context context) {
        this.data = data;
        this.context = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_job, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        final jobsApiModel model=data.get(i);




            Glide.with(context)
                    .load(R.drawable.defualt_logo)
                    .into(myViewHolder.logoimage);

        myViewHolder.tvPostDate.setText(model.getStartDate().toString());
        myViewHolder.tvLocation.setText(model.getLocations().get(0)+"");
        myViewHolder.tvJobTitle.setText(model.getPositionTitle());
        myViewHolder.tvCompanyName.setText(model.getOrganizationName());
        myViewHolder.parentlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String URL=model.getUrl();

                if (!URL.startsWith("http://") && !URL.startsWith("https://"))
                    URL = "http://" + URL;
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
                context.startActivity(browserIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();}


    public class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView logoimage;
        private TextView tvJobTitle,tvCompanyName,tvLocation,tvPostDate;
        private CardView parentlayout;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            logoimage=itemView.findViewById(R.id.company_logo);
            tvJobTitle=itemView.findViewById(R.id.job_title);
            tvCompanyName=itemView.findViewById(R.id.company_name);
            tvLocation=itemView.findViewById(R.id.tvlocation);
            tvPostDate=itemView.findViewById(R.id.post_date);
            parentlayout=itemView.findViewById(R.id.parent_layout);
        }
    }

}