package com.kabir.imageeditor;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kabir.imageeditor.models.Cards;

import java.util.ArrayList;

public class FirebaseUtility {
    public static FirebaseDatabase mFirebaseDatabase;
    public static DatabaseReference mDatabaseReference;
    private static FirebaseUtility firebaseUtil;
    public static ArrayList<Cards> recipeArrayList;

    private FirebaseUtility(){};

    public static void openFBReference(String ref){
        if(firebaseUtil == null)
        {
            firebaseUtil = new FirebaseUtility();
            mFirebaseDatabase = FirebaseDatabase.getInstance();
            recipeArrayList = new ArrayList<Cards>();
        }
        mDatabaseReference = mFirebaseDatabase.getReference().child(ref);
    }
}
