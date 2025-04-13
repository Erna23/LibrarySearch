package com.example.search1

import android.app.TabActivity
import android.os.Bundle
import android.speech.SpeechRecognizer
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SearchView
import android.widget.Toast
import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.search1.databinding.ActivityMainBinding

@Suppress("deprecation")
class SecondActivity : TabActivity() {

    lateinit var BooksLV: ListView
    lateinit var listAdapter: ArrayAdapter<String>
    lateinit var BooksList: ArrayList<String>;
    lateinit var searchView: SearchView
    lateinit var btnSpeech: ImageButton
    lateinit var dlgView: View
    lateinit var tvText: TextView
    lateinit var txt: String

    private lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second)

        var tabHost = this.tabHost

        var tabHome = tabHost.newTabSpec("HOME").setIndicator("메인페이지")
        tabHome.setContent(R.id.tabHome)
        tabHost.addTab(tabHome)

        var tabHotBook = tabHost.newTabSpec("HOT").setIndicator("인기도서")
        tabHotBook.setContent(R.id.tabHotBook)
        tabHost.addTab(tabHotBook)

        var tabNewBook = tabHost.newTabSpec("NEW").setIndicator("새로 등록된 도서")
        tabNewBook.setContent(R.id.tabNewBook)
        tabHost.addTab(tabNewBook)

        tabHost.currentTab = 0

        BooksLV = findViewById(R.id.idLVBooks)
        searchView = findViewById(R.id.idSV)

        BooksList = ArrayList()
        BooksList.add("개인적 기억")
        BooksList.add("그리고 봄")
        BooksList.add("내가 글을 쓰는 이유")
        BooksList.add("다져 가는 삶")
        BooksList.add("디어 마이 버디")
        BooksList.add("모델")
        BooksList.add("문제가 아니라 사람에 주목하라")
        BooksList.add("바둑 수읽기의 비결")
        BooksList.add("발끝이 바다에 닿으면")
        BooksList.add("빌려드립니다")
        BooksList.add("사랑이 제곱이 되었다")
        BooksList.add("센티언스 : 의식의 발명")
        BooksList.add("신곡")
        BooksList.add("앨리스 앤솔로지")
        BooksList.add("엄마의 아트 레시피")
        BooksList.add("오늘은 메타버스")
        BooksList.add("작은 미덕들")
        BooksList.add("죽어나간 시간을 위한 애도")
        BooksList.add("책과 도서관 : 불멸의 기호학")
        BooksList.add("프랑스어식 사고법")

        listAdapter = ArrayAdapter<String>(
            this, android.R.layout.simple_list_item_1, BooksList
        )

        BooksLV.adapter = listAdapter

        val inflater=LayoutInflater.from(this)
        val dialogView=inflater.inflate(R.layout.custom_dialog,null)

        BooksLV.setOnItemClickListener{parent,view,position,id->
            val textView1: TextView = dialogView.findViewById(R.id.textView1)
            val textView2: TextView = dialogView.findViewById(R.id.textView2)
            val textView3: TextView = dialogView.findViewById(R.id.textView3)
            val textView4: TextView = dialogView.findViewById(R.id.textView4)
            val textView5: TextView = dialogView.findViewById(R.id.textView5)
            val textView6: TextView = dialogView.findViewById(R.id.textView6)

            val selectedItem=parent.getItemAtPosition(position) as String


            if (position==12){

                textView1.text=textView1.text.toString()+"$(position+1)"
                textView2.text=textView2.text.toString()+"$selectedItem"
                textView3.text=textView3.text.toString()+"구름출판사"
                textView4.text=textView4.text.toString()+"90000"
                textView5.text=textView5.text.toString()+"단테"
                textView6.text=textView6.text.toString()+"1987"
            }
            else if (position==1){
                val inflater=LayoutInflater.from(this)
                val dialogView=inflater.inflate(R.layout.custom_dialog,null)

                textView1.text=textView1.text.toString()+"$(position+1)"
                textView2.text=textView2.text.toString()+"$selectedItem"
                textView3.text=textView3.text.toString()+"한겨레출판사"
                textView4.text=textView4.text.toString()+"15120"
                textView5.text=textView5.text.toString()+"조선희"
                textView6.text=textView6.text.toString()+"2023"
            }
            else if (position==8){
                val inflater=LayoutInflater.from(this)
                val dialogView=inflater.inflate(R.layout.custom_dialog,null)

                textView1.text=textView1.text.toString()+"$(position+1)"
                textView2.text=textView2.text.toString()+"$selectedItem"
                textView3.text=textView3.text.toString()+"황금가지"
                textView4.text=textView4.text.toString()+"15300"
                textView5.text=textView5.text.toString()+"하승민"
                textView6.text=textView6.text.toString()+"2023"
            }
            else if (position==19){
                val inflater= LayoutInflater.from(this)
                val dialogView=inflater.inflate(R.layout.custom_dialog,null)

                textView1.text=textView1.text.toString()+"$(position+1)"
                textView2.text=textView2.text.toString()+"$selectedItem"
                textView3.text=textView3.text.toString()+"도서출판 씨엘"
                textView4.text=textView4.text.toString()+"19800"
                textView5.text=textView5.text.toString()+"박만규"
                textView6.text=textView6.text.toString()+"2023"
            }

            val dialog=AlertDialog.Builder(this)
            dialog.setTitle("선택한 항목")
            dialog.setView(dialogView)
            dialog.setPositiveButton("확인",null)
            dialog.create()
            dialog.show()
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (BooksList.contains(query)) {
                    listAdapter.filter.filter(query)
                } else {
                    Toast.makeText(this@SecondActivity, "No Language found..", Toast.LENGTH_LONG)
                        .show()
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                listAdapter.filter.filter(newText)
                return false
            }
        })

        btnSpeech.setOnClickListener{
            var intent=Intent(applicationContext,SttActivity::class.java)
            startActivity(intent)
        }

    }
}
