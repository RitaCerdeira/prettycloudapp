package ua.grupo7.pi.prettycloud.communication;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.tapadoo.alerter.Alerter;

import ua.grupo7.pi.prettycloud.R;

public class Messages {

    public void alertMessage(Activity activity,String message){
        Alerter.create(activity)
                .setTitle(R.string.error)
                .setText(message)
                .setBackgroundColorRes(R.color.alert_default_error_background)
                .enableSwipeToDismiss()
                .setDuration(3000)
                .show();
    }


}
