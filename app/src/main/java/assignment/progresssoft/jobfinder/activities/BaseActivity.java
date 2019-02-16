package assignment.progresssoft.jobfinder.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import assignment.progresssoft.jobfinder.Dialogs.LoadingDialog;
import assignment.progresssoft.jobfinder.Dialogs.NoJobsDialog;
import assignment.progresssoft.jobfinder.R;


public class BaseActivity extends AppCompatActivity {
    private static final String TAG = "BaseActivity";

    private LoadingDialog loadingDialog;
    private NoJobsDialog noJobsDialog;




    protected Context mContext;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        mContext = BaseActivity.this;

    }

    public void showLoading() {
        loadingDialog = new LoadingDialog(this);
        loadingDialog.show();
    }

    public void hideNoData() {
        if (noJobsDialog != null && noJobsDialog.isShowing())
            noJobsDialog.dismiss();
    }



    public void showNoData() {
        noJobsDialog = new NoJobsDialog(this);
        noJobsDialog.show();
    }

    public void hideLoading() {
        if (loadingDialog != null && loadingDialog.isShowing())
            loadingDialog.dismiss();
    }


    public static boolean isValidInput(EditText editText) {
        boolean status = false;
        if (editText.getText().toString().trim().length() > 0) {
            status = true;
        }
        return status;
    }


}
