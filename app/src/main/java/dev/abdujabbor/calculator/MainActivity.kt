package dev.abdujabbor.calculator

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible

import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    var list = ArrayList<String>()
    var hasNatija = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_0.setOnClickListener(View.OnClickListener {
            if (tv_sum.text != "0") {
                tv_sum.text = "${tv_sum.text}0"
            }

        })

        tv_1.setOnClickListener {
            raqamYozish(1)

        }

        tv_2.setOnClickListener {
            raqamYozish(2)

        }

        tv_3.setOnClickListener {
            raqamYozish(3)

        }

        tv_4.setOnClickListener {
            raqamYozish(4)
        }

        tv_5.setOnClickListener {
            raqamYozish(5)

        }

        tv_6.setOnClickListener {
            raqamYozish(6)

        }

        tv_7.setOnClickListener {
            raqamYozish(7)

        }

        tv_8.setOnClickListener {
            raqamYozish(8)

        }

        tv_9.setOnClickListener {
            raqamYozish(9)

        }

        tv_nuqta.setOnClickListener {
            val misol = tv_sum.text.toString()
            var amalIndex = -1
            for (i in misol.indices) {
                if (misol[i] == '+' || misol[i] == '-' || misol[i] == '*' || misol[i] == '/') {
                    amalIndex = i
                }
            }
            if (amalIndex == -1) {
                if (!tv_sum.text.toString().contains('.')) {
                    tv_sum.text = "${tv_sum.text}."
                }
            } else {
                val ekranLength = tv_sum.text.length
                val matn = tv_sum.text.toString().subSequence(amalIndex, ekranLength)
                if (!matn.contains('.')) {
                    tv_sum.text = "${tv_sum.text}."
                }
            }
        }

        tv_clear.setOnClickListener {
            tv_sum.text = "0"
            hasNatija = false

        }

        tv_delete.setOnClickListener {
            val a = tv_sum.text
            if (a.length == 1 || a == "-") {
                tv_sum.text = "0"
            } else
                tv_sum.text = a.subSequence(0, a.length - 1)
        }


        tv_plus.setOnClickListener {
            amalYozish("+")
        }

        tv_minus.setOnClickListener {
            amalYozish("-")
        }

        tv_multiple.setOnClickListener {
            amalYozish("*")
        }

        tv_division.setOnClickListener {
            amalYozish("/")
        }

        tv_equal.setOnClickListener {
            if (!hasNatija) hisoblash()
            list.add(tv_sum.text.toString())
            misol1.text = list[0]
            if (list.size>1)
                misol2.text = list[1]
            if (list.size>2)
                misol3.text = list[2]
            if (list.size>3)
                misol4.text = list[3]

            if (list.size>4)
                misol5.text = list[4]

            if (list.size>5){
                list.clear()
            }
            val a = tv_sum.text.toString().substring(tv_sum.text.toString().indexOf('=')+1,tv_sum.text.length)
            val b = tv_sum.text.toString().substring(0,tv_sum.text.toString().indexOf('=')+1)
            tv_sum2.text = b
            tv_sum.text = a
//            if (misol1.text.isNotEmpty()){
//                if (misol2.text.isNotEmpty()){
//
//                    if (misol3.text.isNotEmpty()){
//
//                        if (misol4.text.isNotEmpty()){
//
//                            if (misol5.text.isEmpty()){
//                                tv_sum.text =misol5.text
//                            }
//
//                        }else{
//                            tv_sum.text =misol4.text
//                        }
//
//                    }else{
//                        tv_sum.text =misol3.text
//                    }
//
//                }else{
//                    tv_sum.text =misol2.text
//                }
//            }else{
//                tv_sum.text =misol1.text
//            }



        }

        tv_percent.setOnClickListener{
            if (linear1.isGone ==false){

                linear1.isGone =true
            }else if (  linear1.isGone == true){
                linear1.isGone =false
            }
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.my_optionn__menus,menu)
        return super.onCreateOptionsMenu(menu)
    }


    fun raqamYozish(raqam: Int) {

        if (hasNatija) {
            tv_sum.text = "$raqam"
            hasNatija = false
        } else {

            val r = raqam.toString()
            if (tv_sum.text == "0") {
                tv_sum.text = r
            } else {
                tv_sum.text = "${tv_sum.text}$r"
            }
        }
    }

    fun amalYozish(amalArg: String) {
        if (hasNatija) {
            val misol = tv_sum.text.toString()
            for (i in misol.indices) {
                if (misol[i] == '=') {
                    tv_sum.text = "${misol.subSequence(i + 1, misol.length)}$amalArg"
                    break
                }
            }
            hasNatija = false
        } else {
            tv_sum.text = "${tv_sum.text}$amalArg"
        }
    }


    fun hisoblash() {

        var sonlar = ArrayList<Double>()
        var amallar = ArrayList<Int>()

        val misol = tv_sum.text.toString()

        var index1 = 0
        for (i in misol.indices) {
            if (amallar.isEmpty()) {
                when (misol[i]) {
                    '+' -> {
                        sonlar.add(misol.subSequence(index1, i).toString().toDouble())
                        amallar.add(0)
                        index1 = i
                    }
                    '-' -> {
                        sonlar.add(misol.subSequence(index1, i).toString().toDouble())
                        amallar.add(1)
                        index1 = i
                    }
                    '*' -> {
                        sonlar.add(misol.subSequence(index1, i).toString().toDouble())
                        amallar.add(2)
                        index1 = i
                    }
                    '/' -> {
                        sonlar.add(misol.subSequence(index1, i).toString().toDouble())
                        amallar.add(3)
                        index1 = i
                    }
                }
            } else {
                when (misol[i]) {
                    '+' -> {
                        sonlar.add(misol.subSequence(index1 + 1, i).toString().toDouble())
                        amallar.add(0)
                        index1 = i
                    }
                    '-' -> {
                        sonlar.add(misol.subSequence(index1 + 1, i).toString().toDouble())
                        amallar.add(1)
                        index1 = i
                    }
                    '*' -> {
                        sonlar.add(misol.subSequence(index1 + 1, i).toString().toDouble())
                        amallar.add(2)
                        index1 = i
                    }
                    '/' -> {
                        sonlar.add(misol.subSequence(index1 + 1, i).toString().toDouble())
                        amallar.add(3)
                        index1 = i
                    }
                }
            }
        }

        sonlar.add(misol.subSequence(index1 + 1, misol.length).toString().toDouble())

        var count = 0
        var natija = sonlar.first()
        while (count < amallar.size) {

            when (amallar[count]) {
                0 -> {
                    natija += sonlar[count + 1]
                }
                1 -> {
                    natija -= sonlar[count + 1]
                }
                2 -> {
                    natija *= sonlar[count + 1]
                }
                3 -> {
                    natija /= sonlar[count + 1]
                }
            }

            count++
        }

        val intNatija: Int = natija.toInt()
        if (natija / intNatija == 1.0) {

            tv_sum.text = "${tv_sum.text}= $intNatija"
        } else {
            tv_sum.text = "${tv_sum.text}= $natija"
        }
        hasNatija = true
    }
}

