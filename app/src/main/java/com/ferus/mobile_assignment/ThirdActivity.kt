package com.ferus.mobile_assignment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ThirdActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gmail)

        val emailList = listOf(
            Email("Edurila.com", "$19 Only (First 10 spots) - Bestselling Courses!", "12:34 PM", "E"),
            Email("Chris Abad", "Help make Campaign Monitor better", "11:22 AM", "C"),
            Email("Tuto.com", "8h de formation gratuite et les nouvelles compétences", "11:04 AM", "T"),
            Email("support", "Société Ovh : suivi de vos services - http://www.ovh.com", "10:26 AM", "S"),
            Email("Matt from Ionic", "The New Ionic Creator Is Here!", "9:30 AM", "M"),
            Email("LinkedIn", "New job recommendations for you", "8:45 AM", "L"),
            Email("Spotify", "Discover your personalized weekly mix", "8:15 AM", "S"),
            Email("Udemy", "Courses for you: Web Development, Design, and more", "7:55 AM", "U"),
            Email("Zoom", "Meeting reminder: Project discussion at 3 PM", "7:30 AM", "Z"),
            Email("PayPal", "Transaction completed: Your recent purchase", "6:50 AM", "P")
        )

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = EmailAdapter(emailList)
    }
}