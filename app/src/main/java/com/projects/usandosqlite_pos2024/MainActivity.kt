package com.projects.usandosqlite_pos2024

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.projects.usandosqlite_pos2024.adapter.MeuAdapter
import com.projects.usandosqlite_pos2024.entity.Cadastro
import com.projects.usandosqlite_pos2024.database.DatabaseHandler
import com.projects.usandosqlite_pos2024.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var banco: DatabaseHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setButtonListener()

        binding.etCod.setEnabled(false)

        if(intent.getIntExtra("cod", 0) != 0){
            binding.etCod.setText(intent.getIntExtra("cod", 0).toString())
            binding.etNome.setText(intent.getStringExtra("nome"))
            binding.etTelefone.setText(intent.getStringExtra("telefone"))
        }

        banco = DatabaseHandler(this)
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

    private fun objetoCadastro() : Cadastro {
        var cadastro = Cadastro(
            _id = 0,
            nome = "",
            telefone = ""
        )
        if (!binding.etCod.text.toString().isEmpty()){
            cadastro._id = binding.etCod.text.toString().toInt()
            cadastro.nome = binding.etNome.text.toString()
            cadastro.telefone = binding.etTelefone.text.toString()
        }else{
            cadastro.nome = binding.etNome.text.toString()
            cadastro.telefone = binding.etTelefone.text.toString()
        }


        return cadastro
    }

    private fun btPesquisarOnClick() {
        val builder = AlertDialog.Builder(this)

        val etCodPesquisar = EditText(this)

        builder.setTitle("Digite o código da pesquisa")
        builder.setView(etCodPesquisar)
        builder.setCancelable(false)
        builder.setNegativeButton("Fechar", null)
        builder.setPositiveButton("Pesquisar", DialogInterface.OnClickListener{dialogInterface, i ->
            System.out.println("fazer a pesquisa")
            })

        builder.show()
    }

    private fun btExcluirOnClick() {
        banco.delete(binding.etCod.text.toString().toInt())
        Toast.makeText(this, "Sucesso ao deletar registro", Toast.LENGTH_LONG).show()
        finish()
    }

    private fun btSalvarOnClick() {
        if (binding.etCod.text.isEmpty()){
            banco.insert(objetoCadastro())
            Toast.makeText(this, "Sucesso ao salvar registro", Toast.LENGTH_LONG).show()
        }else{
            banco.update(objetoCadastro())
            Toast.makeText(this, "Sucesso ao alterar registro", Toast.LENGTH_LONG).show()
        }
        finish()
    }

    override fun onStart() {
        super.onStart()

        val registros = banco.list();

        //val adapter = MeuAdapter(this, registros)

    }
}