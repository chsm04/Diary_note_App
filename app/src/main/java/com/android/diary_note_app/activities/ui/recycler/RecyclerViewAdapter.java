package com.android.diary_note_app.activities.ui.recycler;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.diary_note_app.R;

import java.io.IOException;
import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{

    Context context;
    ArrayList<Item> list;

    public RecyclerViewAdapter(Context context, ArrayList<Item> list) {
        super();
        this.context = context;
        this.list = list;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Bitmap bitmap = setImg(list.get(position).image);

        holder.id.setText(list.get(position).Id);
        holder.date.setText(list.get(position).date);
        holder.emoji.setText(list.get(position).emoji);
        holder.name.setText(list.get(position).name);
        holder.content.setText(list.get(position).content);
        holder.image.setImageBitmap(bitmap);
    }

    private Bitmap setImg(String path){

        Uri uri = Uri.parse(path);

        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
        } catch (IOException e) {
            e.getStackTrace();
        }
        return bitmap;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recylcerview_row, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new OpenEditAct(view);
            }
        });

        return new MyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView id;
        TextView date;
        TextView emoji;
        TextView name;
        TextView content;
        ImageView image;


        public MyViewHolder(View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.frag1_id);
            image = itemView.findViewById(R.id.recyclerview_row_image);
            date = itemView.findViewById(R.id.recyclerview_row_date);
            emoji = itemView.findViewById(R.id.recyclerview_row_emoji);
            name = itemView.findViewById(R.id.recyclerview_row_name);
            content = itemView.findViewById(R.id.recyclerview_row_content);
        }
    }
}
