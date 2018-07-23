package com.kabir.imageeditor.adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kabir.imageeditor.EditImageActivity;
import com.kabir.imageeditor.FirebaseUtility;
import com.kabir.imageeditor.models.Cards;
import com.outstarttech.kabir.eidcardeditor.R;

import java.util.ArrayList;

public class CardsAdapter2  extends RecyclerView.Adapter<CardsAdapter2.DealViewHolderRecipe> {

    ArrayList<Cards> cardss;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ChildEventListener mChildListener;
    Context context;
    ProgressDialog progressBarDialog;

    public CardsAdapter2(){
        FirebaseUtility.openFBReference("backgrounds");
        mFirebaseDatabase = FirebaseUtility.mFirebaseDatabase;
        mDatabaseReference = FirebaseUtility.mDatabaseReference;
        cardss = FirebaseUtility.recipeArrayList;
        mChildListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Cards items = dataSnapshot.getValue(Cards.class);
                cardss.add(items);
                notifyItemInserted(cardss.size() - 1);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        mDatabaseReference.addChildEventListener(mChildListener);

        cardss.clear();

    }

    @NonNull
    @Override
    public CardsAdapter2.DealViewHolderRecipe onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();


        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.listtemplate, parent, false);

        return  new CardsAdapter2.DealViewHolderRecipe(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CardsAdapter2.DealViewHolderRecipe holder, int position) {

        Cards recipes1 = cardss.get(position);
        holder.bind(recipes1);
    }

    @Override
    public int getItemCount() {
        return cardss.size();
    }

    public class DealViewHolderRecipe extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView cardImageView;
        TextView cardID;
        String photoURLL;

        public DealViewHolderRecipe (View itemView){
            super(itemView);
            //cardID = (TextView) itemView.findViewById(R.id.cardtitle);
            cardImageView = (ImageView) itemView.findViewById(R.id.cardimagelist);
            itemView.setOnClickListener(this);
        }

        public void bind(Cards recipes){
            //cardID.setText(recipes.getCardID());
            photoURLL = recipes.getCardURL();   // recipe photourl
            Log.e("PicUrl", "ThisUrl");
            Glide.with(context).load(photoURLL).into(cardImageView);

        }

        @Override
        public void onClick(View view) {
            progressBarDialog = new ProgressDialog(context);
            progressBarDialog.setTitle("Please Wait");
            progressBarDialog.setMessage("Loading Card Editor...");
            progressBarDialog.show();
            int position = getAdapterPosition();
            Cards selectedItem = cardss.get(position);
            Intent intent = new Intent(view.getContext(), EditImageActivity.class);
            intent.putExtra("Card", selectedItem);
            view.getContext().startActivity(intent);

        }
    }


}
