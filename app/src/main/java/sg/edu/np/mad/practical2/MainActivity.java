package sg.edu.np.mad.practical2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView userId;
    TextView userDesc;
    User u;
    Button followButton;
    Button messageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int pos = getIntent().getIntExtra("position", 0);

        Intent receivingEnd = getIntent();
        Integer userID = receivingEnd.getIntExtra("userID",-100);
        DBHandler db = new DBHandler(this);
        User user = db.getSpecificUSer(userID);

        u = ListActivity.usersList.get(pos);
        userId = findViewById(R.id.idTxt);
        userDesc = findViewById(R.id.descTxt);
        followButton = findViewById(R.id.followButton);
        messageButton = findViewById(R.id.messageButton);

        userId.setText(u.name);
        userDesc.setText(u.description);

        follow(u, followButton);
        followButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if (u.followed == false){
                    u.followed = true;
                    Toast.makeText(getApplicationContext(), "Followed", Toast.LENGTH_SHORT).show();

                } else {
                    u.followed = false;
                    Toast.makeText(getApplicationContext(), "Unfollowed", Toast.LENGTH_SHORT).show();
                }
                follow(u, followButton);
                db.updateUser(user);
            }
        });

        messageButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(MainActivity.this, MessageGroup.class));
            }
        });
    }

    public void follow(User u, Button btn){
        TextView txt = btn;
        if(u.followed == false){
            txt.setText("Follow");

        }else {
            txt.setText("Unfollow");
        }
    }
}

