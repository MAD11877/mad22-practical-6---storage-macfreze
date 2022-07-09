package sg.edu.np.mad.practical2;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Random;

public class DBHandler extends SQLiteOpenHelper {
    private String tableName = "User";
    private String nameColumn = "NAME";
    private String descColumn = "DESCRIPTION";
    private String idColumn = "ID";
    private String followedColumn = "FOLLOWED";
    DBHandler(Context c){
        super(c,"users.db",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        String query = "Create Table User (NAME TEXT, DESCRIPTION TEXT, " +
                "ID INTEGER PRIMARY KEY, FOLLOWED BOOLEAN)";
        
        db.execSQL(query);
        ArrayList<User> userList = initRandomUser();
        for (User user : userList){
            ContentValues values = new ContentValues();
            values.put(nameColumn, user.name);
            values.put(descColumn, user.description);
            values.put(idColumn, user.id);
            values.put(followedColumn, user.followed);
            db.insert(tableName, null, values);
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1){
        db.execSQL("DROP TABLE IF EXISTS USER");
        db.close();
    }
    public ArrayList<User> getUsers(){
        String query = "Select * FROM USER";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        ArrayList<User> usersList = new ArrayList<>();
        while(cursor.moveToNext()){
            User u = new User();
            u.name = cursor.getString(0);
            u.description = cursor.getString(1);
            u.id = cursor.getInt(2);
            u.followed = fGetBoolbyInt(cursor.getInt(3));
            usersList.add(u);

        }
        cursor.close();
        db.close();
        return usersList;
    }
    public User getSpecificUSer(Integer id){
        String query = "SELECT * FROM USER WHERE ID = " + "\"" + id + "\"";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        User u = new User();

        if(cursor.moveToFirst()){
            Boolean bool = fGetBoolbyInt(cursor.getInt(3));
            u.name = cursor.getString(0);
            u.description = cursor.getString(1);
            u.id = cursor.getInt(2);
            u.followed = bool;
        }
        return u;
    }
    public void  updateUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        Integer iFollowed = fGetIntbyBool(user.followed);
        String query = "UPDATE USER SET FOLLOWED = \"" + iFollowed + "\" WHERE ID = \"" + user.id + "\"";
        db.execSQL(query);
        db.close();
    }
    private ArrayList<User> initRandomUser(){
        ArrayList<User> usersList = new ArrayList<>();
        Integer randomId = 0;
        while (usersList.size() <20){
            Random rand = new Random();
            String randName = "Name " + Integer.toString(rand.nextInt());
            String randDescription = "Description " + Integer.toString(rand.nextInt());
            Boolean randFollowed = rand.nextBoolean();
            User usr = new User(randName, randDescription, randomId, randFollowed);
            usersList.add(usr);
            randomId += 1;
        }
        return usersList;
    }
    public int fGetIntbyBool(Boolean b){
        if (b == true){
            return 1;
        }
        else{
            return 0;
        }
    }
    public boolean fGetBoolbyInt(Integer i){
        if (i == 1){
            return true;
        }
        else {
            return false;
        }
    }

}
