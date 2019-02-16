package assignment.progresssoft.jobfinder.activities.MainActivityMVP;


import java.util.List;


import assignment.progresssoft.jobfinder.models.githubJobs;
import assignment.progresssoft.jobfinder.models.jobsApiModel;

/*
    * This interface consists the three inner interfaces named presenter, MainView, GetNoticeIntractor.
* */
public interface Main_Contract {

    /*
    * Presenter;
    * This interface callbacks will be called when the user interact with the view
    * onActivityOpend,On RecyclerItemClicked
    * when the view is destroyed onDestroy()
    * and requesting the data from the server requestDataFromServer() for the first time when the activity is created.
    * */
    interface presenter{

        void onDestroy();


        void onActivitycreate();

        void requestDataFromServer(String providerType, String placeName, String position, double latitude, double longitude);


    }


    /*
    * The interface callback will be called when the user need to show/hide the progressbar showPregress()/hidePregress()
    * set data to the recycler view setDataToViews(…)
    * and lastly to show the error if the network response is failed onResponseFailure(…).
    * */
    interface MainView {

        void showLoadingBar();

        void hideLodaingBar();

        void showNoDataDialog();

        void hideNodDataDialog();

        void setDataToViewsGitHub(List<githubJobs> jobsList);

        void setDataToViewsJobApi(List<jobsApiModel> jobsList);

        void onResponseFailure(Throwable throwable);

    }


    /**
     * Intractors are classes built for fetching data from your database, web services, or any other data source.
     * (Model)
     **/
    interface GetJobsDataImpl {

      public   interface OnFinishedListenerGithub {
            void onGithubFinished(List<githubJobs> jobsArrayList);
            void onGithubFailure(Throwable t);
        }

        public   interface OnFinishedListenerJobsApi {
            void onJobsApiFinished(List<jobsApiModel> jobsArrayList);
            void onJobsApiFailure(Throwable t);
        }

        void getGethubJops(OnFinishedListenerGithub onFinishedListenerGithub, String position, double latitude, double longitude);
        void getJobsApiJops(OnFinishedListenerJobsApi onFinishedListenerJobsApi, String position, String placeName);
    }

}// Interface Ends
