package com.lfc.mysql

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.lfc.mysql.DBUtils.DatabaseContext
import com.lfc.mysql.DBUtils.MyDBUtils
import com.lfc.mysql.DataBeans.CardInfoD
import com.lfc.mysql.DataBeans.PersonInfoD
import com.lfc.mysql.greenDao.CardInfoDDao
import com.lfc.mysql.greenDao.PersonInfoDDao
import kotlinx.android.synthetic.main.activity_one_to_more.*
import kotlin.random.Random

/*
   *@Author LFC
   *@Date 2020-2-6 21:25:30
   *@Remarks: 一对多
   */
class OneToMoreA : AppCompatActivity() {
//  private var StrValue = ""
    //  private var Type = 0

    private var perDao: PersonInfoDDao? = null
    private var cardDao: CardInfoDDao? = null

    companion object {
        fun EnterThis(context: Context, string: String = "", int: Int = 0) {
            var intent = Intent(context, OneToMoreA().javaClass)
            intent.putExtra("StrID", string)
            intent.putExtra("Type", int)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one_to_more)
        //title = intent!!.getStringExtra("StrID")
        // Type = intent!!.getIntExtra("Type", 0)
        initView()
        initData()
        getData()
    }

    private fun initData() {
        perDao = MyDBUtils.getDaoSession(context = DatabaseContext(this))?.personInfoDDao
        cardDao = MyDBUtils.getDaoSession(context = DatabaseContext(this))?.cardInfoDDao
//        cardDao = MyApp().daoSession?.cardInfoDDao
    }

    private fun initView() {
        btn_add_otm.setOnClickListener {
            var list_cards = mutableListOf<CardInfoD>()
            for (index in 0..Random.nextInt(2) + 1) {
                var personD = PersonInfoD()
                personD.id = null
                personD.strName = "#$index 李昕柔"
                personD.strValue = "default"
                var lResult = perDao?.insertOrReplace(personD)
                if (lResult!! > 0.0) {
                    Log.d("--lfc", "添加成功")
                } else {
                    Toast.makeText(this, "插入失败 $lResult", Toast.LENGTH_SHORT).show()
                }
                for (t in 0..Random.nextInt(2) + 1) {
                    var cardD = CardInfoD()
                    cardD.id = null
                    cardD.pId = personD.id
                    cardD.d_Money = Random.nextDouble(1000.00)
                    cardD.personInfoD = personD
                    cardD.strFromName = "$index-$t"
                    list_cards.add(cardD)
                }

            }
            cardDao?.insertInTx(list_cards)

        }
        btn_search_otm.setOnClickListener {
            var sb = StringBuffer()
            var list_persons = perDao?.queryBuilder()?.list()
            var list_cards = cardDao?.queryBuilder()?.list()
            sb.append(" list_persons size: ${list_persons?.size}" + Gson().toJson(list_persons) + "\n")
            sb.append(" list_cards size: ${list_cards?.size}" + Gson().toJson(list_cards) + "\n")
            tv_log_otm.text = sb.toString()
        }
        btn_del_otm.setOnClickListener {
            cardDao?.deleteAll()
            perDao?.deleteAll()
        }

    }

    private fun getData() {

    }
}
