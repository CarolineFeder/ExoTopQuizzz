package com.example.rmicaroline.topquizzz.Model;

import java.util.Comparator;

/**
 * Created by Rémi&Caroline on 25/05/2018.
 */

public class User implements Comparable <User>{

    private String mFirstName;
    private int mScore;


    public String getFirstName() {
        return mFirstName;
    }

    public int getScore (){return mScore;}

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public void setScore(int score) {
        mScore = score;
    }

    public String toString (){
        String str = mFirstName + " avec un score de: " + mScore;
        return str;
    }


    @Override
    public int compareTo(User compareUser) {
        int compareScore = ((User) compareUser).getScore();
        return compareScore - mScore;
    }


    public static Comparator<User> FirstnameComparator = new Comparator <User> (){

        public int compare (User user1, User user2){
            String user1Name = user1.getFirstName().toUpperCase();
            String user2Name = user2.getFirstName().toUpperCase();

            return user1Name.compareTo(user2Name);
        }
    };


}