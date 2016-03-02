package gem.training3.enterprisenetwork.screen.fragment.statistic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

import butterknife.Bind;
import gem.training3.enterprisenetwork.R;
import gem.training3.enterprisenetwork.base.BaseFragment;

/**
 * Created by huylv on 01-Mar-16.
 */
public class StatisticFragment extends BaseFragment<StatisticPresenter> implements StatisticView {

    @Bind(R.id.table_purchased)
    TableLayout table_item_purchased;

    @Bind(R.id.fragment_statistic_pb)
    ProgressBar fragment_statistic_pb;

    @Bind(R.id.fragment_statistic_line_chart)
    LineChart fragment_statistic_line_chart;

    @Bind(R.id.chart_type_sp)
    Spinner chart_type_sp;

    @Bind(R.id.time_filter_sp)
    Spinner time_filter_sp;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initTable();

        chart_type_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        showProgress(fragment_statistic_pb, fragment_statistic_line_chart, table_item_purchased);
                        getPresenter().loadTablePurchased();
                        break;
                    case 1:
                        getPresenter().loadLineChart();
                        break;
                    case 2:
                        getPresenter().loadBarChart();
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(5f, 0));
        entries.add(new Entry(6f, 1));
        entries.add(new Entry(5f, 2));
        entries.add(new Entry(7f, 3));
        entries.add(new Entry(7f, 4));
        entries.add(new Entry(7f, 5));

        ArrayList<String> label = new ArrayList<>();
        label.add(new String("12/2/2012"));
        label.add(new String("13/2/2012"));
        label.add(new String("14/2/2012"));
        label.add(new String("15/2/2012"));
        label.add(new String("16/2/2012"));
        label.add(new String("17/2/2012"));

        LineDataSet lineDataSet = new LineDataSet(entries, "EEEE");
        fragment_statistic_line_chart.setData(new LineData(label, lineDataSet));
        fragment_statistic_line_chart.setPinchZoom(true);
        fragment_statistic_line_chart.setDoubleTapToZoomEnabled(true);
    }

    private void initTable() {
        TableRow tableFirstRow = (TableRow) LayoutInflater.from(getActivity()).inflate(R.layout.table_row_statistic, null);
        table_item_purchased.addView(tableFirstRow);

        for (int i = 0; i < 5; i++) {
            TableRow tableRow = (TableRow) LayoutInflater.from(getActivity()).inflate(R.layout.table_row_statistic, null);
            TextView tvNo = (TextView) tableRow.findViewById(R.id.table_c1);
            tvNo.setText(String.valueOf(i));
            table_item_purchased.addView(tableRow);
        }
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_statistic;
    }

    @Override
    public StatisticPresenter onCreatePresenter() {
        return new StatisticPresenterImpl(this);
    }

    @Override
    public void onLoadTableSuccess() {
        hideProgress(fragment_statistic_pb, table_item_purchased, fragment_statistic_line_chart);
    }

    @Override
    public void onLoadLineChartSuccess() {
        hideProgress(fragment_statistic_pb, fragment_statistic_line_chart, table_item_purchased);
    }

    @Override
    public void onLoadBarChartSuccess() {

    }
}
