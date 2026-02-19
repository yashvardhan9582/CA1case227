package com.example.ca1case227


import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference

    private lateinit var idET: EditText
    private lateinit var nameET: EditText


    private lateinit var btnCreate: Button
    private lateinit var btnRead: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = FirebaseDatabase.getInstance().getReference("Users")

        idET = findViewById(R.id.idET)
        nameET = findViewById(R.id.nameET)

        btnCreate = findViewById(R.id.btnCreate)
        btnRead= findViewById(R.id.btnRead)


        btnCreate.setOnClickListener { createUser() }
        btnRead.setOnClickListener { readUser() }

    }

    private fun createUser() {
        val  id = idET.text.toString()
        val  name = nameET.text.toString()


        val  user = User(id,name)

        database.child(id).setValue(user)
            .addOnSuccessListener {
                Toast.makeText(this, "Book Added", Toast.LENGTH_SHORT).show()
                clearFields()
            }
    }

    private fun readUser() {
        val id = idET.text.toString()

        database.child(id).get()
            .addOnSuccessListener {
                if (it.exists()) {
                    val user = it.getValue(User::class.java)
                    nameET.setText(user?.name)
                    Toast.makeText(this, "Book Found", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Book Not Found", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun clearFields() {
        idET.text.clear()
        nameET.text.clear()
         }
}
