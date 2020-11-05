package com.example.adminapp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.Viewholder> {

    private List<QuestionModel> list;
    private String category;
    private DeleteListener listener;

    public QuestionAdapter(List<QuestionModel> list,String category,DeleteListener listener) {
        this.list = list;
        this.category = category;
        this.listener = listener;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_item,parent,false);

        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {

        String question = list.get(position).getQuestion();
        String answer = list.get(position).getAnswer();

       holder.setData(question,answer,position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Viewholder extends RecyclerView.ViewHolder {
        TextView question, answer;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            question = itemView.findViewById(R.id.question);
            answer = itemView.findViewById(R.id.answer);
        }

        private void setData(String question, String answer, final int position) {

            this.question.setText(position + 1 + ". " + question);
            this.answer.setText("Ans. " + answer);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent editintent = new Intent(itemView.getContext(),AddQuestionActivity.class);
                    editintent.putExtra("categoryName",category);
                    editintent.putExtra("setId",list.get(position).getSet());
                    editintent.putExtra("position",position);

                    itemView.getContext().startActivity(editintent);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    listener.onLongClick(position,list.get(position).getId());
                    return false;
                }
            });
        }
    }

    public interface DeleteListener{

        void onLongClick(int position,String id);
    }
}
