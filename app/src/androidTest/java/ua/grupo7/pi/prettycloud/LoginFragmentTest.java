package ua.grupo7.pi.prettycloud;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.fragment.app.Fragment;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.jetbrains.annotations.NotNull;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ua.grupo7.pi.prettycloud.activities.SingleFragmentActivity;
import ua.grupo7.pi.prettycloud.fragments.LoginFragment;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;

@RunWith(AndroidJUnit4.class)
public class LoginFragmentTest {
    @NotNull
    private final ActivityTestRule testActivityRule = new ActivityTestRule(SingleFragmentActivity.class, true, true);

    @NotNull
    public final ActivityTestRule getTestActivityRule() {
        return this.testActivityRule;
    }

    private String username = "jose@hotmail.com";
    private String password = "jose_pedro";

    private SharedPreferences sharedPreferences;

    @Rule
    @NotNull
    public final ActivityTestRule rule() {
        return this.testActivityRule;
    }

    @Before
    public final void setUp() {
        ((SingleFragmentActivity)this.rule().getActivity()).setFragment((Fragment) LoginFragment.newInstance());
        sharedPreferences = testActivityRule.getActivity().getSharedPreferences("PrettyCloudSalons", Context.MODE_PRIVATE);
    }

    @Test
    public final void failSignUpTest() throws InterruptedException {
        Espresso.onView(ViewMatchers.withId(R.id.user_name_login_text)).
                perform(ViewActions.typeText("short")).
                perform(ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.user_password_login_text)).
                perform(click()).
                perform(ViewActions.typeText("short"),closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.login_button)).perform(ViewActions.click());
        Thread.sleep(1000);
        assert !sharedPreferences.getBoolean("IS_LOOGED_IN",false);
    }


    @Test
    public final void signUpTest() throws InterruptedException {
        Espresso.onView(ViewMatchers.withId(R.id.user_name_login_text)).
                perform(ViewActions.typeText(username)).
                perform(ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.user_password_login_text)).
                     perform(click()).
                     perform(ViewActions.typeText(password),closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.login_button)).perform(click());
        assert sharedPreferences.getBoolean("IS_LOOGED_IN",false);
        Thread.sleep(1000);
    }


}
