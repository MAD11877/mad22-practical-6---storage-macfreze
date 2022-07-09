package sg.edu.np.mad.practical2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Random;

public class ListActivity extends AppCompatActivity {

    public static ArrayList<User> usersList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        for (int i=0; i<20; i++){
            Random r = new Random();
            String name = "User" + r.nextInt();
            String desc = String.valueOf(Math.abs(r.nextInt()));
            Boolean followed = r.nextBoolean();
            usersList.add(new User(name, desc, i, followed));
        }

        RecyclerView rv = findViewById(R.id.recyclerView);
        LinearLayoutManager layout = new LinearLayoutManager(this);
        RecyclerAdapter adapter = new RecyclerAdapter(usersList);

        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setLayoutManager(layout);
        rv.setAdapter(adapter);
    }
}