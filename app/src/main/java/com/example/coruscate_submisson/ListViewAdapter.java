package com.example.coruscate_submisson;

import java.util.List;
import java.util.Random;
import java.util.TimerTask;

import android.app.Activity;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.content.Context;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.view.LayoutInflater;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.example.coruscate_submisson.Interface.ItemClickListner;
import com.example.coruscate_submisson.Model.UserModel;

public class ListViewAdapter extends BaseAdapter implements View.OnClickListener
{
    Context context;
    ItemClickListner listner;
    List<UserModel> TempSubjectList;
    CountDownTimer countDownTimer;
    ViewItem viewItem=null;
    CountDownTimer timer;
    Handler timerHandler;
    public ListViewAdapter(List<UserModel> listValue, Context context)
    {
        this.context = context;
        this.TempSubjectList = listValue;
    }

    @Override
    public int getCount()
    {
        return this.TempSubjectList.size();
    }

    @Override
    public Object getItem(int position)
    {
        return this.TempSubjectList.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {


        if(convertView == null)
        {
            viewItem = new ViewItem();

            LayoutInflater layoutInfiater = (LayoutInflater)this.context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            convertView = layoutInfiater.inflate(R.layout.cardviewdisplay, null);

            viewItem.IdTextView = convertView.findViewById(R.id.textviewID);

            viewItem.NameTextView = convertView.findViewById(R.id.textviewSubjectName);
            viewItem.Counter=convertView.findViewById(R.id.timer);
            viewItem.cd=convertView.findViewById(R.id.cardview);
            convertView.setTag(viewItem);
        }
        else
        {
            viewItem = (ViewItem) convertView.getTag();
        }
        viewItem.Counter=convertView.findViewById(R.id.timer);
        viewItem.IdTextView.setText(TempSubjectList.get(position).getName());
        viewItem.NameTextView.setText(TempSubjectList.get(position).getAddress());

        Random r=new Random();
         final int random=r.nextInt(10)+10;
        viewItem.Counter.setText(random+"");

       /* countDownTimer=new CountDownTimer(random*1000,1000) {
            @Override
            public void onTick(long l) {

                viewItem.Counter.setText( (l / (long)1000)+"");
            }

            @Override
            public void onFinish() {
                Toast.makeText(context, "Hello", Toast.LENGTH_SHORT).show();
            }
        }.start();*/

        /*viewItem.timer = new CountDownTimer(random*1000, 1000) {
            public void onTick(long millisUntilFinished) {
                viewItem.Counter.setText("" + millisUntilFinished/1000 + "");

            }

            public void onFinish() {
                viewItem.Counter.setText( "0:0");
                viewItem.cd.setBackgroundColor(Color.parseColor("#D81B60"));
            }
        }.start();*/
        return convertView;
    }




    public void setItemClickListner(ItemClickListner i){
        this.listner=i;
    }

    @Override
    public void onClick(View view) {
        //listner.onClick(view,position,false);
    }
}

class ViewItem
{
    TextView IdTextView;
    TextView NameTextView;
    TextView Counter;
    CountDownTimer timer;
    CardView cd;
}




