package org.estrada.votedroid;


import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import org.estrada.votedroid.modele.VDQuestion;

import java.util.ArrayList;
import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.MyViewHolder> {

public List<VDQuestion> list;

/**
 * Provide a reference to the type of views that you are using
 * (custom ViewHolder).
 */
public static class MyViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
    public TextView txtQuestion;
    public ImageButton btnImageQ;

    public MyViewHolder(LinearLayout v) {
        super(v);
        // Define click listener for the ViewHolder's View
        txtQuestion = v.findViewById(R.id.txtQuestion);
        btnImageQ = v.findViewById(R.id.btnImage);

        // TODO : les ecouteurs vont dans le onbindviewholder
        btnImageQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Resultat.class);
                v.getContext().startActivity(i);
            }
        });

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Vote.class);
                v.getContext().startActivity(i);
            }
        });
    }
}
    public QuestionAdapter() {
        list = new ArrayList<VDQuestion>();
    }

    // Create new views (invoked by the layout manager)
    @Override
    public QuestionAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.question_item, parent, false);

        MyViewHolder vh = new MyViewHolder(v);

        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        holder.txtQuestion.setText(list.get(position).texteQuestion);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return list.size();
    }

}

