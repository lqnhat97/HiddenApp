package com.ntt.tainguyen197.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentPhoto.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentPhoto#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentPhoto extends Fragment {
    View view;
    GridView gridView;
    GridviewAdapter gridviewAdapter;

    public ArrayList<HinhAnh> getAnhArrayList() {
        return anhArrayList;
    }

    ArrayList<HinhAnh> anhArrayList;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FragmentPhoto() {
        // Required empty public constructor
        getClass();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentPhoto.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentPhoto newInstance(String param1, String param2) {
        FragmentPhoto fragment = new FragmentPhoto();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_fragment_photo, container, false);
        gridView = (GridView) view.findViewById(R.id.gridviewfragmentaddsv);
        anhArrayList = new ArrayList<HinhAnh>();
        DataArray();
        gridviewAdapter = new GridviewAdapter(anhArrayList,getActivity().getApplicationContext());
        gridView.setAdapter(gridviewAdapter);
        return view;
    }

    public void DataArray() {

        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Permission is not granted
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},3);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callbac\k method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted

            Uri uri,uri1;
            Cursor cursor,cursor1;
            int column_index_data,column_index_data1, column_index_dateTaken,column_index_folder_name1;
            String absolutePathOfImage = null;
            uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            uri1 = MediaStore.Images.Media.INTERNAL_CONTENT_URI;
            String[] projection = {MediaStore.MediaColumns.DATA,
                    MediaStore.Images.Media.BUCKET_DISPLAY_NAME,MediaStore.Images.Media.DATE_TAKEN};

            String orderby = MediaStore.Images.Media.DATE_TAKEN;
            cursor = getActivity().getContentResolver().query(uri, projection, null,
                    null, orderby);
            cursor1 = getActivity().getContentResolver().query(uri1, projection, null,
                    null, null);

            column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            column_index_data1 = cursor1.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);

            column_index_dateTaken = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATE_TAKEN);
            //Đổi từ mili giây sang ngày
            Calendar myCal= Calendar.getInstance();
            myCal.setTimeInMillis(column_index_dateTaken/1000);
            Date dateText = new Date(myCal.get(Calendar.YEAR)-1900,
                    myCal.get(Calendar.MONTH),
                    myCal.get(Calendar.DAY_OF_MONTH),
                    myCal.get(Calendar.HOUR_OF_DAY),
                    myCal.get(Calendar.MINUTE));

            column_index_folder_name1 = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATE_TAKEN);
            while (cursor.moveToNext()) {

                absolutePathOfImage = cursor.getString(column_index_data);
                anhArrayList.add(new HinhAnh(absolutePathOfImage,orderby));
                Log.e("Column", absolutePathOfImage);
                Log.e("MyApp", "DATE: " + android.text.format.DateFormat.format("MM/dd/yyyy hh:mm", dateText));
            }
            while (cursor1.moveToNext()) {
                absolutePathOfImage = cursor1.getString(column_index_data);
                anhArrayList.add(new HinhAnh(absolutePathOfImage,orderby));
            }

        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
