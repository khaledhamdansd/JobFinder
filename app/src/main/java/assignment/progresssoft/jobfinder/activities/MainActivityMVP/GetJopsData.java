package assignment.progresssoft.jobfinder.activities.MainActivityMVP;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;


import assignment.progresssoft.jobfinder.Constants;
import assignment.progresssoft.jobfinder.models.githubJobs;
import assignment.progresssoft.jobfinder.models.jobsApiModel;
import assignment.progresssoft.jobfinder.retrofit.APIInterface;
import assignment.progresssoft.jobfinder.retrofit.ApiUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetJopsData implements Main_Contract.GetJobsDataImpl {
    private static final String TAG = "GetJopsData";
    private ArrayList<githubJobs> githubJobsArrayList;
    private ArrayList<jobsApiModel> jobsApiModelArrayList;


    @Override
    public void getGethubJops(final OnFinishedListenerGithub onFinishedListener, String position, double latitude, double longitude) {

        APIInterface mApiInterface= ApiUtils.getAPIService(Constants.GITHUB_BASE_URL);
        Call<List<githubJobs>> call=mApiInterface.getjopslist(position,latitude,longitude);
        call.enqueue(new Callback<List<githubJobs>>() {
            @Override
            public void onResponse(Call<List<githubJobs>> call, Response<List<githubJobs>> response) {
                if (response.isSuccessful()){
                    githubJobsArrayList =new ArrayList<>();
                    githubJobsArrayList.addAll(response.body());
                    onFinishedListener.onGithubFinished(githubJobsArrayList);
                }
            }
            @Override
            public void onFailure(Call<List<githubJobs>> call, Throwable t) {
                Log.d(TAG, "onFailure: #####"+t);
                onFinishedListener.onGithubFailure(t);

            }
        });
    }

    @Override
    public void getJobsApiJops(final OnFinishedListenerJobsApi onFinishedListener, String position, String placeName) {


        APIInterface mApiInterface = ApiUtils.getAPIService(Constants.JOBS_BASE_URL);
        Call<List<jobsApiModel>> call=mApiInterface.getjobsapiproject("https://jobs.search.gov/jobs/search.json?query="+position+"+jobs+in+"+placeName+"");
        call.enqueue(new Callback<List<jobsApiModel>>() {
            @Override
            public void onResponse(Call<List<jobsApiModel>> call, Response<List<jobsApiModel>> response) {
                if (response.isSuccessful()){
                    jobsApiModelArrayList=new ArrayList<>();
                    jobsApiModelArrayList.addAll(response.body());
                    onFinishedListener.onJobsApiFinished(jobsApiModelArrayList);


                }
            }

            @Override
            public void onFailure(Call<List<jobsApiModel>> call, Throwable t) {
                onFinishedListener.onJobsApiFailure(t);

            }
        });

    }


}
