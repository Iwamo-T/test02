package com.example.test02

//未知のエリア
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import kotlinx.android.synthetic.main.activity_main.*


//ここまで

class MainActivity : AppCompatActivity() {

    
    // スタイルとフォントファミリーの設定
    private var mTypeface = Typeface.create(Typeface.SANS_SERIF, Typeface.NORMAL)
    // データの個数
    private val chartDataCount = 20

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

        //ラベル設定
        val labels = arrayOf(
            "1日", "2日", "3日", "4日", "5日", "6日", "7日", "8日", "9日", "10日",
            "11日", "12日", "13日", "14日", "15日", "16日", "17日", "18日", "19日", "20日",
        )

        val xAxis = lineChart.xAxis
        xAxis.valueFormatter = IndexAxisValueFormatter(labels)
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM)
        xAxis.setDrawGridLines(true)
        xAxis.setDrawLabels(true)
        xAxis.setTextSize(12F)
        //ここまで
    }
}