package ua.grupo7.pi.prettycloud.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import ua.grupo7.pi.prettycloud.R;
import ua.grupo7.pi.prettycloud.viewModels.RegisterViewModel;

public class RegisterFragment extends Fragment {

    private RegisterViewModel mViewModel;
    private String errorMessage = null;

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.register_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(RegisterViewModel.class);
        // TODO: Use the ViewModel
    }

    public boolean validUsername(String username){
        //if webservice.getUserByName(username) == null
        if (username.length()<3){
            errorMessage = getString(R.string.short_username_error);
        }
        return true;
    }

    public boolean validPassword(String password){
        if (password.length()<8){
            errorMessage = getString(R.string.short_password_error);
        }
        return true;
    }

}
