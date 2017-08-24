package com.example.aditya.bosm2k17;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

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
             Fixtures.add(1,3);

             NonFixtures.add(0,2);
             NonFixtures.add(1,4);

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
                            Toast.makeText(MainActivity.this, round, Toast.LENGTH_SHORT).show();
                            }

                            else if(sportEvent.getKey().compareTo("matches")==0)
                                {          //  Log.e("qas",abc.toString());

                                    for (DataSnapshot sportMatches : sportType.child("matches").getChildren())
                                    {
                                    //  Log.e("data",sportMatches.toString());
                                     //   Log.e("qaslj",sportMatches.toString());

                            String TeamA = (String) sportMatches.child("Team1").getValue();
                            String TeamB = (String) sportMatches.child("Team2").getValue();
                            String date = (String) sportMatches.child("Date").getValue();
                            String time = (String) sportMatches.child("Time").getValue();
                            String venue= (String) sportMatches.child("Venue").getValue();
                            String round = (String) sportMatches.child("Round").getValue();
                            String winner = (String) sportMatches.child("Winner").getValue();

//Log.e("hello",TeamA+TeamB);

                                FixtureSportsData data = new FixtureSportsData(TeamA,TeamB,date,time,venue,round,winner);
                                // Toast.makeText(MainActivity.this, data.getDate(), Toast.LENGTH_SHORT).show();

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

                                for (DataSnapshot sportMatches :sportType.child("matches").getChildren()) {

                                    String date = (String) sportMatches.child("Date").getValue();
                                    String time = (String) sportMatches.child("Time").getValue();
                                    String venue = (String) sportMatches.child("Venue").getValue();
                                    String round = (String) sportMatches.child("Round").getValue();

                                    ArrayList<String> categoryNameList = new ArrayList<>();
                                    ArrayList<String> categoryDescList = new ArrayList<>();
                                    ArrayList<ArrayList<String>> categoryResultList = new ArrayList<>();


                                    for (DataSnapshot InnerEventDetails : sportEvent.getChildren()) {
                                        if (InnerEventDetails.getKey().compareTo("CategoryDesc") == 0) {
                                            for (DataSnapshot catDesc : InnerEventDetails.getChildren()) {
                                                String categoryDescElement = (String) catDesc.getValue();
                                                categoryDescList.add(categoryDescElement);
                                            }
                                        } else if (InnerEventDetails.getKey().compareTo("CategoryName") == 0) {
                                            for (DataSnapshot catName : InnerEventDetails.getChildren()) {
                                                String categoryNameElement = (String) catName.getValue();
                                                categoryNameList.add(categoryNameElement);
                                            }
                                        } else if (InnerEventDetails.getKey().compareTo("CategoryResult") == 0) {
                                            for (DataSnapshot catName : InnerEventDetails.getChildren()) {
                                                ArrayList<String> CategoryWiseResult = new ArrayList<String>();
                                                for (DataSnapshot catResult : catName.getChildren()) {
                                                    String resultElement = (String) catResult.getValue();
                                                    CategoryWiseResult.add(resultElement);
                                                }

                                                categoryResultList.add(CategoryWiseResult);
                                                // Toast.makeText(MainActivity.this, categoryResultElement, Toast.LENGTH_SHORT).show();
                                            }
                                        }

                                    }

                                    NonFixtureSportsData data = new NonFixtureSportsData(categoryNameList, categoryDescList, date, time, venue, round, categoryResultList);
                                    DataList.add(data);
                                }
                            }
                        }                            Data.sports.nonFixtureSportsDataList.add(Integer.valueOf(sportType.getKey()),DataList);


                    }
                    }
                Data.onDataChanged();
             printData();
                }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

   private void printData() {
       // textView=(TextView)findViewById(R.id.textView);
       // textView.setText("");
      //  Log.e("data",String.valueOf(Data.sports.getNonFixtureSportsDataList().get(2).get(0).getDate()));
        pDialog.dismiss();
    }
}
