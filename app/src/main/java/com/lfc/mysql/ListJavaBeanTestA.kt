package com.lfc.mysql

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.lfc.mysql.DBUtils.DatabaseContext
import com.lfc.mysql.DBUtils.MySQLiteOpenHelper
import com.lfc.mysql.DataBeans.CustomerD
import com.lfc.mysql.DataBeans.OrdersD
import com.lfc.mysql.greenDao.DaoMaster
import com.lfc.mysql.greenDao.DaoSession
import com.lfc.mysql.utils.Const
import kotlinx.android.synthetic.main.activity_list_java_test.*
import java.util.*
import kotlin.random.Random

/*
   *@Author LFC
   *@Date 2020-2-5 21:13:42
   *@Remarks: 存储list测试
   */
class ListJavaBeanTestA : AppCompatActivity() {
//  private var StrValue = ""
    //  private var Type = 0

    companion object {
        fun EnterThis(context: Context, string: String = "", int: Int = 0) {
            var intent = Intent(context, ListJavaBeanTestA().javaClass)
            intent.putExtra("StrID", string)
            intent.putExtra("Type", int)
            context.startActivity(intent)
        }
    }

    private var daoSession: DaoSession? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_java_test)
        //title = intent!!.getStringExtra("StrID")
        // Type = intent!!.getIntExtra("Type", 0)
        initView()
        initData()
        getData()
    }

    private fun initData() {
        val databaseContext = DatabaseContext(this)
        val devOpenHelper = MySQLiteOpenHelper(databaseContext, Const.DB_Multab, null)
        val db = devOpenHelper.getEncryptedWritableDb("123456")
        val daoMaster = DaoMaster(db)
        daoSession = daoMaster.newSession()
    }

    private fun initView() {
        btn_add.setOnClickListener {

            for (index in 0..Random.nextInt(2) + 1) {
                var customerD = CustomerD()
                customerD.id = null
//                customerD.id = index.toLong()
                var lists = mutableListOf<OrdersD>()
                for (t in 0..Random.nextInt(2) + 1) {
                    var orderD = OrdersD()
                    orderD.id = (index * 100 + t).toLong()
                    orderD.customerID = customerD.id
                    orderD.strNote = "$index-$t"
                    orderD.strTime = Date().toString()
                    lists.add(orderD)
                }
//                customerD.__setDaoSession(MyApp().daoSession)
//                customerD.orders.addAll(lists)
                customerD.orders = lists
                try {
                    val insterResult = daoSession?.customerDDao?.insertOrReplace(customerD) as Long
                    if (insterResult > 0) {
                        Log.d("--lfc", "添加成功")
                    } else {
                        Toast.makeText(this, "插入失败 $insterResult", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    Toast.makeText(this, "插入失败  学院编号唯一，新增失败", Toast.LENGTH_SHORT).show()

                }

            }

        }

        btn_search.setOnClickListener {
            val listResult =
                daoSession?.customerDDao?.queryBuilder()?.list()
//            LgU.d("size:${listResult.size} " + listResult.toString())
            tv_log.text = "size:${listResult?.size} " + Gson().toJson(listResult)

        }
        btn_del.setOnClickListener {
            daoSession?.customerDDao?.deleteAll()
        }
    }

    private fun getData() {

    }
}
