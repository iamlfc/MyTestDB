package com.lfc.mysql

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.lfc.mysql.DBUtils.DatabaseContext
import com.lfc.mysql.DBUtils.MySQLiteOpenHelper
import com.lfc.mysql.DataBeans.Student
import com.lfc.mysql.adapter.Adapter_Student
import com.lfc.mysql.callback.OnItemDelCallback
import com.lfc.mysql.callback.OnItemEditCallback
import com.lfc.mysql.greenDao.DaoMaster
import com.lfc.mysql.greenDao.DaoSession
import com.lfc.mysql.greenDao.StudentDao
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random


/*
   *@Author LFC
   *@Date 2020-2-1 13:05:03
   *@Remarks: 基本操作
   */
class MainActivity : AppCompatActivity() {
//  private var StrValue = ""
    //  private var Type = 0

    companion object {
        fun EnterThis(context: Context, string: String = "", int: Int = 0) {
            var intent = Intent(context, MainActivity().javaClass)
            intent.putExtra("StrID", string)
            intent.putExtra("Type", int)
            context.startActivity(intent)
        }
    }

    private var adaStu: Adapter_Student? = null
    private var list_data = mutableListOf<Student>()
    /**
     *  greenDao 数据操作
     *
     */
    private var daoMaster: DaoMaster? = null
    private var daoSession: DaoSession? = null
    private var stuDao: StudentDao? = null
    private var strDBPASS = "123456"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //title = intent!!.getStringExtra("StrID")
        // Type = intent!!.getIntExtra("Type", 0)
        initView()
        initData()
        getData()
    }

    private fun initData() {
//        创建数据
        // 创建数据
//        val devOpenHelper = DaoMaster.DevOpenHelper(this, "hlq.db", null)
        val databaseContext = DatabaseContext(this)
        var devOpenHelper = MySQLiteOpenHelper(databaseContext, getDBPath(), null)
        var db = devOpenHelper.getEncryptedWritableDb(strDBPASS)
        daoMaster = DaoMaster(db)
        daoSession = daoMaster?.newSession()
        stuDao = daoSession?.studentDao

    }

    private fun initView() {
        btn_add.setOnClickListener {
            //            TODO() 新增
            AddData()
            btn_refresh.performClick()
        }
        btn_del.setOnClickListener {
            //            TODO() 清空
            ClearData()
            btn_refresh.performClick()
        }
        btn_updata.setOnClickListener {
            //            TODO() 条件查询
            searchByDao()
        }
        btn_add.setOnLongClickListener {
            //            TODO() 批量增加
            AddMoreData()
            btn_refresh.performClick()
            true
        }
        btn_refresh.setOnClickListener {
            try {
                //            TODO() 刷新
                val listResult = stuDao?.queryBuilder()?.listLazy() as MutableList<Student>
//     查询所有返回数据 但只返回前三条数据 并且跳过第一条数据
//                val stuList = stuDao.queryBuilder().limit(3).offset(1).list()

                list_data.clear()
                list_data.addAll(listResult)
//                adaStu?.RefreshAll(list_data)
                adaStu?.notifyDataSetChanged()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        rlv?.apply {
            var wllm = LinearLayoutManager(baseContext)
            layoutManager = wllm
            itemAnimator = DefaultItemAnimator()
            adaStu = Adapter_Student(baseContext, R.layout.item_student, list_data)
            adaStu?.onDelCallback = object : OnItemDelCallback {
                override fun OnItemDelListener(index: Int, any: Any) {
                    DelData(any as Student)
                }

            }
            adaStu?.onEditCallback = object : OnItemEditCallback {
                override fun OnItemEditListener(index: Int, any: Any) {
                    EditData(any as Student)

                }


            }

            adapter = adaStu

        }

    }

    /**
     * 条件查询数据库
     */
    private fun searchByDao() {
        stuDao?.apply {
            var listResult = this.queryBuilder()
//                .limit(3).offset(1)  // 但只返回前三条数据 并且跳过第一条数据
                ?.where(
                    StudentDao.Properties.StuSex.eq(1.toString()),
//             lt：小于 le：小于等于
//             gt：大于 ge：大于等于
                    StudentDao.Properties.StuScore.ge(60)
                )
//                  orderAsc  升序  orderDesc 降序
                ?.orderDesc(StudentDao.Properties.StuScore)

                ?.list() as MutableList<Student>
            list_data.clear()
            list_data.addAll(listResult)
            adaStu?.notifyDataSetChanged()
        }
    }

    private fun getDBPath(): String? {
        var strDBPath = "hlq.db"
        return strDBPath
    }

    private fun EditData(student: Student) {
        try {
            stuDao?.apply {
                var stuData =
                    this.queryBuilder().limit(1)
                        ?.where(StudentDao.Properties.StuName.eq(student.stuName))// 所以这个where  还是用主键最好 主键： 唯一标示
                        ?.build()?.unique()
                stuData?.apply {
                    val strName = student.stuName
                    this.stuName = "武汉加油" + strName.substring(strName.length - 1, strName.length)
                    this.stuSex = 2.toString()
                    stuDao?.update(this)
                    Toast.makeText(this@MainActivity, "修改成功 ", Toast.LENGTH_SHORT).show()

                }
            }
//        stuDao?.delete(student)
            btn_refresh.performClick()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    private fun ClearData() {
        try {
            stuDao?.deleteAll()

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    /**
     * 删除单个数据
     */
    private fun DelData(student: Student) {
        try {
            stuDao?.deleteByKey(student.strUId)
//        stuDao?.delete(student)
            btn_refresh.performClick()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    private fun AddMoreData() {
        var list_adds = mutableListOf<Student>()
        for (index in 0..Random.nextInt(2) + 1) {
            list_adds.add(
                Student(
                    null,
                    (stuDao?.queryBuilder()?.list()?.maxBy { it.strUId }?.strUId!!.toInt() + index).toString(),
//                    (stuDao?.queryBuilder()?.list()!![stuDao?.count()?.toInt()!! - 1].strUId + index).toString(),
                    "WH加油$index",
                    "1",
                    Random.nextInt(100)
                    , "WH"
                )
            )
        }
        try {
            stuDao?.insertInTx(list_adds)
            Toast.makeText(this, "批量添加成功 ", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun AddData() {
        var stu = Student(null, "001", "中国必胜", "1", 99, "china")
        try {
            val insterResult = stuDao?.insert(stu) as Long
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

    private fun getData() {

    }
}
