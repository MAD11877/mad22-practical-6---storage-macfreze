package sg.edu.np.mad.practical2;

import java.io.Serializable;

public class User implements Serializable {
    public String name;
    public String description;
    public int id;

    public boolean followed;

    public User(){};
    public User(String username, String userDesc, int userId, boolean userFollowed){
        name = username;
        description = userDesc;
        id = userId;
        followed = userFollowed;
    }
}

