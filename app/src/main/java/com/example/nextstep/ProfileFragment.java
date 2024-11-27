package com.example.nextstep;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class ProfileFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);// shows layout of fragment

        //intialize all the buttons in profile fragment
        ImageView backIcon = view.findViewById(R.id.back_icon);
        Button portfolioButton = view.findViewById(R.id.portfolio_button);  // Use Button for portfolio
        Button logoutButton = view.findViewById(R.id.logout_button);

        // wen back button is pressed it returns to home activity
        backIcon.setOnClickListener(v -> {
            if (getActivity() != null) {  // getactivity() checks if the fragment is attached to an activity
                Intent intent = new Intent(getActivity(), HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // lama nrg3 lel activity ay haga by7slha clear fl back stack (any activity that was here before)
                startActivity(intent);
                getActivity().finish(); // Finish the current activity
            }
        });

        // Portfolio Button click listener to go to PortfolioActivity
        portfolioButton.setOnClickListener(v -> {
            if (getActivity() != null) {  // Ensure the fragment is attached to an activity
                Intent intent = new Intent(getActivity(), PortfolioActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Clears the back stack up to HomeActivity
                startActivity(intent);
                getActivity().finish(); // Finish the current activity
            }
        });

        // Logout Button click listener to go to LoginActivity
        logoutButton.setOnClickListener(v -> {
            if (getActivity() != null) {  // Ensure the fragment is attached to an activity
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                getActivity().finish();
            }
        });

        return view;
    }
}
