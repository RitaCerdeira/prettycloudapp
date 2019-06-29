package ua.grupo7.pi.prettycloud;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.espresso.remote.EspressoRemoteMessage;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.ArrayList;

import ua.grupo7.pi.prettycloud.activities.SingleFragmentActivity;
import ua.grupo7.pi.prettycloud.adapters.SalonAdapter;
import ua.grupo7.pi.prettycloud.communication.PrettyCloudWebService;
import ua.grupo7.pi.prettycloud.communication.RestApi;
import ua.grupo7.pi.prettycloud.config.Config;
import ua.grupo7.pi.prettycloud.fragments.SalonsFragment;
import ua.grupo7.pi.prettycloud.models.Salon;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class SalonFragmentTest {
    @NotNull
    private final ActivityTestRule testActivityRule = new ActivityTestRule(SingleFragmentActivity.class, true, true);

    @NotNull
    public final ActivityTestRule getTestActivityRule() {
        return this.testActivityRule;
    }

    private RestApi restApi = new RestApi(Config.API_URL);

    private PrettyCloudWebService webService;

    @NotNull
    private SalonAdapter salonAdapter;

    @NotNull
    ArrayList<Salon> salonArrayList;

    private Fragment fragment;

    private SharedPreferences sharedPreferences;

    private RecyclerView recyclerView;
    @Rule
    @NotNull
    public final ActivityTestRule rule() {
        return this.testActivityRule;
    }

    @Before
    public final void setUp() {
        sharedPreferences = testActivityRule.getActivity().getSharedPreferences("PrettyCloudSalons", Context.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean("IS_LOOGED_IN",true).commit();
        ((SingleFragmentActivity)this.rule().getActivity()).setFragment((Fragment) SalonsFragment.newInstance());
        fragment = ((SingleFragmentActivity)this.rule().getActivity()).getFragment();
        webService = restApi.getWebservice();
        try {
            salonArrayList = webService.getSalons().execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }
        salonAdapter = new SalonAdapter(this.getTestActivityRule().getActivity().getApplicationContext(), salonArrayList, new SalonAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Salon item) {
                Toast.makeText(testActivityRule.getActivity().getApplicationContext(),
                        "Salon " + item.getName(), Toast.LENGTH_LONG).show();
            }
        }
        );
    }


    @Test
    public void testSample() throws InterruptedException {
        Thread.sleep(2000);
        Log.d("count: ",String.valueOf(getRVcount()));
        if (getRVcount() > 0){
            onView(withId(R.id.salons_recycler_view)).perform(RecyclerViewActions.scrollToPosition(7));
            Thread.sleep(2000);
            onView(withId(R.id.salons_recycler_view)).perform(RecyclerViewActions.scrollToPosition(0));
            Thread.sleep(2000);
            onView(withId(R.id.salons_recycler_view)).perform(
                    RecyclerViewActions.actionOnItemAtPosition(0, MyViewAction.clickChildViewWithId(R.id.salon_image,
                            testActivityRule.getActivity().getApplicationContext(),salonArrayList.get(0))));
            Thread.sleep(2000);
        }

    }

    private int getRVcount(){
        RecyclerView recyclerView = (RecyclerView) testActivityRule.getActivity().findViewById(R.id.salons_recycler_view);
        return recyclerView.getAdapter().getItemCount();
    }

    public static Matcher<RecyclerView.ViewHolder> withTitle(final String title)
    {
        return new BoundedMatcher<RecyclerView.ViewHolder, SalonAdapter.SalonViewHolder>(SalonAdapter.SalonViewHolder.class)
        {
            @Override
            public void describeTo(Description description) {

            }

            @Override
            protected boolean matchesSafely(SalonAdapter.SalonViewHolder item)
            {
                return item.salonTextView.getText().toString().equalsIgnoreCase(title);
            }


        };
    }


}
