package com.example.rmicaroline.topquizzz.Contoller;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.rmicaroline.topquizzz.Model.User;
import com.example.rmicaroline.topquizzz.R;

import java.util.ArrayList;
import java.util.Collections;

import static com.example.rmicaroline.topquizzz.Contoller.MainActivity.KEY_FIRSTNAME;
import static com.example.rmicaroline.topquizzz.Contoller.MainActivity.KEY_SCORE;
import static java.lang.System.out;

public class HistoryActivity extends AppCompatActivity {


    private TextView mGreetingText;
    private TextView mPlayer1Text;
    private TextView mPlayer2Text;
    private TextView mPlayer3Text;
    private TextView mPlayer4Text;
    private TextView mPlayer5Text;
    private Button mPerScoreButton;
    private Button mPerNameButton;
    private ArrayList<Integer> mListScore;
    private ArrayList<User> mUserList;
    private ArrayList<User> mPlayerList;
    private ArrayList<User> mTopPlayerList;
    private int mScore;
    private String mFirstName;
    private User newUser;
    private Boolean mNewList;
    private Boolean mCondition;
    public static final String KEY_BOOLEAN = "KEY_BOOLEAN";
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    public String KEY_USERLIST = "KEY_USERLIST";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);


        mGreetingText = (TextView) findViewById(R.id.activity_main_greeting_txt);
        mPlayer1Text = (TextView) findViewById(R.id.activity_history_player1_txt);
        mPlayer2Text = (TextView) findViewById(R.id.activity_history_player2_txt);
        mPlayer3Text = (TextView) findViewById(R.id.activity_history_player3_txt);
        mPlayer4Text = (TextView) findViewById(R.id.activity_history_player4_txt);
        mPlayer5Text = (TextView) findViewById(R.id.activity_history_player5_txt);
        mPerScoreButton = (Button) findViewById(R.id.activity_history_perScore_btn);
        mPerNameButton = (Button) findViewById(R.id.activity_history_perName_btn);

        // Configuration Shared Preferences
        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mPreferences.edit();

        //Lecture variables dans Shared Preference
        mNewList = mPreferences.getBoolean(KEY_BOOLEAN, false);
        mFirstName = mPreferences.getString(KEY_FIRSTNAME, "null");
        mScore = mPreferences.getInt(KEY_SCORE, 0);


        if (mNewList = false) {
            mUserList = new ArrayList<User>();
            out.println("creation d'une nouvelle liste");
        }


        if (mNewList= true) {
                mUserList = new ArrayList<User>();
                out.println("creation d'une nouvelle liste");

            out.println("mNewList = true");
            AddtoList(mFirstName, mScore);
            out.println(mUserList);
        }


        // recupère le score du joueur enregistré dans préférence
        mPlayerList = new ArrayList<User>();
        User user1 = new User();
        user1.setFirstName("Toto");
        user1.setScore(4);
        User user2 = new User();
        user2.setFirstName("Lala");
        user2.setScore(2);
        User user3 = new User();
        user3.setFirstName("Bibi");
        user3.setScore(4);
        User user4 = new User();
        user4.setFirstName("Vava");
        user4.setScore(1);
        //User user5 = new User();
        //user5.setFirstName("Coco");
        //user5.setScore(1);
        //User user6 = new User();
        //user6.setFirstName("Coco");
        //user6.setScore(3);
        mPlayerList.add(user1);
        mPlayerList.add(user2);
        mPlayerList.add(user3);
        mPlayerList.add(user4);
        //mPlayerList.add(user5);
        //mPlayerList.add(user6);

        PerScore(mPlayerList);
        FiveBest();
        SetTextView();

        mPerNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PerName(mTopPlayerList);
                SetTextView();
            }
        });

        mPerScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PerScore(mTopPlayerList);
                SetTextView();
            }
        });

    }

    protected void AddtoList(String name, int score) {
        // Creer un utilisateur
        newUser = new User();
        newUser.setFirstName(name);
        newUser.setScore(score);

        mUserList.add(newUser);

        mNewList = true;
        mEditor.putBoolean(KEY_BOOLEAN, mNewList);
        mEditor.commit();

    }


    protected void PerScore(ArrayList<User> list) {
        //tri la liste par score du + grand au plus petit
        Collections.sort(list);
    }


    protected void PerName(ArrayList<User> list) {
        // tri la liste par ordre alphabetic A-Z
        Collections.sort(list, User.FirstnameComparator);
    }

    protected void FiveBest (){
        PerScore(mPlayerList);
        mTopPlayerList = new ArrayList<User>();
        int nbPlayer = mPlayerList.size();
        if (nbPlayer > 5){
            nbPlayer = 5;
        }
        for (int i = 0; i <= nbPlayer-1;i++){
            mTopPlayerList.add(mPlayerList.get(i));
        }
        out.println(mTopPlayerList);

    }

    protected void SetTextView() {

        //réinitialise tout les text view
        mPlayer1Text.setText("");
        mPlayer2Text.setText("");
        mPlayer3Text.setText("");
        mPlayer4Text.setText("");
        mPlayer5Text.setText("");

        int nbPlayer = mTopPlayerList.size();

        // condition sur le nombre de jouer
        switch (nbPlayer) {
            case 1:
                mPlayer1Text.setText("1- " + mTopPlayerList.get(0));
                break;
            case 2:
                mPlayer1Text.setText("1- " + mTopPlayerList.get(0));
                mPlayer2Text.setText("2- " + mTopPlayerList.get(1));
                break;
            case 3:
                mPlayer1Text.setText("1- " + mTopPlayerList.get(0));
                mPlayer2Text.setText("2- " + mTopPlayerList.get(1));
                mPlayer3Text.setText("3- " + mTopPlayerList.get(2));
                break;
            case 4:
                mPlayer1Text.setText("1- " + mTopPlayerList.get(0));
                mPlayer2Text.setText("2- " + mTopPlayerList.get(1));
                mPlayer3Text.setText("3- " + mTopPlayerList.get(2));
                mPlayer4Text.setText("4- " + mTopPlayerList.get(3));
                break;
            case 5:
                mPlayer1Text.setText("1- " + mTopPlayerList.get(0));
                mPlayer2Text.setText("2- " + mTopPlayerList.get(1));
                mPlayer3Text.setText("3- " + mTopPlayerList.get(2));
                mPlayer4Text.setText("4- " + mTopPlayerList.get(3));
                mPlayer5Text.setText("5- " + mTopPlayerList.get(4));
                break;

        }

    }


    @Override
    protected void onStart() {
        super.onStart();
        out.println("HistoryActivity::onStart()");
    }


    @Override
    protected void onResume() {
        super.onResume();
        out.println("HistoryActivity::onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        out.println("HistoryActivity::onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        out.println("HistoryActivity::onStop()");


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        out.println("HistoryActivity::onDestroy()");
    }

}

