package com.doctorconference.managment;

import android.app.Dialog;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.doctorconference.managment.doctorrecordtab.DoctorRecordFragment;
import com.doctorconference.managment.topictab.TopicsRecordFragment;

public class MainDashboard extends AppCompatActivity implements
        TopicsRecordFragment.OnListFragmentInteractionListener,
        DoctorRecordFragment.OnListFragmentInteractionListener{

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    public static  FloatingActionButton fab;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dashboard);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                if(position==2 || position==1 )
                    fab.setVisibility(View.GONE);
                else
                    fab.setVisibility(View.VISIBLE);
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        Utils.db = new DatabaseHandler(this);
//        Utils.db.addTopics(new GetSetData("","Topic1 Tittle","TopicDetails"));
//        Utils.db.addTopics(new GetSetData("","Topic2 Tittle","TopicDetails"));
//        Utils.db.addTopics(new GetSetData("","Topic3 Tittle","TopicDetails"));
//        Utils.db.addTopics(new GetSetData("","Topic4 Tittle","TopicDetails"));
//        Utils.db.addTopics(new GetSetData("","Topic5 Tittle","TopicDetails"));
//        Utils.db.addTopics(new GetSetData("","Topic6 Tittle","TopicDetails"));

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (mViewPager.getCurrentItem()){
                    case 0:
                        ShowDetails();
                        break;
                }
            }
        });

    }
    public void ShowDetails() {
        final Dialog dialog;
        dialog = new Dialog(this, R.style.CustomDialog); //this is a reference to the style above
        dialog.setContentView(R.layout.identify_callout_content); //I saved the xml file above as yesnomessage.xml
        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        final EditText fname= (EditText) dialog.findViewById(R.id.fname);
        final TextView mHeading= (TextView) dialog.findViewById(R.id.text);
        final EditText lname= (EditText) dialog.findViewById(R.id.lname);
        final Button mAddRecord= (Button) dialog.findViewById(R.id.button);
        final Button mcancel= (Button) dialog.findViewById(R.id.buttoncancel);
 //       mTempData = new GetSetData(
//                                fname.getText().toString(),
//                                lname.getText().toString(),
//                                contact.getText().toString(),
//                                email.getText().toString(),
//                                bgroup.getText().toString(),
//                                mCategory,
//                                mLocation,
//                                Double.toString(10));

        mAddRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fname.getText().toString().trim().isEmpty())
                    Toast.makeText(MainDashboard.this, "Please Fill Required Field", Toast.LENGTH_SHORT).show();
                else{
                   // GetSetData tempdate=new GetSetData("",fname.getText().toString().trim(), )
                }

            }
        });

        mcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onListFragmentInteraction(GetSetData item) {
        if(mViewPager.getCurrentItem()==1)
            Toast.makeText(MainDashboard.this, item.getmFirstName(), Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(MainDashboard.this, item.getmFirstName(), Toast.LENGTH_SHORT).show();

    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main_dashboard, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position){
                case 0:
                    return new DoctorRecordFragment();
                case 1:
                    return new DoctorRecordFragment();
                case 2:
                    return new TopicsRecordFragment();

                default:
                    break;
            }
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Conferences";
                case 1:
                    return "Doctors";
                case 2:
                    return "Suggested Topics";
            }
            return null;
        }
    }
}
