package edu.dlsu.mobapde.labdatabasekorean;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.dlsu.mobapde.labdatabasephone.CursorRecyclerViewAdapter;
import edu.dlsu.mobapde.labdatabasephone.Phone;
import edu.dlsu.mobapde.labdatabasephone.PhonesAdapter;
import edu.dlsu.mobapde.labdatabasephone.R;

/**
 * Created by G301 on 11/9/2017.
 */

public class PhonesAdapterSkeleton
        extends CursorRecyclerViewAdapter<PhonesAdapter.PhoneViewHolder> {

    private OnItemClickListener onItemClickListener;

    public PhonesAdapter(Context context, Cursor cursor) {
        super(context, cursor);
    }

    @Override
    public void onBindViewHolder(PhoneViewHolder viewHolder, Cursor cursor) {
        // cursor is already pointed at the current position
        long id = cursor.getLong(cursor.getColumnIndex(Phone.COLUMN_ID));
        String man = cursor.getString(cursor.getColumnIndex(Phone.COLUMN_MANUFACTURER));

        viewHolder.tvId.setText(id+"");
        viewHolder.tvManufacturer.setText(man);

        // set the database id to the viewholder's itemView (the "whole row" view)
        viewHolder.itemView.setTag(id);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pass id to caller
                onItemClickListener.onItemClick((Long) v.getTag());
            }
        });
    }

    @Override
    public PhoneViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_phone, parent, false);
        return new PhoneViewHolder(v);
    }

    public class PhoneViewHolder extends RecyclerView.ViewHolder{
        TextView tvId;
        TextView tvManufacturer;
        public PhoneViewHolder(View itemView) {
            super(itemView);
            tvId = (TextView) itemView.findViewById(R.id.tv_id);
            tvManufacturer = (TextView) itemView.findViewById(R.id.tv_manufacturer);
        }
    }

    // interface to be implemented to know if an item has been clicked or not
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        public void onItemClick(long id);
    }
}