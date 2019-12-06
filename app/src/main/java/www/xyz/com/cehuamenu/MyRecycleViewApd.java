package www.xyz.com.cehuamenu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 我 on 2019/8/27.
 */

public class MyRecycleViewApd extends RecyclerView.Adapter<MyRecycleViewApd.MyViewHolder> {


    private Context context;
    private List<String> list;
    private View inflater;
    //构造方法，传入数据
    public MyRecycleViewApd(Context context, List<String> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder myViewHolder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_main,parent,false));
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.textView.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);
             textView = (TextView) itemView.findViewById(R.id.tv_item);
        }

    }
}
