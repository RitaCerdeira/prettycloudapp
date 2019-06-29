package ua.grupo7.pi.prettycloud;


import android.content.Context;
import android.content.SharedPreferences;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import ua.grupo7.pi.prettycloud.activities.SingleFragmentActivity;
import ua.grupo7.pi.prettycloud.fragments.SalonDetailsFragment;

@RunWith(AndroidJUnit4.class)
public class SalonDetailsFragmentTest {

    @NotNull
    private final ControlledActivityTestRule testActivityRule = new ControlledActivityTestRule(SingleFragmentActivity.class, true, true);

    @NotNull
    public final ActivityTestRule getTestActivityRule() {
        return this.testActivityRule;
    }

    private SharedPreferences sharedPreferences;

    private SharedValues sharedValues = new SharedValues();

    private Context context;

    @NotNull
    private TextView salonNameTextview;

    @Rule
    @NotNull
    public final ControlledActivityTestRule rule() {
        return this.testActivityRule;
    }

    @Before
    public final void setUp() throws InterruptedException {
        sharedPreferences = this.getTestActivityRule().getActivity().getSharedPreferences("PrettyCloudSalons",
                Context.MODE_PRIVATE);
        sharedPreferences.edit().putLong(sharedValues.SALON_ID,3l).commit();
        context =  this.getTestActivityRule().getActivity().getApplicationContext();
        ((SingleFragmentActivity) this.rule().getActivity()).setFragment((Fragment) SalonDetailsFragment.newInstance());
        salonNameTextview = testActivityRule.getActivity().findViewById(R.id.salon_details_name);
    }

    @Test
    public void testSalonDescription() throws InterruptedException {
        Espresso.onView(ViewMatchers.withId(R.id.salon_details_name)).check(ViewAssertions.matches(ViewMatchers.withText("Feel you")));
        Toast.makeText(context,"Salon name is expected",Toast.LENGTH_LONG).show();
        Thread.sleep(2000);
    }


}