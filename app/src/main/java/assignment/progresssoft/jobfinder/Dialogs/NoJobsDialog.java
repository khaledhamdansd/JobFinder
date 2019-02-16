package assignment.progresssoft.jobfinder.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.Window;

import assignment.progresssoft.jobfinder.R;
import assignment.progresssoft.jobfinder.activities.FilterActivity;

public class NoJobsDialog extends Dialog {

    public NoJobsDialog(@Nullable Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_no_jobs);
        setCancelable(false);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dismiss();
                Intent intent=new Intent(getContext(), FilterActivity.class);
                getContext().startActivity(intent);
                getContext();
            }
        },2000);

    }
}
