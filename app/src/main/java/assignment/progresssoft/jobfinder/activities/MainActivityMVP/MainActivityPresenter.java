package assignment.progresssoft.jobfinder.activities.MainActivityMVP;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import assignment.progresssoft.jobfinder.models.githubJobs;
import assignment.progresssoft.jobfinder.models.jobsApiModel;


public class MainActivityPresenter implements Main_Contract.presenter,Main_Contract.GetJobsDataImpl.OnFinishedListenerGithub,Main_Contract.GetJobsDataImpl.OnFinishedListenerJobsApi {
    private static final String TAG = "MainActivityPresenter";
    private Main_Contract.MainView mainView;
    private Main_Contract.GetJobsDataImpl getJobsSearchData;
    private Context context;

    public MainActivityPresenter( Context context,Main_Contract.MainView mainView, Main_Contract.GetJobsDataImpl getJobsSearchData) {
        this.mainView = mainView;
        this.getJobsSearchData = getJobsSearchData;
        this.context = context;
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onActivitycreate() {

    }

    @Override
    public void requestDataFromServer(String providerType, String placeName, String position, double latitude, double longitude) {
        switch (providerType){
            case "GitHub":
                if(mainView != null){
                    mainView.showLoadingBar();
                }
                getJobsSearchData.getGethubJops(this,position,latitude,longitude);
                break;
            case "Jobs Api":
                if(mainView != null){
                    mainView.showLoadingBar();
                }
                getJobsSearchData.getJobsApiJops(this,position,placeName);
                break;
            default:
                Toast.makeText(context, "Make Sure To Chose A Provider", Toast.LENGTH_SHORT).show();
                break;
        }
    }



    @Override
    public void onGithubFinished(List<githubJobs> jobsArrayList) {
        if (mainView!=null) {
            if (!(jobsArrayList.size() <= 0)) {
                mainView.setDataToViewsGitHub(jobsArrayList);
                mainView.hideLodaingBar();
            } else {
                mainView.hideLodaingBar();
                mainView.showNoDataDialog();

                Log.d(TAG, "onJobsApiFinished: ############### No Data");
            }
        }
    }

    @Override
    public void onGithubFailure(Throwable t) {
        if(mainView != null){
            mainView.onResponseFailure(t);
            mainView.hideLodaingBar();
        }
    }

    @Override
    public void onJobsApiFinished(List<jobsApiModel> jobsArrayList) {
        Log.d(TAG, "onJobsApiFinished:###############");
        if (mainView!=null){
            if (!(jobsArrayList.size()<=0)){
                mainView.setDataToViewsJobApi(jobsArrayList);
                mainView.hideLodaingBar();
            }else {
                mainView.hideLodaingBar();
                mainView.showNoDataDialog();
                Log.d(TAG, "onJobsApiFinished: ############### No Data");
            }
        }
    }


    @Override
    public void onJobsApiFailure(Throwable t) {
        if(mainView != null){
            mainView.onResponseFailure(t);
            mainView.hideLodaingBar();
        }
    }
}
