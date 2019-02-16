package assignment.progresssoft.jobfinder.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.util.ArrayList;
import java.util.Arrays;

import assignment.progresssoft.jobfinder.R;
import assignment.progresssoft.jobfinder.activities.BaseActivity;
import assignment.progresssoft.jobfinder.activities.MainActivityMVP.MainActivity;
import assignment.progresssoft.jobfinder.adapter.ProviderAdapter;
import assignment.progresssoft.jobfinder.models.ProviderItem;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FilterActivity extends BaseActivity {
    private static final String TAG = "FilterActivity";

    @BindView(R.id.provider_spinner)
    Spinner providerSpinner;

    @BindView(R.id.btnNext)
    Button next;

    @BindView(R.id.edtJobPosition)
    EditText edtJopPosition;

    private String providerName,placeName,position;
    private LatLng mplaceLocation;

    private ArrayList<ProviderItem>  mProviderItems;
    private ProviderAdapter mProviderAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        ButterKnife.bind(this);
        initSpinner();
        initAuotCompleteFragment();


    }//onCreate


    @OnClick(R.id.btnNext)
    void onClick(){
        if (isValidInput(edtJopPosition)&&mplaceLocation!=null)
        {
            position=edtJopPosition.getText().toString();
            Intent intent=new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra("position",position);
            intent.putExtra("provider",providerName);
            intent.putExtra("plaeName",placeName);
            intent.putExtra("latitude",mplaceLocation.latitude);
            intent.putExtra("longitude",mplaceLocation.longitude);
            startActivity(intent);
        }else
            {
            Toast.makeText(this, "make sure to fill all data", Toast.LENGTH_SHORT).show();
            return;
        }
    }//onClick

    private void initSpinner() {
        mProviderItems=new ArrayList<>();
        mProviderItems.add(new ProviderItem("GitHub",R.drawable.bateeeeeg));
        mProviderItems.add(new ProviderItem("Jobs Api",R.drawable.logo_jobapi));
        mProviderAdapter=new ProviderAdapter(getApplicationContext(),mProviderItems);
        providerSpinner.setAdapter(mProviderAdapter);

        providerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ProviderItem providerItem= (ProviderItem) parent.getItemAtPosition(position);
                providerName=providerItem.getProviderName();
                Log.d(TAG, "onItemSelected:### "+providerName);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }//initSpiner


    // Initialize the AutocompleteSupportFragment.
    private void initAuotCompleteFragment() {
        /**
         * Initialize Places. For simplicity, the API key is hard-coded. In a production
         * environment we recommend using a secure mechanism to manage API keys.
         */
        if (!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), "AIzaSyCSEvl-ntlEiOGO6kwHh2gVP3Za_YXBPL4");
        }


        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment_jobLocation);

        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME,Place.Field.LAT_LNG));

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {

                Log.i(TAG, "Place:###### " + place.getName() + ", " + place.getId());
                placeName=place.getName();
                mplaceLocation=place.getLatLng();

            }


            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.i(TAG, "An error occurred:#### " + status);
            }
        });
    }

}//class
