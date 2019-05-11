package ua.grupo7.pi.prettycloud.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import ua.grupo7.pi.prettycloud.LoginViewModel;
import ua.grupo7.pi.prettycloud.R;
import ua.grupo7.pi.prettycloud.SharedValues;

public class Login extends Fragment {

    private LoginViewModel mViewModel;
    private EditText usernameText;
    private EditText passwordText;
    private String errorMessage;
    private SharedValues sharedValues;
    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPreferences;

    public static Login newInstance() {
        return new Login();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);
        usernameText = view.findViewById(R.id.user_name_text);
        passwordText = view.findViewById(R.id.user_password_text);
        editor = sharedPreferences.edit();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
    }

    private boolean validateUsername(){
        if(usernameText.getText().length()<3){
            errorMessage = getResources().getString(R.string.short_username_error);
            editor.putString(sharedValues.ErrorMessage,errorMessage);
            return false;
        }
        return true;
    }

    private boolean validatePassword(){
        if (passwordText.getText().length()<8){
            errorMessage = getResources().getString( R.string.short_password_error);
            editor.putString(sharedValues.ErrorMessage,errorMessage);
            return false;
        }
        return true;

    }
    private void validateLogin(){
        assert validateUsername();
        assert validatePassword();
        //if (api.login(usernameText,passwordText));
        editor.putBoolean(sharedValues.isLoggedIn,true);
    }
}
