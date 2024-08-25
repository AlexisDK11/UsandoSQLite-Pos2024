package com.projects.usandosqlite_pos2024

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.projects.usandosqlite_pos2024.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var banco: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setButtonListener()

        banco = SQLiteDatabase.openOrCreateDatabase(
            this.getDatabasePath("dbfile.sqlite"),
            null
        )

        banco.execSQL("CREATE TABLE IF NOT EXISTS cadastro (_id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, telefone TEXT )")
    }

    private fun setButtonListener() {
        binding.btSalvar.setOnClickListener{
            btSalvarOnClick()
        }

        binding.btExcluir.setOnClickListener{
            btExcluirOnClick()
        }
        binding.btPesquisar.setOnClickListener{
            btPesquisarOnClick()
        }
    }

    private fun btPesquisarOnClick() {
        TODO("Not yet implemented")
    }

    private fun btExcluirOnClick() {
        TODO("Not yet implemented")
    }

    private fun btSalvarOnClick() {
        TODO("Not yet implemented")
    }
}