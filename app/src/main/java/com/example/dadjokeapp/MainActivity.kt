package com.example.dadjokeapp

import android.os.Bundle
import android.view.ContextThemeWrapper
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    private lateinit var getJokesButton: Button;
    private lateinit var jokesTableLayout: TableLayout;
    lateinit var jokesCollection: List<String>;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.main_activity)

        getJokesButton = findViewById<Button>(R.id.getJokesButton)
        jokesTableLayout = findViewById<TableLayout>(R.id.jokeTableLayout)

        getJokesButton.setOnClickListener { setJokes() }

        val inputStream = this.resources.openRawResource(R.raw.dad_a_base)
        jokesCollection = inputStream.bufferedReader().readLines().toList()

        setJokes()
    }

    fun setJokes() {
        //add uniqueness to this rng
        jokesTableLayout.removeAllViews()
        for(i in 1..10){
            addJokeToList(jokesCollection[Random.nextInt(0, jokesCollection.size)])
        }
    }

    fun addJokeToList(joke: String) {
        val styleTextViewContext = ContextThemeWrapper(this, R.style.JokeTextViewStyle)
        val styleTableRowContext = ContextThemeWrapper(this, R.style.JokeTableRow)
        var jokeRow: TableRow = TableRow(styleTableRowContext);
        var textView: TextView = TextView(styleTextViewContext);


        textView.text = joke

        jokeRow.addView(textView);

        jokesTableLayout.addView(jokeRow)
    }
}