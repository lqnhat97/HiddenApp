package com.example.vinhhoang.takescreenshot;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private View main;
    private ListView listView;
    private ImageView imageView;
    private static final int MY_PERMISSION_REQUEST=1;
    ArrayList<String> arrayList;
    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main = findViewById(R.id.main);
        imageView = (ImageView) findViewById(R.id.imageView);
        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bitmap = ScreenShot.takeScreenShotOfRootView(imageView);
                imageView.setImageBitmap(bitmap);

            }
        });

        listView=findViewById(R.id.listView1);
        List<String> stringList=readAllMessage();
        if(stringList!=null) {
            String messageCSV = "";
            for (int i = 0; i < stringList.size(); i++) {
                messageCSV += stringList.get(i) + ",";
            }


        Button btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doStuff();
            }
        });

            String messageArray[] = messageCSV.split(",");
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, messageArray);
            listView.setAdapter(adapter);
        }

        if(ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.READ_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED)
        {
            if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,Manifest.permission.READ_EXTERNAL_STORAGE))
            {
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},MY_PERMISSION_REQUEST);
            }
            else {
                ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},MY_PERMISSION_REQUEST);
            }
        }


    }

    public void getMusic()
    {
        ContentResolver contentResolver=getContentResolver();
        Uri songURI = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        Cursor  cursor =contentResolver.query(songURI,null,null,null,null);
        if(cursor!=null && cursor.moveToFirst())
        {
            int songTitle=cursor.getColumnIndex(MediaStore.Video.Media.TITLE);
            int songArtist=cursor.getColumnIndex(MediaStore.Video.Media.ARTIST);
            int songLocation=cursor.getColumnIndex(MediaStore.Video.Media.DATA);

            do {
                String currentTitle=cursor.getString(songTitle);
                String currentArtist=cursor.getString(songArtist);
                String currentLocation=cursor.getString(songLocation);
                arrayList.add("Title: "+ currentTitle+"\n"+
                        "Artist: " +currentArtist+"\n"+
                        "Location: "        +currentLocation);
            }while (cursor.moveToNext());
        }
    }

    public void doStuff(){
        arrayList=new ArrayList<>();
        getMusic();
        adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions,int[] grantResults)
    {
       switch (requestCode)
       {
           case  MY_PERMISSION_REQUEST:{
               if(grantResults.length>0&& grantResults[0]==PackageManager.PERMISSION_GRANTED)
               {
                   if(ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.READ_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED)
                   {
                       Toast.makeText(this, "Permission granted!",Toast.LENGTH_SHORT).show();


                   }
               }else{
                   Toast.makeText(this,"NO PERMISSION GRANTED!",Toast.LENGTH_SHORT).show();
                   finish();
               }
               return;
           }
       }
    }

    public List<String> readAllMessage()
    {
        List<String> sms =  new ArrayList<String>();
        Uri uriSMSURI= Uri.parse("content://sms/inbox");

        if(ContextCompat.checkSelfPermission(MainActivity.this, "android.permission.READ_SMS") != PackageManager.PERMISSION_GRANTED) {
            final int REQUEST_CODE_ASK_PERMISSIONS = 123;
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.READ_SMS"}, REQUEST_CODE_ASK_PERMISSIONS);
        }

        Cursor cur =getContentResolver().query(uriSMSURI,null,null,null,null);
        while(cur.moveToNext())
        {
            String address=cur.getString(cur.getColumnIndex("address"));
            String body=cur.getString(cur.getColumnIndexOrThrow("body"));
            sms.add(address);
            sms.add(body);
        }
        return sms;
    }
}
