package com.wilson.assignment

import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

class QuizActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quiz)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val pager = findViewById<ViewPager2>(R.id.quizPager)
        val prevQuiz = findViewById<ImageButton>(R.id.prevQuiz)
        val nextQuiz = findViewById<ImageButton>(R.id.nextQuiz)
        val quizPages = arrayListOf<Fragment>()

        for (i in 0..9) {
            quizPages.add(QuizPage.newInstance(i))
        }

        pager.adapter = object: FragmentStateAdapter(this) {
            override fun getItemCount() = 10
            override fun createFragment(position: Int) = quizPages[position]
        }

        pager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                prevQuiz.isEnabled = position > 0
                nextQuiz.isEnabled = position + 1 < pager.adapter!!.itemCount
            }
        })

        prevQuiz.setOnClickListener { pager.currentItem -= 1 }
        nextQuiz.setOnClickListener { pager.currentItem += 1 }
    }
}