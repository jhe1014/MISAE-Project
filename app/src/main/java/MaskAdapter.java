import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.project.misae_project.R;

import java.util.List;

public class MaskAdapter extends BaseAdapter {

    private Context mask_context;
    private List<String> mask_list;
    private LayoutInflater mask_inflater;
    private ViewHolder viewHolder ;

    public MaskAdapter(List<String> list, Context context){
        mask_list = list;
        mask_context = context;
        mask_inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
       // return 0;
        return mask_list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if(convertView==null){
            convertView = mask_inflater.inflate(R.layout.activity_mask_listview,null);

            viewHolder = new ViewHolder();
            viewHolder.label = (TextView)convertView.findViewById(R.id.mask_ListView_TextView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.label.setText(mask_list.get(position));
        return  convertView;    }
    class ViewHolder{
        public  TextView label;
    }
}
