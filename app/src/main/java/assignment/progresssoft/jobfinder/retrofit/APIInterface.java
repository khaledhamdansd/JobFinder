package assignment.progresssoft.jobfinder.retrofit;


import java.util.List;

import assignment.progresssoft.jobfinder.models.githubJobs;
import assignment.progresssoft.jobfinder.models.jobsApiModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface APIInterface {

    @GET("positions.json")
    Call<List<githubJobs>> getjopslist(@Query("description") String description, @Query("lat") Double lat, @Query("long") Double longitude);

    @GET
    Call<List<jobsApiModel>> getjobsapiproject(@Url String url);
}