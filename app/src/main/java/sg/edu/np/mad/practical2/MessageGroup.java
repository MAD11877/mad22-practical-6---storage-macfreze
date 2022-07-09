package sg.edu.np.mad.practical2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MessageGroup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_group);

        Button btngrp1 = findViewById(R.id.grp1btn);
        Button btngrp2 = findViewById(R.id.grp2btn);

        btngrp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayFragment(new MessageGroup1());
            }
        });

        btngrp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayFragment(new MessageGroup2());
            }
        });
    }

    public void displayFragment(Fragment fragment){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.Frame, fragment).commit();
    }
}