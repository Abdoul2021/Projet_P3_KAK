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
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

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
    private NeighbourApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour_detail);
        ButterKnife.bind(this);
        int position = getIntent().getIntExtra("position", 0);

        mApiService = DI.getNeighbourApiService();
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

        //Return button
        mReturnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { onBackPressed();
                Toast.makeText(getApplicationContext(), "Tu retournes dans la liste des voisins", Toast.LENGTH_SHORT).show();
            }
            });

        //Favorite Button (style)
        if ( ! mApiService.getNeighboursFavorites().contains(mNeighbour)) {
            mFavoritesButton.setImageResource(R.drawable.ic_baseline_star_border_24);
        } else mFavoritesButton.setImageResource(R.drawable.ic_baseline_star_24);

        //Favorite Button (action)
        mFavoritesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( ! mApiService.getNeighboursFavorites().contains(mNeighbour)) {
                    mApiService.addNeighbourFavorites(mNeighbour);
                }
                if ( mApiService.getNeighboursFavorites().contains(mNeighbour))
                {    mFavoritesButton.setImageResource(R.drawable.ic_baseline_star_24);
                    Toast.makeText(getApplicationContext(), mNeighbour.getName() + " est maintenant dans ta liste de favoris !", Toast.LENGTH_SHORT).show();
                }
                else {
                    mFavoritesButton.setImageResource(R.drawable.ic_baseline_star_border_24);
                    Toast.makeText(getApplicationContext(), mNeighbour.getName() + " n'est plus dans ta liste de favoris !", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }}