//https://github.com/codeWithCal/TicTacToeAndroid
//Šis projekts tika ņemts par piemēru, vēlāk pārtaisīts, pārveidots un modificēts līdz galām.



package com.example.tictactoe
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.example.tictactoe.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity()
{
    enum class Gajiens
    {
        NULLE,
        KRUSTINS
    }

    private var pirmais= Gajiens.KRUSTINS
    private var tekosais= Gajiens.KRUSTINS


    private var krustinuskaits=0
    private var nullesskaits =0



    private var saraksts = mutableListOf<Button>()

    private lateinit var binding: ActivityMainBinding




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pilnaissaratsts()
    }

    private fun pilnaissaratsts() {
        saraksts.add(binding.line11)
        saraksts.add(binding.line12)
        saraksts.add(binding.line13)
        saraksts.add(binding.line21)
        saraksts.add(binding.line22)
        saraksts.add(binding.line23)
        saraksts.add(binding.line31)
        saraksts.add(binding.line32)
        saraksts.add(binding.line33)

    }

    fun boardTapped(view: View) {

        if(view !is Button)
            return
        addToBoard(view)

        if(uzvara(NULLE)){
            nullesskaits++
            result("Nulles uzvarēja!")
        }
        if(uzvara(KRUSTINS)){
            krustinuskaits++
            result("Krustiņi uzvarēja!")
        }

        if(fullBoard()) {
            if (!uzvara(NULLE) && !uzvara(KRUSTINS)) {
                result("Neizšķirts!")
            }
        }

    }


    private fun uzvara(i: String): Boolean {

        //horizontāla

        if(match(binding.line11,i)&& match(binding.line12,i)&&match(binding.line13,i))
            return true
        if(match(binding.line21,i)&& match(binding.line22,i)&&match(binding.line23,i))
            return true
        if(match(binding.line31,i)&& match(binding.line32,i)&&match(binding.line33,i))
            return true

        //vertikāla
        if(match(binding.line11,i)&& match(binding.line21,i)&&match(binding.line31,i))
            return true
        if(match(binding.line12,i)&& match(binding.line22,i)&&match(binding.line32,i))
            return true
        if(match(binding.line13,i)&& match(binding.line23,i)&&match(binding.line33,i))
            return true

        //diagonāle
        if(match(binding.line11,i)&& match(binding.line22,i)&&match(binding.line33,i))
            return true
        if(match(binding.line13,i)&& match(binding.line22,i)&&match(binding.line31,i))
            return true


        return false

    }


    private fun match(button: Button,symbol: String): Boolean=button.text==symbol

    private fun result(title: String) {

        val message ="\nNulles: $nullesskaits\n\nKrustiņi: $krustinuskaits"
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Atjaunot spēli")
            { _,_->
                resetBoard()
            }
            .setCancelable(false)
            .show()



    }

    private fun resetBoard() {
        for(button in saraksts){
            button.text=""
        }

        if (pirmais==Gajiens.NULLE)
            pirmais= Gajiens.KRUSTINS
        else if (pirmais==Gajiens.KRUSTINS)
            pirmais= Gajiens.NULLE

        tekosais=pirmais
        setTurnLabel()

    }

    private fun fullBoard(): Boolean {
        for(button in saraksts){
            if(button.text=="")
                return false
        }
        return true

    }

    private fun addToBoard(button: Button) {

        if(button.text !="")
            return

        if(tekosais==Gajiens.NULLE){
            button.text= NULLE
            tekosais = Gajiens.KRUSTINS
        }
        else if(tekosais==Gajiens.KRUSTINS){
            button.text= KRUSTINS
            tekosais = Gajiens.NULLE
        }

        setTurnLabel()

    }

    private fun setTurnLabel() {
        var turnText= ""
        if(tekosais== Gajiens.KRUSTINS)
            turnText= "Spēlē $KRUSTINS"
        else if(tekosais== Gajiens.NULLE)
            turnText= "Spēlē $NULLE"

        binding.turnTV.text= turnText

    }

    companion object{
        const val NULLE = "0"
        const val KRUSTINS ="X"

    }

}