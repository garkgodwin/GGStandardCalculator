package com.example.ggstandardcalculator.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ggstandardcalculator.MainActivity;
import com.example.ggstandardcalculator.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class StandardHistoryAdapter extends RecyclerView.Adapter<StandardHistoryAdapter.MyViewHolder> {

    private ArrayList<String> inputs;
    private ArrayList<String> totals;
    private MainActivity context;
    private PopupWindow popupWindow;

    public StandardHistoryAdapter(ArrayList<String> inputs, ArrayList<String> totals, MainActivity context, PopupWindow popupWindow){
        this.inputs = inputs;
        this.totals = totals;
        this.context = context;
        this.popupWindow = popupWindow;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_standard_history, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Log.d("ADAPTER", "onBindViewHolder: called");
        holder.txtArithmetic.setText(inputs.get(position).toString());
        holder.txtTotal.setText(totals.get(position).toString());


        holder.txtArithmetic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClicks(holder);
            }
        });
        holder.txtTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClicks(holder);
            }
        });
        holder.cardHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClicks(holder);
            }
        });
    }


    @Override
    public int getItemCount() {
        return inputs.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtArithmetic, txtTotal;
        CardView cardHistory;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtArithmetic = itemView.findViewById(R.id.txt_arithmetic);
            txtTotal = itemView.findViewById(R.id.txt_total);
            cardHistory = itemView.findViewById(R.id.card_standard_history);
        }
    }


    @SuppressLint("ResourceAsColor")
    private void onClicks(MyViewHolder holder){
        holder.cardHistory.setCardBackgroundColor(R.color.gray);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Please choose an action.") .setTitle("System Message");
        builder.setPositiveButton("Arithmetic", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                TextView txt = (TextView)context.findViewById(R.id.txt_standard_input);
                txt.setText(holder.txtArithmetic.getText().toString());
                String values[] = holder.txtArithmetic.getText().toString().split("\\s");
                context.setFirstNumber(Double.parseDouble(values[0]));
                context.setSecondNumber(Double.parseDouble(values[2]));
                int op = 0;
                if(values[1].contains("+")){
                    op = 1;
                }
                else if(values[1].contains("-")){
                    op = 2;
                }
                else if(values[1].contains("x")){
                    op = 3;
                }
                else if(values[1].contains("÷")){
                    op = 4;
                }
                context.setOperation(op);
                context.setTextLength();
                popupWindow.dismiss();
            }
        });
        builder.setNegativeButton("Total", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TextView txt = (TextView)context.findViewById(R.id.txt_standard_input);
                if(Double.parseDouble(holder.txtTotal.getText().toString())%1== 0) { //this condition will check if the number is whole or not
                    DecimalFormat df = new DecimalFormat("0.#");
                    String total = holder.txtTotal.getText().toString();
                    txt.setText(df.format(Double.parseDouble(total)));
                }
                else {
                    txt.setText(holder.txtTotal.getText().toString());
                }
                context.setOperation(0);
                context.setTextLength();
                popupWindow.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}
