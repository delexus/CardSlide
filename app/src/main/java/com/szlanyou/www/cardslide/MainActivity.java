package com.szlanyou.www.cardslide;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.ParcelUuid;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CardRecyclerView recyclerView = (CardRecyclerView) findViewById(R.id.id_recycler_view);
        StringAdapter adapter = new StringAdapter(this);
        CardLayoutManager llm = new CardLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);
    }

    private static class StringAdapter extends RecyclerView.Adapter {

        private LayoutInflater inflater;
        private int ARRAY_COLORS[] = {Color.GREEN, Color.BLUE, Color.RED, Color.LTGRAY};
        private Random random = new Random();

        StringAdapter(Context context) {
            inflater = LayoutInflater.from(context);
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(inflater.inflate(R.layout.item_view, parent, false));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ViewHolder viewHolder = (ViewHolder) holder;
            viewHolder.textView.setText("hello");
            viewHolder.textView.setBackgroundColor(ARRAY_COLORS[random.nextInt(4)]);
        }

        @Override
        public int getItemCount() {
            return 10;
        }
    }

    private static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_item);
        }
    }
}
