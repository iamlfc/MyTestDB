package com.lfc.mysql

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_enter_door.*

/*
   *@Author LFC
   *@Date 2020-2-6 23:16:57
   *@Remarks:
   */
class EnterDoor : AppCompatActivity() {
//  private var StrValue = ""
    //  private var Type = 0

    companion object {
        fun EnterThis(context: Context, string: String = "", int: Int = 0) {
            var intent = Intent(context, EnterDoor().javaClass)
            intent.putExtra("StrID", string)
            intent.putExtra("Type", int)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enter_door)
        //title = intent!!.getStringExtra("StrID")
        // Type = intent!!.getIntExtra("Type", 0)
        initView()
        //initData()
        getData()
    }

    private fun initView() {
        btn_baseTest.setOnClickListener { MainActivity.EnterThis(this) }
        btn_list_test.setOnClickListener { ListJavaBeanTestA.EnterThis(this) }
        btn_oneToMany.setOnClickListener { OneToMoreA.EnterThis(this) }
    }

    private fun getData() {

    }
}
