package www.xyz.com.cehuamenu;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.yanzhenjie.recyclerview.OnItemMenuClickListener;
import com.yanzhenjie.recyclerview.SwipeMenu;
import com.yanzhenjie.recyclerview.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.SwipeMenuItem;
import com.yanzhenjie.recyclerview.SwipeRecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends Activity {
    private SlidingMenu slidingMenu;
    ArrayList<String> mStrings = new ArrayList<>();
    private SwipeRecyclerView src_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initSlidingMenu();
        initSwipRecycleView();
    }

    private void initData() {
        mStrings.add("119ooouuuuuuooookggggggggkkkkkooo1");
        mStrings.add("10021");
        mStrings.add("19991");
        mStrings.add("141");
    }

    private void initSwipRecycleView() {
        src_main = (SwipeRecyclerView)findViewById(R.id.src_main);

        final MyRecycleViewApd myRecycleViewApd = new MyRecycleViewApd(this, mStrings);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        //RecyclerView.LayoutManager manager = new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
        //GridLayoutManager manager1 = new GridLayoutManager(context,2);
        //manager1.setOrientation(GridLayoutManager.VERTICAL);
        //StaggeredGridLayoutManager manager2 = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        src_main.setLayoutManager(manager);

        src_main.setSwipeMenuCreator(new SwipeMenuCreator() {
            @Override
            public void onCreateMenu(SwipeMenu leftMenu, SwipeMenu rightMenu, int position) {
                SwipeMenuItem deleteItem = new SwipeMenuItem(MainActivity.this);
                deleteItem.setText("删除");
                deleteItem.setBackgroundColor(Color.RED);
                deleteItem.setWidth(150);
                deleteItem.setHeight(UIUtils.dp2px(48));
                rightMenu.addMenuItem(deleteItem); // 在Item左侧添加一个菜单。

                SwipeMenuItem zhiding = new SwipeMenuItem(MainActivity.this);
                zhiding.setText("置顶");
                zhiding.setBackgroundColor(Color.BLUE);
                zhiding.setWidth(150);
                zhiding.setHeight(UIUtils.dp2px(48));
                rightMenu.addMenuItem(zhiding); // 在Item左侧添加一个菜单。
            }
        });
        src_main.setOnItemMenuClickListener(new OnItemMenuClickListener() {
            @Override
            public void onItemClick(SwipeMenuBridge menuBridge, int adapterPosition) {
                // 任何操作必须先关闭菜单，否则可能出现Item菜单打开状态错乱。
                menuBridge.closeMenu();

                // 左侧还是右侧菜单：
                int direction = menuBridge.getDirection();
                // 菜单在Item中的Position：
                int menuPosition = menuBridge.getPosition();
                if(menuPosition == 0){
                    mStrings.remove(adapterPosition);
                    myRecycleViewApd.notifyItemRemoved(adapterPosition);
                }
                if(menuPosition == 1){
                    mStrings.add(0,mStrings.get(adapterPosition));
                    mStrings.remove(adapterPosition+1);
                    myRecycleViewApd.notifyDataSetChanged();
                }
            }
        });
        src_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
//        src_main.setLongPressDragEnabled(true); // 拖拽排序，默认关闭。
//        src_main.setItemViewSwipeEnabled(true); // 侧滑删除，默认关闭。
        src_main.setSwipeItemMenuEnabled(true);
        src_main.setAdapter(myRecycleViewApd);
    }

    private void initSlidingMenu() {

        slidingMenu = new SlidingMenu(this);
        slidingMenu.setMode(SlidingMenu.LEFT);
        slidingMenu.setBehindWidth(150);
        slidingMenu.setFadeDegree(0.35f);

        View view =  View.inflate(this,R.layout.left_fragment,null);
        ListView listView = (ListView) view.findViewById(R.id.lv_left);
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(MainActivity.this,R.layout.tv_left,mStrings);
        listView.setAdapter(stringArrayAdapter);
        slidingMenu.setMenu(view);
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);

    }


}
