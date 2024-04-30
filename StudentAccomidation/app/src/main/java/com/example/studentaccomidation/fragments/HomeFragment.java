package com.example.studentaccomidation.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Process;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.studentaccomidation.R;
import com.example.studentaccomidation.resItem;
import com.example.studentaccomidation.resItemAdapter;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<resItem> resItems;
    resItemAdapter resItemAdapter;
    FirebaseFirestore db;
    ProgressDialog progressDialog;

    public HomeFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        progressDialog=new ProgressDialog(requireContext());
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching Data...");
        progressDialog.show();

        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        db = FirebaseFirestore.getInstance();
        resItems = new ArrayList<resItem>();
        resItemAdapter = new resItemAdapter(requireContext(), resItems);

        recyclerView.setAdapter(resItemAdapter);

        eventChangeListener();
        return view;
    }

    private void eventChangeListener() {
        db.collection("residences").orderBy("name", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error != null){
                            if(progressDialog.isShowing()){
                                progressDialog.dismiss();
                            }
                            Toast.makeText(requireContext(), "Error getting data", Toast.LENGTH_SHORT).show();
                        }
                        assert value != null;
                        for (DocumentChange documentChange: value.getDocumentChanges()){
                            if (documentChange.getType() == DocumentChange.Type.ADDED){
                                String documentId = documentChange.getDocument().getId();
                                resItems.add(documentChange.getDocument().toObject(resItem.class));
                                resItem.documentId = documentId;
                            }
                        }
                        resItemAdapter.notifyDataSetChanged();
                        if(progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                    }
                });
    }
}