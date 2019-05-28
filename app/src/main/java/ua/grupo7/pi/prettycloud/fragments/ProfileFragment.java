package ua.grupo7.pi.prettycloud.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import ua.grupo7.pi.prettycloud.models.Client;
import ua.grupo7.pi.prettycloud.viewModels.ProfileViewModel;
import ua.grupo7.pi.prettycloud.R;

public class ProfileFragment extends Fragment {

    private ProfileViewModel mViewModel;
    private EditText client_user_name;
    private EditText client_user_last_name;
    private EditText client_user_email;
    private EditText client_phone_number;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment, container, false);
        client_user_name = view.findViewById(R.id.user_firstName_profile_editText);
        client_user_last_name = view.findViewById(R.id.user_lastName_profile_editText);
        client_user_email = view.findViewById(R.id.user_email_profile_editText);
        client_phone_number = view.findViewById(R.id.user_phone_number_profile_editText);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);

        Client client = mViewModel.getTestClient();
        client_user_name.setText(client.getFirstName());
        client_user_last_name.setText(client.getLastName());
        client_phone_number.setText(client.getPhoneNumber());
        client_user_email.setText(client.getEmail());
    }

}
