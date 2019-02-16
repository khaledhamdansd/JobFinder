package assignment.progresssoft.jobfinder.activities.MainActivityMVP;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.RelativeLayout;

import java.util.List;

import assignment.progresssoft.jobfinder.activities.BaseActivity;
import assignment.progresssoft.jobfinder.R;
import assignment.progresssoft.jobfinder.adapter.GitHubproviderAdapter;
import assignment.progresssoft.jobfinder.adapter.jobsApiAdapter;
import assignment.progresssoft.jobfinder.models.githubJobs;
import assignment.progresssoft.jobfinder.models.jobsApiModel;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements Main_Contract.MainView {
    private static final String TAG = "MainActivity";

    @BindView(R.id.content_recycler)
    RecyclerView recyclerView;

    @BindView(R.id.relativeMain)
    RelativeLayout relativeLayout;

    private Main_Contract.presenter presenter;





    private String providerName,placeName,position;
    private Double longitude,latitude;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        getIntentData();

        presenter = new MainActivityPresenter(getApplicationContext(),this, new GetJopsData());
        presenter.requestDataFromServer(providerName,placeName,position,latitude,longitude);


    }

    private void getIntentData() {
        providerName=getIntent().getStringExtra("provider");
        position=getIntent().getStringExtra("position");
        longitude=getIntent().getDoubleExtra("longitude",0);
        latitude=getIntent().getDoubleExtra("latitude",0);
        placeName=getIntent().getStringExtra("plaeName");
    }


    @Override
    public void showLoadingBar() {
        showLoading();
    }

    @Override
    public void hideLodaingBar() {
    hideLoading();
    }

    @Override
    public void showNoDataDialog() {
        showNoData();
    }

    @Override
    public void hideNodDataDialog() {
     hideNoData();
    }


    @Override
    public void setDataToViewsGitHub(List<githubJobs> jobsList) {
            GitHubproviderAdapter adapter=new GitHubproviderAdapter(jobsList,getApplicationContext());
            recyclerView.setAdapter(adapter);



    }

    @Override
    public void setDataToViewsJobApi(List<jobsApiModel> jobsList) {

            jobsApiAdapter adapter=new jobsApiAdapter(jobsList,getApplicationContext());
            recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Snackbar.make(recyclerView, R.string.error_message, Snackbar.LENGTH_SHORT)
                .show();
    }
}
