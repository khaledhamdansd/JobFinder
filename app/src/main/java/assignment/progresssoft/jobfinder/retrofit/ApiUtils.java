package assignment.progresssoft.jobfinder.retrofit;


public class ApiUtils {

    public static APIInterface getAPIService(String BASE_URL) {
        return RetrofitClient.getClient(BASE_URL).create(APIInterface.class);
    }
}