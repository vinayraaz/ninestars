package com.ninestartsvinaytask.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ninestartsvinaytask.MainActivity;
import com.ninestartsvinaytask.R;
import com.ninestartsvinaytask.SecondActivity;
import com.ninestartsvinaytask.model.PhotoModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PhotoListAdapter extends RecyclerView.Adapter<PhotoListAdapter.PhotoViewHolder> {
    Context context;
    List<PhotoModel> photoModels = new ArrayList<>();
    String thumbailUrl,title;

    public PhotoListAdapter(Context context, List<PhotoModel> photoModels) {
        this.context = context;
        this.photoModels = photoModels;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.photo_list_details, viewGroup, false);
        return new PhotoListAdapter.PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, final int position) {
        Picasso
                .with(context)
                .load(photoModels.get(position).getThumbnailUrl())
                .into(holder.Image);
        holder.Title.setText(photoModels.get(position).getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                thumbailUrl = photoModels.get(position).getThumbnailUrl();
                title = photoModels.get(position).getTitle();
                Intent intent = new Intent(context,SecondActivity.class);
                intent.putExtra("THUMBNAILURL",thumbailUrl);
                intent.putExtra("TITLE",title);
                context.startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return photoModels.size();
    }

    public class PhotoViewHolder extends RecyclerView.ViewHolder {
        public TextView Title;
        public ImageView Image;

        public PhotoViewHolder(@NonNull View itemView) {
            super(itemView);
            Title = (TextView) itemView.findViewById(R.id.textview);
            Image = (ImageView) itemView.findViewById(R.id.imagevie);
        }
    }
}
