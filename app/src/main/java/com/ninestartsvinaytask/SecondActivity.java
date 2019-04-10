package com.ninestartsvinaytask;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class SecondActivity extends AppCompatActivity{
    String photoTitle="",thumbnailUrl="";
    TextView tvTitle;
    ImageView ivThumbnailUrl;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);
        tvTitle = (TextView)findViewById(R.id.textview);
        ivThumbnailUrl = (ImageView) findViewById(R.id.imageview);

        Bundle b= getIntent().getExtras();
        if (b!= null){
            photoTitle = b.getString("TITLE");
            thumbnailUrl = b.getString("THUMBNAILURL");

            Picasso
                    .with(SecondActivity.this)
                    .load(thumbnailUrl)
                    .into(ivThumbnailUrl);
            tvTitle.setText(photoTitle);

        }
    }
}
