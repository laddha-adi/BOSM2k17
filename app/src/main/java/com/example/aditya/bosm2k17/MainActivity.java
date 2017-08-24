package com.example.aditya.bosm2k17;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.aditya.bosm2k17.Data.sports;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    DatabaseReference mDatabase;
    ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       final List<Integer> Fixtures= new ArrayList<>();
       final List<Integer> NonFixtures= new ArrayList<>();

        Fixtures.add(0,1);
        NonFixtures.add(1,2);
        NonFixtures.add(2,3);
        Fixtures.add(3,4);
        Fixtures.add(4,5);
        Fixtures.add(5,6);
        NonFixtures.add(6,7);
        NonFixtures.add(7,8);
        Fixtures.add(8,9);
        Fixtures.add(9,10);
        NonFixtures.add(10,11);
        Fixtures.add(11,12);
        Fixtures.add(12,13);
        Fixtures.add(13,14);
        Fixtures.add(14,15);
        Fixtures.add(15,16);
        Fixtures.add(16,17);
        Fixtures.add(17,18);
        Fixtures.add(18,19);
        NonFixtures.add(19,20);
        Fixtures.add(20,21);
        Fixtures.add(21,22);
        Fixtures.add(22,23);
        NonFixtures.add(23,24);
        Fixtures.add(24,25);
        Fixtures.add(25,26);
        Fixtures.add(26,27);

        // Fixtures.add(1,3);

             NonFixtures.add(0,2);
             NonFixtures.add(1,3);

        pDialog = new ProgressDialog(this);
        pDialog.setTitle("fetching data from server");
        pDialog.setMessage("Loading...");
        pDialog.show();
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.setCancelable(false);

        mDatabase   = FirebaseDatabase.getInstance().getReference().child("Schedule");
        sports = new Sports();
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                for (DataSnapshot sportType : dataSnapshot.getChildren()) {

                    if(Fixtures.contains(Integer.valueOf(sportType.getKey()))){
                        List<FixtureSportsData> SportList= new ArrayList();
                        for (DataSnapshot sportEvent : sportType.getChildren()) {
                            ArrayList<String> categoryResultList = new ArrayList<>();

                            if(sportEvent.getKey().compareTo("rounds")==0){
                            String round =(String) sportEvent.getValue();
                            List<String> RoundList = Arrays.asList(round.split(","));
                            Data.sports.SportsRoundList.add(Integer.valueOf(sportType.getKey()),RoundList);
                            //Toast.makeText(MainActivity.this, round, Toast.LENGTH_SHORT).show();
                            }

                            else if(sportEvent.getKey().compareTo("matches")==0)
                                {
                                    for (DataSnapshot sportMatches : sportType.child("matches").getChildren())
                                    {
                            String TeamA = (String) sportMatches.child("Team1").getValue();
                            String TeamB = (String) sportMatches.child("Team2").getValue();
                            String date = (String) sportMatches.child("Date").getValue();
                            String time = (String) sportMatches.child("Time").getValue();
                            String venue= (String) sportMatches.child("Venue").getValue();
                            String round = (String) sportMatches.child("Round").getValue();
                            String winner = (String) sportMatches.child("Winner").getValue();
                            FixtureSportsData data = new FixtureSportsData(TeamA,TeamB,date,time,venue,round,winner);

                                SportList.add(data);
                        }
                                }
                        }
                        Data.sports.fixtureSportsDataList.add(Integer.valueOf(sportType.getKey()),SportList);

                     }
                 else if(NonFixtures.contains(Integer.valueOf(sportType.getKey()))) {

                        List<NonFixtureSportsData> DataList= new ArrayList();
                        for (DataSnapshot sportEvent : sportType.getChildren()) {

                            if (sportEvent.getKey().compareTo("rounds") == 0) {
                                String round = (String) sportEvent.getValue();
                                List<String> RoundList = new ArrayList<String>();
                                RoundList = Arrays.asList(round.split(","));
                                Data.sports.SportsRoundList.add(Integer.valueOf(sportType.getKey()), RoundList);
                            }
                            else if (sportEvent.getKey().compareTo("matches") == 0) {

                               for( DataSnapshot sportMatches : sportType.child("matches").getChildren()){

                                    String date = (String) sportMatches.child("Date").getValue();
                                    String time = (String) sportMatches.child("Time").getValue();
                                    String venue = (String) sportMatches.child("Venue").getValue();
                                    String round = (String) sportMatches.child("Round").getValue();

                                    ArrayList<String> categoryNameList = new ArrayList<>();
                                    ArrayList<String> categoryDescList = new ArrayList<>();
                                    ArrayList<ArrayList<String>> categoryResultList = new ArrayList<>();

                                   for (DataSnapshot sportEventsDetails : sportMatches.getChildren()) {
                                        if (sportEventsDetails.getKey().compareTo("CategoryDescription") == 0) {
                                            for (DataSnapshot catDesc : sportEventsDetails.getChildren()) {
                                                String categoryDescElement = (String) catDesc.getValue();
                                                categoryDescList.add(categoryDescElement);
                                            }
                                        } else if (sportEventsDetails.getKey().compareTo("CategoryName") == 0) {
                                            for (DataSnapshot catName : sportEventsDetails.getChildren()) {
                                                String categoryNameElement = (String) catName.getValue();
                                                categoryNameList.add(categoryNameElement);
                                            }
                                        } else if (sportEventsDetails.getKey().compareTo("CategoryResult") == 0) {

                                            for (DataSnapshot catName : sportEventsDetails.getChildren()) {
                                                ArrayList<String> CategoryWiseResult = new ArrayList<String>();
                                                for (DataSnapshot catResult : catName.getChildren()) {
                                                    String resultElement = (String) catResult.getValue();
                                                    CategoryWiseResult.add(resultElement);

                                                    for (int i = categoryResultList.size(); i <= Integer.valueOf(catName.getKey()); i++) {

                                                            ArrayList<String> dummyCategoryWiseResult = new ArrayList<String>();
                                                            categoryResultList.add(i, dummyCategoryWiseResult);

                                                    }
                                                }
                                                  categoryResultList.add(Integer.valueOf(catName.getKey()),CategoryWiseResult);
                                            }
                                        }
}
                                    NonFixtureSportsData data = new NonFixtureSportsData(categoryNameList, categoryDescList, date, time, venue, round, categoryResultList);
                                    DataList.add(data);
                               }}}
                        Data.sports.nonFixtureSportsDataList.add(Integer.valueOf(sportType.getKey()),DataList);
                    }
                    }
                Data.onDataChanged();
                }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }}

