package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

//refer to MyNeighbourRecyclerViewAdapter

public class MyFavRecyclerViewAdapter extends RecyclerView.Adapter<MyFavRecyclerViewAdapter.ViewHolder>{

    private final List<Neighbour> mNeighbours;
    private Context mContext;

    public MyFavRecyclerViewAdapter(List<Neighbour> items, Context context) {
        mNeighbours = items;
        mContext = context;
    }

    @Override
    public MyFavRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_neighbour, parent, false);
        return new MyFavRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyFavRecyclerViewAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Neighbour neighbour = mNeighbours.get(position);
        holder.mNeighbourName.setText(neighbour.getName());
        Glide.with(holder.mNeighbourAvatar.getContext())
                .load(neighbour.getAvatarUrl())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.mNeighbourAvatar);

        //Modified compared to MyNeighbourRecyclerViewAdapter (Fragment : 1 instead of 0)
        holder.mDeleteButton.setOnClickListener(v -> EventBus.getDefault().post(new DeleteNeighbourEvent(neighbour, 1)));

        //Access to neighbour' detail
        holder.mFavoriteNeighFrag.setOnClickListener(view -> {
            Intent intent = new Intent(mContext, NeighbourDetailActivity.class);
            intent.putExtra("position", position);
            intent.putExtra("fragment", "neighbourfav");
            intent.putExtra("neighbour", neighbour);
            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return mNeighbours.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_list_fragment_neighbour_layout)
        public ConstraintLayout mFavoriteNeighFrag;
        @BindView(R.id.item_list_avatar)
        public ImageView mNeighbourAvatar;
        @BindView(R.id.item_list_name)
        public TextView mNeighbourName;
        @BindView(R.id.item_list_delete_button)
        public ImageButton mDeleteButton;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }}
