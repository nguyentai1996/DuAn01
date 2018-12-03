
package com.example.admin.qlks;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.admin.qlks.database.HoaDonChiTietDAO;

import com.example.admin.qlks.database.HoaDonChiTietDAO;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;


public class MainMainActivity extends AppCompatActivity implements OnChartValueSelectedListener {

    static HoaDonChiTietDAO hoaDonChiTietDAO;
    private CombinedChart mChart;
    private ArrayList<String> PieEntryLabels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmain);

        mChart = (CombinedChart) findViewById(R.id.combinedChart);
        mChart.getDescription().setEnabled(false);
        mChart.setBackgroundColor(Color.WHITE);
        mChart.setDrawGridBackground(false);
        mChart.setDrawBarShadow(false);
        mChart.setHighlightFullBarEnabled(false);
        mChart.setOnChartValueSelectedListener(MainMainActivity.this);

        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setAxisMinimum(0f);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setAxisMinimum(0f);

        final List<String> xLabel = new ArrayList<>();
        xLabel.add("Jan");
        xLabel.add("Feb");
        xLabel.add("Mar");
        xLabel.add("Apr");
        xLabel.add("May");
        xLabel.add("Jun");
        xLabel.add("Jul");
        xLabel.add("Aug");
        xLabel.add("Sep");
        xLabel.add("Oct");
        xLabel.add("Nov");
        xLabel.add("Dec");


        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return xLabel.get((int) value % xLabel.size());
            }
        });

        CombinedData data = new CombinedData();
        LineData lineDatas = new LineData();
        lineDatas.addDataSet((ILineDataSet) dataChart());

        data.setData(lineDatas);

        xAxis.setAxisMaximum(data.getXMax() + 0.25f);

        mChart.setData(data);
        mChart.invalidate();
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }


    @Override
    public void onNothingSelected() {

    }

    private DataSet dataChart() {
        hoaDonChiTietDAO = new HoaDonChiTietDAO(this);

        LineData d = new LineData();





    ArrayList<Entry> entries = new ArrayList<Entry>();


        entries.add(new Entry(0, (float) hoaDonChiTietDAO.getDoanhThuThang1()));
        entries.add(new Entry(1, (float) hoaDonChiTietDAO.getDoanhThuThangHai()));
        entries.add(new Entry(2,(float) hoaDonChiTietDAO.getDoanhThuThangBa()));
        entries.add(new Entry(3,(float) hoaDonChiTietDAO.getDoanhThuThangTu() ));
        entries.add(new Entry(4,(float) hoaDonChiTietDAO.getDoanhThuThangNam()));
        entries.add(new Entry(5,(float) hoaDonChiTietDAO.getDoanhThuThangSau()));
        entries.add(new Entry(6,(float) hoaDonChiTietDAO.getDoanhThuThangBay()));
        entries.add(new Entry(7,(float) hoaDonChiTietDAO.getDoanhThuThangTam()));
        entries.add(new Entry(8,(float) hoaDonChiTietDAO.getDoanhThuThang9()));
        entries.add(new Entry(9,(float) hoaDonChiTietDAO.getDoanhThuThang10()));
        entries.add(new Entry(10,(float) hoaDonChiTietDAO.getDoanhThuThang11()));
        entries.add(new Entry(11,(float) hoaDonChiTietDAO.getDoanhThuThang12()));


    LineDataSet set = new LineDataSet(entries, "Doanh số theo tháng");
        set.setColor(Color.GREEN);
        set.setLineWidth(2.5f);
        set.setCircleColor(Color.GREEN);
        set.setCircleRadius(5f);
        set.setFillColor(Color.GREEN);
        set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        set.setDrawValues(true);
        set.setValueTextSize(10f);
        set.setValueTextColor(Color.GREEN);

        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        d.addDataSet(set);

        return set;
}
}
