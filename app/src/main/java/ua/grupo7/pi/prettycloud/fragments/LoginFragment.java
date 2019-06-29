package ua.grupo7.pi.prettycloud.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;

import ua.grupo7.pi.prettycloud.models.Client;
import ua.grupo7.pi.prettycloud.viewModels.LoginViewModel;
import ua.grupo7.pi.prettycloud.R;
import ua.grupo7.pi.prettycloud.SharedValues;
import ua.grupo7.pi.prettycloud.communication.Messages;

public class LoginFragment extends Fragment {

    private LoginViewModel mViewModel;
    private EditText usernameText;
    private EditText passwordText;
    private String errorMessage;
    private SharedValues sharedValues;
    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPreferences;
    private Button loginButton;
    private Messages messagingComunication;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);
        usernameText = view.findViewById(R.id.user_name_login_text);
        passwordText = view.findViewById(R.id.user_password_login_text);
        loginButton = view.findViewById(R.id.login_button);
        messagingComunication = new Messages();
        loginButton.setOnClickListener(loginButtonClick());
        sharedValues = new SharedValues();
        sharedPreferences = getActivity().getSharedPreferences(getString(R.string.shared_preferences_key), Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
    }

    private void validateUsername(){
        if(usernameText.getText().length()<3){
            errorMessage = getResources().getString(R.string.short_username_error);
            editor.putString(sharedValues.ErrorMessage,errorMessage).apply();
            messagingComunication.alertMessage(getActivity(),sharedPreferences.getString(sharedValues.ErrorMessage,"Some error... "));
        }
    }

    private void validatePassword(){
        if (passwordText.getText().length()<8){
            errorMessage = getResources().getString( R.string.short_password_error);
            editor.putString(sharedValues.ErrorMessage,errorMessage).apply();
            messagingComunication.alertMessage(getActivity(),sharedPreferences.getString(sharedValues.ErrorMessage,"Some error.."));
        }

    }
    private void validateLogin(){
        validateUsername();
        validatePassword();
        mViewModel.getClients().observe(getViewLifecycleOwner(), new Observer<List<Client>>() {
            @Override
            public void onChanged(List<Client> clients) {
                for (Client client:clients){
                    Log.d("Client ", client.getEmail());
                    if (client.getEmail().equals(usernameText.getText().toString())&&
                            client.getPassword().equals(passwordText.getText().toString())){
                        sharedPreferences.edit().putBoolean(sharedValues.isLoggedIn,true).commit();
                        sharedPreferences.edit().putLong(sharedValues.CLIENT_ID,client.getId()).commit();
                    }
                }
            }
        });
        //boolean log = mViewModel.authenticate(usernameText.getText().toString(),passwordText.getText().toString());
        //sharedPreferences.edit().putBoolean(sharedValues.isLoggedIn,log).commit();
    }
        //mViewModel.authentication(usernameText.toString(),passwordText.toString());
        //if (api.login(usernameText,passwordText));

    private View.OnClickListener loginButtonClick(){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateLogin();
                if(sharedPreferences.getBoolean(sharedValues.isLoggedIn,false) == true) {
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    Fragment fragment = new SalonsFragment();
                    fragmentTransaction.replace(R.id.fragment_container, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            }
        };
    }


}
