package ua.grupo7.pi.prettycloud;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;

import org.hamcrest.Matcher;

import ua.grupo7.pi.prettycloud.models.Salon;

public class MyViewAction {

    public static ViewAction clickChildViewWithId(final int id, Context context, Salon salon) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return null;
            }

            @Override
            public String getDescription() {
                return "Click on a child view with specified id.";
            }

            @Override
            public void perform(UiController uiController, View view) {
                View v = view.findViewById(id);
                v.performClick();
                Toast.makeText(context,"Clicked salon " + salon.getName(),Toast.LENGTH_LONG).show();
            }
        };
    }

}