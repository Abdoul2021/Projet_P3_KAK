package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FavoriteNeighbourFragment extends Fragment {

        @BindView(R.id.favorite_neighbour_fragment)
        RecyclerView mFavoriteNeighbourFragment;

        private NeighbourApiService mApiService;
        private List<Neighbour> mNeighbours;

    public FavoriteNeighbourFragment(){ }

        public static FavoriteNeighbourFragment newInstance() {
            FavoriteNeighbourFragment mFavoriteNeighbourFragment = new FavoriteNeighbourFragment();
            return mFavoriteNeighbourFragment;
        }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApiService = DI.getNeighbourApiService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.favorite_neighbour_fragment, container, false);
        ButterKnife.bind(this, view);
        defineRecyclerView();
        Context context = view.getContext();
        mFavoriteNeighbourFragment = (RecyclerView) view;
        mFavoriteNeighbourFragment.setLayoutManager(new LinearLayoutManager(context));
        mFavoriteNeighbourFragment.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mFavoriteNeighbourFragment.setAdapter(new MyFavRecyclerViewAdapter(mNeighbours , getContext()));
        ;
        return view;
    }

    private void defineRecyclerView(){
        this.mNeighbours = new ArrayList<>();
        this.mFavoriteNeighbourFragment.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    /**
     * Init the List of neighbours
     * refer to NeighbourFragment
     */
    private void initList() {
        mNeighbours = mApiService.getNeighboursFavorites();
        mFavoriteNeighbourFragment.setAdapter(new MyFavRecyclerViewAdapter(mNeighbours, getContext()));
    }

    @Override
    public void onResume() {
        super.onResume();
        initList();
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    /**
     * Fired if the user clicks on a delete button
     * @param event
     */
    @Subscribe
    public void onDeleteNeighbour(DeleteNeighbourEvent event) {
        if (event.fragment == 1) {
            mApiService.deleteNeighbourFavorites(event.neighbour);
            initList();
        }
    }
}


