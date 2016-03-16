package com.doctorconference.managment.topictab;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.doctorconference.managment.GetSetData;
import com.doctorconference.managment.R;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link GetSetData} and makes a call to the
 * specified {@link }.
 * TODO: Replace the implementation with code for your data type.
 */
public class TopicRViewAdapter extends RecyclerView.Adapter<TopicRViewAdapter.ViewHolder> {

    private final List<GetSetData> mValues;
    private final TopicsRecordFragment.OnListFragmentInteractionListener mListener;

    public TopicRViewAdapter(List<GetSetData> items, TopicsRecordFragment.OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mTopicTittle.setText(mValues.get(position).getmTopicTitle());
        holder.mTopicDetails.setText(mValues.get(position).getmTopicDetails());

        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public TextView mTopicTittle, mTopicDetails;
        public GetSetData mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTopicTittle = (TextView) view.findViewById(R.id.content);
            mTopicDetails = (TextView) view.findViewById(R.id.details);
        }
    }
}
