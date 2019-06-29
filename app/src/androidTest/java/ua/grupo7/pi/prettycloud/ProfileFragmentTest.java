package ua.grupo7.pi.prettycloud;

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
import ua.grupo7.pi.prettycloud.fragments.ProfileFragment;

@RunWith(AndroidJUnit4.class)
public class ProfileFragmentTest {
    @NotNull
    private final ActivityTestRule testActivityRule = new ActivityTestRule(SingleFragmentActivity.class, true, true);

    @NotNull
    public final ActivityTestRule getTestActivityRule() {
        return this.testActivityRule;
    }

    @Rule
    @NotNull
    public final ActivityTestRule rule() {
        return this.testActivityRule;
    }

    @Before
    public final void setUp() {
        ((SingleFragmentActivity)this.rule().getActivity()).setFragment((Fragment) ProfileFragment.newInstance());
    }

    @Test
    public final void LabelsAreDisplayed() {
        Espresso.onView(ViewMatchers.withId(R.id.user_email_profile_textView)).check(ViewAssertions.matches(ViewMatchers.withText("Email")));
        Espresso.onView(ViewMatchers.withId(R.id.user_firstName_profile_textView)).check(ViewAssertions.matches(ViewMatchers.withText("Name")));
        Espresso.onView(ViewMatchers.withId(R.id.user_lastName_profile_textView)).check(ViewAssertions.matches(ViewMatchers.withText("Last Name")));
        Espresso.onView(ViewMatchers.withId(R.id.user_phone_number_profile_textView)).check(ViewAssertions.matches(ViewMatchers.withText("Phone Number")));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
