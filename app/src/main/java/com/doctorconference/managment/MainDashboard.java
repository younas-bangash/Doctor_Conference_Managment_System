package com.doctorconference.managment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.doctorconference.managment.invatition.invatitionFragment;
import com.doctorconference.managment.topictab.TopicsRecordFragment;

import java.util.List;

public class MainDashboard extends AppCompatActivity implements
        TopicsRecordFragment.OnListFragmentInteractionListener,
        invatitionFragment.OnListFragmentInteractionListener{

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
                if(position==0 )
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
                        ShowDetails(null);
                        break;
                }
            }
        });

    }
    public void ShowDetails(final GetSetData data) {
        final Dialog dialog;
        dialog = new Dialog(this, R.style.CustomDialog); //this is a reference to the style above
        dialog.setContentView(R.layout.identify_callout_content); //I saved the xml file above as yesnomessage.xml
        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        final EditText title= (EditText) dialog.findViewById(R.id.title);
        final EditText date= (EditText) dialog.findViewById(R.id.date);
        final Button mAddRecord= (Button) dialog.findViewById(R.id.button);
        final Button mcancel= (Button) dialog.findViewById(R.id.buttoncancel);
        if(data !=null){
            title.setText(data.getmTopicTitle());
            date.setText(data.getmTopicDetails());
            mAddRecord.setText("Update");
        }


        mAddRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(title.getText().toString().trim().isEmpty())
                    Toast.makeText(MainDashboard.this, "Please Fill Required Field", Toast.LENGTH_SHORT).show();
                else if(date.getText().toString().trim().isEmpty()){
                    Toast.makeText(MainDashboard.this, "Please Fill Required Field", Toast.LENGTH_SHORT).show();
                   // GetSetData tempdate=new GetSetData("",fname.getText().toString().trim(), )
                }else{
                    dialog.cancel();
                    if(data==null)
                        Utils.db.addConf(new GetSetData("",title.getText().toString(),date.getText().toString()));
                    else
                        Utils.db.updateContact(new GetSetData(data.getmTopicD(),title.getText().toString(),date.getText().toString()));
                    // Reload current fragment

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
    public void EditDeleteConfrence(final GetSetData data) {
        final Dialog dialog;
        dialog = new Dialog(this, R.style.CustomDialog); //this is a reference to the style above
        dialog.setContentView(R.layout.edit_delete_confrence); //I saved the xml file above as yesnomessage.xml
        dialog.setCancelable(true);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        final TextView edit= (TextView) dialog.findViewById(R.id.edit);
        final TextView delete= (TextView) dialog.findViewById(R.id.delete);
        final TextView invite= (TextView) dialog.findViewById(R.id.invite);
        edit.setText("Accept and Add Invitation");
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                //ShowDetails(data);
                //Utils.db.updateContact(data);

            }
        });

        delete.setText("Reject The Invitation");
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteWarningDialong(data);
                dialog.cancel();
            }


        });
        invite.setText("Cancel");
        invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
//                Toast.makeText(MainDashboard.this, "Select Doctore To Send Invition", Toast.LENGTH_SHORT).show();
//                List<GetSetData> mDoctoreRecord=Utils.db.checkUserDetails("","",true);
//                //Creating the instance of PopupMenu
//                PopupMenu popup = new PopupMenu(MainDashboard.this,invite);
//                popup.setGravity(Gravity.CENTER_HORIZONTAL);
//                //Inflating the Popup using xml file
//                for (int i=0;i<mDoctoreRecord.size();i++)
//                    popup.getMenu().add(Menu.NONE, i, Menu.NONE, mDoctoreRecord.get(i).getmFirstName()+" "+mDoctoreRecord.get(i).getmLastName());
//                //registering popup with OnMenuItemClickListener
//                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                    public boolean onMenuItemClick(MenuItem item) {
//                        //(String mConfrenceID, String mDoctoreID, String mTitle,String mDate)
//                        GetSetData mTemp=new GetSetData(
//                                data.getmTopicD(),
//                                ""+item.getItemId(),
//                                data.getmTopicTitle(),
//                                data.getmTopicDetails());
//                        Utils.db.addInvit(mTemp);
//                        Toast.makeText(MainDashboard.this,"Invitation Send Successfully...",Toast.LENGTH_SHORT).show();
//                        return true;
//                    }
//                });
//
//                popup.show();//showing popup menu
            }
        });
//        dialog.show();

    }

    private void showDeleteWarningDialong(final GetSetData item) {
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure,You wanted to Reject ?");

        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                Utils.db.deleteInvit(item);
                Toast.makeText(MainDashboard.this, "Invitation Rejected", Toast.LENGTH_SHORT).show();
            }
        });

        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
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
        if(mViewPager.getCurrentItem()==0)
            EditDeleteConfrence(item);
        else
            Toast.makeText(MainDashboard.this, item.getmFirstName(), Toast.LENGTH_SHORT).show();

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
                    return new invatitionFragment();
                case 1:
                    return new TopicsRecordFragment();

                default:
                    break;
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Invitations";
                case 1:
                    return "Suggested Topics";
            }
            return null;
        }
    }


}
