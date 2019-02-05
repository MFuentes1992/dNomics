package com.example.markf.dnomics;

import android.support.v7.widget.RecyclerView;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by markf on 04/02/2019.
 */

public class MyAdapter  extends RecyclerView.Adapter<FlowerViewHolder> {

    private Context mContext;
    private List<ReportTO> reportList;

    MyAdapter(Context mContext, List<ReportTO> mFlowerList) {
        this.mContext = mContext;
        this.reportList = mFlowerList;
    }

    @Override
    public FlowerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.draft_row_recycleview, parent, false);
        return new FlowerViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(final FlowerViewHolder holder, int position) {
        holder.lblDraftReportName.setText(reportList.get(position).getReportName());
        holder.lblDraftReportNumber.setText(reportList.get(position).getReportNumber());
        /*holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(mContext, DetailActivity.class);
                mIntent.putExtra("Title", mFlowerList.get(holder.getAdapterPosition()).getFlowerName());
                mIntent.putExtra("Description", mFlowerList.get(holder.getAdapterPosition()).getFlowerDescription());
                mIntent.putExtra("Image", mFlowerList.get(holder.getAdapterPosition()).getFlowerImage());
                mContext.startActivity(mIntent);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return reportList.size();
    }


}
class FlowerViewHolder extends RecyclerView.ViewHolder {

    TextView lblDraftReportName;
    TextView lblDraftReportNumber;
    CardView mCardView;

    FlowerViewHolder(View itemView) {
        super(itemView);

        lblDraftReportName = itemView.findViewById(R.id.lblDraftReportName);
        lblDraftReportNumber = itemView.findViewById(R.id.lblDraftReportNumber);
        mCardView = itemView.findViewById(R.id.cardview);
    }
}
