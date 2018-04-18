package com.mehmetzantur.medicaldic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by mehme on 11.05.2016.
 */
public class WordAdapter extends ArrayAdapter<WordPoco> {
    Context context;
    int layoutResourceId;
    WordPoco Words[] = null;

    public WordAdapter(Context context, int resource, WordPoco[] word){
        super(context,resource,word);
        this.context=context;
        this.layoutResourceId=resource;
        this.Words = word;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        wordViewHolder holder = null;


        if(row == null){

            LayoutInflater inflater = LayoutInflater.from(context);


            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new wordViewHolder();

            holder.title = (TextView) row.findViewById(R.id.itemTitle);
            holder.definition = (TextView) row.findViewById(R.id.itemDefi);

            row.setTag(holder);
        }
        else{
            holder = (wordViewHolder) row.getTag();
        }

        WordPoco poco = Words[position];
        holder.title.setText(poco.getTitle());
        holder.definition.setText(poco.getDefinition());
        return row;
    }

    static class wordViewHolder{
        TextView title;
        TextView definition;
    }

}
