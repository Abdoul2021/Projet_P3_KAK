package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.DummyNeighbourGenerator;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NeighbourDetailActivity extends AppCompatActivity {

    @BindView(R.id.neighbour_picture)  ImageView mNeighbourPicture ;
    @BindView(R.id.name_on_picture)  TextView mNameOnPicture ;
    @BindView(R.id.first_name) TextView mFirstName;
    @BindView(R.id.town)  TextView mTown;
    @BindView(R.id.phone_number)  TextView mPhoneNumber;
    @BindView(R.id.facebook) TextView mFacebook;
    @BindView(R.id.description_text)  TextView mDescriptionText ;
    @BindView(R.id.return_button)  ImageView mReturnButton ;
    @BindView(R.id.favorites_button) FloatingActionButton mFavoritesButton;

    private Neighbour mNeighbour;
    private Neighbour neighbour;
    private NeighbourApiService mApiService;
    public String frag;

    List<Neighbour> favNeigh= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_detail);
        ButterKnife.bind(this);
        int position = getIntent().getIntExtra("position", 0);
        frag = getIntent().getStringExtra("fragment");
        neighbour = getIntent().getParcelableExtra("neighbour");
        mApiService = DI.getNeighbourApiService();

        //Details for neighbour and favorite
        if(frag.equals("neighbour")){
            mNeighbour = mApiService.getNeighbourByPosition(position);
            //Details
            mNameOnPicture.setText(mNeighbour.getName());
            mFirstName.setText(mNeighbour.getName());
            mTown.setText(mNeighbour.getAddress());
            mPhoneNumber.setText(mNeighbour.getPhoneNumber());
            mFacebook.setText("www.facebook.fr/" + mNeighbour.getName());
            mDescriptionText.setText(mNeighbour.getAboutMe());
            //Picture
            Glide.with(this).load(mNeighbour.getAvatarUrl()).centerCrop().into(mNeighbourPicture);

        }else if (frag.equals("neighbourfav")){
            mNeighbour = mApiService.getNeighbourFavoriteByPosition(position);
            //Details for favorite
            mNameOnPicture.setText(neighbour.getName());
            mFirstName.setText(neighbour.getName());
            mTown.setText(neighbour.getAddress());
            mPhoneNumber.setText(neighbour.getPhoneNumber());
            mFacebook.setText("www.facebook.fr/" + neighbour.getName());
            mDescriptionText.setText(neighbour.getAboutMe());
            //Picture for favorite
            Glide.with(this).load(neighbour.getAvatarUrl()).centerCrop().into(mNeighbourPicture);
        }

        //Return button
        mReturnButton.setOnClickListener(v -> { onBackPressed();
            Toast.makeText(getApplicationContext(), "Tu quittes la page de " + mNeighbour.getName(), Toast.LENGTH_SHORT).show();
        });

        //Favorite Button (style)
        if ( ! mApiService.getNeighboursFavorites().contains(mNeighbour)) {
            mFavoritesButton.setImageResource(R.drawable.ic_baseline_star_border_24);
        } else mFavoritesButton.setImageResource(R.drawable.ic_baseline_star_24);

        //Favorite Button (action)
        mFavoritesButton.setOnClickListener(view -> {
            if ( ! mApiService.getNeighboursFavorites().contains(mNeighbour)) {
                mApiService.addNeighbourFavorites(mNeighbour);

            }
            if ( mApiService.getNeighboursFavorites().contains(mNeighbour))
            {    mFavoritesButton.setImageResource(R.drawable.ic_baseline_star_24);
                Toast.makeText(getApplicationContext(), mNeighbour.getName() + " est maintenant dans la liste des favoris !", Toast.LENGTH_SHORT).show();
            }
            else {
                mFavoritesButton.setImageResource(R.drawable.ic_baseline_star_border_24);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

    }

}