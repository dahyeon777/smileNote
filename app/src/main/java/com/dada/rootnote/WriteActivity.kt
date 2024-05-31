package com.dada.rootnote

import android.annotation.SuppressLint
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.dada.rootnote.databinding.ActivityWriteBinding

class WriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWriteBinding
    lateinit var db : MemoDatabase
    var memoList : List<MemoEntitiy> = listOf<MemoEntitiy>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_write)

        db = MemoDatabase.getInstance(this)!!

        binding.saveBtn.setOnClickListener {
            val titleText =binding.titleText
            val contentText = binding.contentText
            var memo1 = MemoEntitiy(null,titleText.text.toString())
            var memo2 = MemoEntitiy(null,contentText.text.toString())
            insertMemo(memo1)

        }
    }

    fun insertMemo(memo1 : MemoEntitiy){

        val insertTask = @SuppressLint("StaticFieldLeak")
        object : AsyncTask<Unit,Unit,Unit>(){
            override fun doInBackground(vararg params: Unit?) {
                db.memoDAO().insert(memo1)
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                getAllMemos()
            }
        }
        insertTask.execute()

    }

    fun getAllMemos(){
        val getTask : AsyncTask<Unit,Unit,Unit> = (@SuppressLint("StaticFieldLeak")
        object : AsyncTask<Unit,Unit,Unit>() {

            override fun doInBackground(vararg params: Unit?) {
                memoList = db.memoDAO().getAll()

            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
               /* setRecyclerView(memoList)*/

            }
        }).execute()


    }
}