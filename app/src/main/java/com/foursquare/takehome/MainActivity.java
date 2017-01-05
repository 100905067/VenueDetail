package com.foursquare.takehome;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName() ;
    private RecyclerView rvRecyclerView;
    private PersonAdapter personAdapter;
    private List<Person> pList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvRecyclerView = (RecyclerView) findViewById(R.id.rvRecyclerView);
        rvRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        personAdapter = new PersonAdapter(pList, getApplicationContext());
        rvRecyclerView.setAdapter(personAdapter);

        //TODO hook up your adapter and any additional logic here

        parseVenueFromResponse();
    }


    /**
     * Parsing a fake json response from assets/people.json
     */
    private void parseVenueFromResponse() {
        new AsyncTask<Void, Void, Venue>() {
            @Override
            protected Venue doInBackground(Void... params) {
                try {
                    InputStream is = getAssets().open("people.json");
                    InputStreamReader inputStreamReader = new InputStreamReader(is);

                    PeopleHereJsonResponse response = new Gson().fromJson(inputStreamReader, PeopleHereJsonResponse.class);
                    return response.getVenue();
                } catch (Exception e) {}

                return null;
            }

            @Override
            protected void onPostExecute(Venue venue) {
                //TODO use the venue object to populate your recyclerview
                List<Person> personVenueList = venue.getVisitors();
                Collections.sort(personVenueList, new Comparator<Person>() {

                    public int compare(Person p1, Person p2) {
                        return p2.compareTo(p1);
                    }
                });
                int count = personVenueList.size()+1;
                long endTime = venue.getOpenTime();
                for(int i=0;i<personVenueList.size();i++) {
                    Person current = personVenueList.get(i);
                    if (endTime < current.getArriveTime())
                        pList.add(new Person(count, null, endTime, current.getArriveTime()));
                    pList.add(current);
                    if(endTime < current.getLeaveTime())
                        endTime = current.getLeaveTime();
                    count++;
                }
                if(endTime<venue.getCloseTime()) {
                    pList.add(new Person(count, null, endTime, venue.getCloseTime()));
                }
            }
        }.execute();
    }
}
