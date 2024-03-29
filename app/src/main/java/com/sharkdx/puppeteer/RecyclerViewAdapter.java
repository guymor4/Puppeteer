package com.sharkdx.puppeteer;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.DataObjectHolder>
{
	private static String LOG_TAG = "RecyclerViewAdapter";
	private ArrayList<Agent> mDataset;
	private static MyClickListener myClickListener;

	public static class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener
	{
		TextView hostname;
		TextView name;
		TextView version;

		public DataObjectHolder(View itemView)
		{
			super(itemView);
			hostname = itemView.findViewById(R.id.agent_host);
			name = itemView.findViewById(R.id.agent_name);
			version = itemView.findViewById(R.id.agent_version);

			Log.i(LOG_TAG, "Adding Listener");
			itemView.setOnClickListener(this);
		}

		@Override
		public void onClick(View v)
		{
			if (myClickListener != null)
				myClickListener.onItemClick(getAdapterPosition(), v);
		}
	}

	public void setOnItemClickListener(MyClickListener myClickListener)
	{
		this.myClickListener = myClickListener;
	}

	public RecyclerViewAdapter(ArrayList<Agent> myDataset)
	{
		mDataset = myDataset;
	}

	@Override
	public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType)
	{
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_agent, parent, false);

		DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
		return dataObjectHolder;
	}

	@Override
	public void onBindViewHolder(DataObjectHolder holder, int position)
	{
		holder.hostname.setText(mDataset.get(position).getFullHostname());
		holder.name.setText(mDataset.get(position).getName());
		holder.version.setText(mDataset.get(position).getVersion().toString());
	}

	public void addItem(Agent dataObj, int index)
	{
		mDataset.add(index, dataObj);
		notifyItemInserted(index);
	}

	public void deleteItem(int index)
	{
		mDataset.remove(index);
		notifyItemRemoved(index);
	}

	public Agent getItem(int index)
	{
		return mDataset.get(index);
	}

	@Override
	public int getItemCount()
	{
		return mDataset.size();
	}

	public interface MyClickListener
	{
		public void onItemClick(int position, View v);
	}
}