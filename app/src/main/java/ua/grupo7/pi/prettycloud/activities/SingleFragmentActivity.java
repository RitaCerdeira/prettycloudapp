package ua.grupo7.pi.prettycloud.activities;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import ua.grupo7.pi.prettycloud.R;

@RestrictTo(RestrictTo.Scope.TESTS)
public class SingleFragmentActivity extends AppCompatActivity {

    private  Fragment fragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout content = new FrameLayout(this);
        content.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        content.setId(R.id.fragment_test);
        setContentView(content);
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_test, fragment, "TEST")
                .commitAllowingStateLoss();
    }

    public void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_test, fragment).commit();
    }

    public Fragment getFragment(){
        return fragment;
    }
}
