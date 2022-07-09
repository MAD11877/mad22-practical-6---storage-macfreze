package sg.edu.np.mad.practical2;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewHolder extends RecyclerView.ViewHolder{
    TextView name;
    TextView desc;
    ImageView profPic;
    public RecyclerViewHolder(View item){
        super(item);
        name = item.findViewById(R.id.userName);
        desc = item.findViewById(R.id.userDesc);
        profPic = item.findViewById(R.id.profPicSmall);
    }
}
