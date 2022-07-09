package sg.edu.np.mad.practical2;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    ArrayList<User> usersList;
    public RecyclerAdapter(ArrayList<User> usersList){
        this.usersList = usersList;
    }

    @Override
    public int getItemViewType(int position) {
        if(usersList.get(position).name.endsWith("7")){
            return 1;
        }
        else{
            return 0;
        }
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == 1){
            view  = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row_large, parent, false);
        }
        else{
            view  = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row, parent, false);
        }
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder recyclerViewHolder, int position) {
        User u = usersList.get(position);
        recyclerViewHolder.name.setText(u.name);
        recyclerViewHolder.desc.setText(u.description);
        recyclerViewHolder.profPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(recyclerViewHolder.profPic.getContext());
                alertDialogBuilder.setTitle("Profile");
                alertDialogBuilder.setMessage(u.name);
                alertDialogBuilder.setCancelable(true);
                alertDialogBuilder.setPositiveButton("View", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int id) {
                        Intent listact = new Intent(recyclerViewHolder.profPic.getContext(), MainActivity.class);
                        listact.putExtra("urFollowed", u.followed);
                        recyclerViewHolder.profPic.getContext().startActivity(listact);
                    }
                });

                alertDialogBuilder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int id) {

                    }
                });
                AlertDialog alert = alertDialogBuilder.create();
                alert.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }
}
