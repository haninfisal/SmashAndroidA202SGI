package com.example.smash.bookingApp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class listAdapter extends RecyclerView.Adapter<listAdapter.ThisViewHolder> {

    String data1[], data2[], data3[], data4[], data5[];
    int images[];
    Context mContext;

    public listAdapter(Context ct, String str1[], String str2[], String str3[],
                       String str4[], String str5[], int img[])
    {

        mContext = ct;
        data1 = str1;
        data2 = str2;
        data3 = str3;
        data4 = str4;
        data5 = str5;
        images = img;

    }

    @NonNull
    @Override
    public ThisViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.the_row, parent, false);
        return new ThisViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThisViewHolder holder, int position) {
        holder.courtName.setText(data1[position]);
        holder.locationTxt.setText(data2[position]);
        holder.logoView.setImageResource(images[position]);

       // Show data on secondActivity
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, secondActivity.class);
                intent.putExtra("data1", data1[position]);
                intent.putExtra("data2", data2[position]);
                intent.putExtra("logoView", images[position]);
                intent.putExtra("data3", data3[position]);
                intent.putExtra("data4", data4[position]);
                intent.putExtra("data5", data5[position]);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return images.length;
    }

    public class ThisViewHolder extends RecyclerView.ViewHolder {

        TextView courtName, locationTxt, contactNum, operationHour, pricePerHour;
        ImageView logoView;
        ConstraintLayout mainLayout;

        public ThisViewHolder(@NonNull View itemView) {
            super(itemView);

            courtName = itemView.findViewById(R.id.court);
            locationTxt = itemView.findViewById(R.id.location);
            logoView = itemView.findViewById(R.id.logo);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            contactNum = itemView.findViewById(R.id.contact);
            operationHour = itemView.findViewById(R.id.opHour);
            pricePerHour = itemView.findViewById(R.id.price);


        }
    }
}
