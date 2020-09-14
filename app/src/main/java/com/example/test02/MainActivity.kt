package com.example.test02

//未知のエリア
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.CombinedChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import kotlinx.android.synthetic.main.activity_main.*


//ここまで

class MainActivity : AppCompatActivity() {

    //    private var chart: CombinedChart? = null//追加
    
    // スタイルとフォントファミリーの設定
    private var mTypeface = Typeface.create(Typeface.SANS_SERIF, Typeface.NORMAL)
    // データの個数
    private val chartDataCount = 20

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //        chart = findViewById(R.id.chart1) //未知のエリア
        //val chart = bar_chart

        // グラフの設定
        setupLineChart()
        // データの設定
        lineChart.data = lineData(chartDataCount, 100f)
    }

    // LineChart用のデータ作成
    private fun lineData(count: Int, range: Float):LineData {

        val values = mutableListOf<Entry>()

        for (i in 0 until count) {
            // 値はランダムで表示させる
            if(i !in 11..15){
                val value = (Math.random() * (range)).toFloat()
                values.add(Entry(i.toFloat(), value))
            }
        }

        // グラフのレイアウトの設定
        val yVals = LineDataSet(values, "テストデータ").apply {
            axisDependency =  YAxis.AxisDependency.LEFT
            color = Color.BLACK
            // タップ時のハイライトカラー
            highLightColor = Color.YELLOW
            setDrawCircles(true)
            setDrawCircleHole(true)
            // 点の値非表示
            setDrawValues(false)
            // 線の太さ
            lineWidth = 2f
        }
        val data = LineData(yVals)
        return data
    }

    private fun setupLineChart(){
        lineChart.apply {
            description.isEnabled = false
            setTouchEnabled(true)
            isDragEnabled = true
            // 拡大縮小可能
            isScaleXEnabled = true
            setPinchZoom(false)
            setDrawGridBackground(false)

            //データラベルの表示
            legend.apply {
                form = Legend.LegendForm.LINE
                typeface = mTypeface
                textSize = 11f
                textColor = Color.BLACK
                verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
                horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
                orientation = Legend.LegendOrientation.HORIZONTAL
                setDrawInside(false)
            }

            //y軸右側の設定
            axisRight.isEnabled = false

            //未知のエリア
//            val xLabel: ArrayList<String> = ArrayList()
//            xLabel.add("9")
//            xLabel.add("15")
//            xLabel.add("21")
//            xLabel.add("27")
//            xLabel.add("33")

//            val xAxis: XAxis = chart.getXAxis()
//            xAxis.position = XAxis.XAxisPosition.BOTTOM
//            xAxis.setDrawGridLines(false)
//            xAxis.setValueFormatter(ValueFormatter { value, axis -> xLabel[value.toInt()] })
//            //
//            xAxis.valueFormatter = object : ValueFormatter() {
//                override fun getFormattedValue(value: Float): String {
//                    return months.get(value.toInt() % months.length)
//                }
//            }
            //ここまで

            //X軸表示
            xAxis.apply {
                typeface = mTypeface
                setDrawLabels(false)
                // 格子線を表示する
                setDrawGridLines(true)
            }

            //y軸左側の表示
            axisLeft.apply {
                typeface = mTypeface
                textColor = Color.BLACK
                // 格子線を表示する
                setDrawGridLines(true)
            }
        }
    }
}