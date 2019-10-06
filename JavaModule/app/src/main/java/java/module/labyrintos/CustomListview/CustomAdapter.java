package java.module.labyrintos.CustomListview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.module.labyrintos.R;
import java.util.ArrayList;
/**
 * Created by Labyrintos on 2019-10-04
 */
public class CustomAdapter extends BaseAdapter {

    private ArrayList<CustomItem> listCustom = new ArrayList<>();
    private Context context;

    //초기 데이터 주고받기
    public CustomAdapter() { }

    //초기 데이터 주고받기
    public CustomAdapter(ArrayList<CustomItem> listCustom) {
        this.listCustom = listCustom;
    }

    // 추후에 콘텍스트 사용할일이 있을땐 이렇게 사용, 뭐가 더 메모리 적은지 확인할 필요 있음
    public CustomAdapter(Context context, ArrayList<CustomItem> listCustom) {
        this.context = context;
        this.listCustom = listCustom;
    }

    @Override
    public int getCount() {
        return listCustom.size();
    }

    @Override
    public Object getItem(int position) {
        return listCustom.get(position);
    }

    // Item의 id : Item을 구별하기 위한 것으로 position 사용
    @Override
    public long getItemId(int position) {
        return position;
    }

    // 실제로 Item이 보여지는 부분
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.customlistview_item, null, false);

            holder = new ViewHolder();
            holder.imageView =  convertView.findViewById(R.id.imageView);
            holder.textTitle = convertView.findViewById(R.id.text_title);
            holder.textContent = convertView.findViewById(R.id.text_content);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        CustomItem item = listCustom.get(position);
        Glide.with(parent.getContext()).load(item.getImage()).into(holder.imageView);
        holder.textTitle.setText(item.getTitle());
        holder.textContent.setText(item.getContent());

        return convertView;
    }

    //뷰 홀더 패턴
    class ViewHolder {
        ImageView imageView;
        TextView textTitle;
        TextView textContent;
    }

    // Activity에서 Adapter에있는 ArrayList에 data를 추가
    public void addItem(CustomItem item) {
        listCustom.add(item);
    }
}