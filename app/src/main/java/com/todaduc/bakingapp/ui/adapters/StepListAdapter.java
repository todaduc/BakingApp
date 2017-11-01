package com.todaduc.bakingapp.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.todaduc.bakingapp.R;
import com.todaduc.bakingapp.entities.BakingStep;
import java.util.List;

/**
 * Created by ddjankou on 8/29/2017.
 */

public class StepListAdapter  extends BaseAdapter {

    private Context context;
    private List<BakingStep> bakingStepList;

    public StepListAdapter(Context context, List<BakingStep> bakingStepList) {
        this.context = context;
        this.bakingStepList = bakingStepList;
    }

    @Override
    public int getCount() {
        return bakingStepList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);

            convertView = inflater.inflate(R.layout.fragment_step, parent, false);
        }
        ((TextView)convertView.findViewById(R.id.tv_step_description)).setText(bakingStepList.get(position).getDescription());
        // Set the image resource and return the newly created ImageView
        return convertView;
    }
}
