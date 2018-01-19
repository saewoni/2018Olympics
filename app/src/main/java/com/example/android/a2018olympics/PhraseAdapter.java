package com.example.android.a2018olympics;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * PhraseAdapter class is used to design how to set texts for default_translation and
 * korean_translation inside each category
 */

public class PhraseAdapter extends ArrayAdapter<Phrase>{



    public PhraseAdapter(Context contextIn, List<Phrase> objectIn) {
        super(contextIn, 0, objectIn);
    }


    //Getting a view that displays data at the specified position (pos) in the data set.
    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.
                    from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Phrase currPhraseAdapter = getItem(pos);
        TextView defaultTrans = (TextView)listItemView.findViewById(R.id.default_translation);
        defaultTrans.setText(currPhraseAdapter.getDefaultTranslation());

        TextView koreanTrans = (TextView)listItemView.findViewById(R.id.korean_translation);
        koreanTrans.setText(currPhraseAdapter.getKoreanTranslation());


        return listItemView;


    }
}
