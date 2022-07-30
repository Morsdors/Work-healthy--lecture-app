
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import androidx.room.Dao
import androidx.room.Update

class DBHelper(context: Context):SQLiteOpenHelper(context,dbname,
    factory, version) {

    //lateinit var currentUserId
    companion object {
            internal const val dbname = "userDB"
            internal val factory = null
            internal val version = 2
            //table
            val TABLE_NAME = "users"
            private val COL_ID = "Id"
            private val COL_NAME = "Name"
            private val COL_PASSWORD = "Password"
            val COL_POINTS = "points"
    }

    class Person constructor() {
        var name = ""
        var points = 0
        var id = 0
    }
    //class Person constructor(val name: String, val points: Int, val id: Int) {}


    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_QUERY = ("CREATE TABLE $TABLE_NAME ($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COL_NAME TEXT, $COL_PASSWORD TEXT, $COL_POINTS INTEGER )")
        db!!.execSQL(CREATE_TABLE_QUERY)
        //db?.execSQL("CREATE TABLE user(id integer primary key autoincrement, "+"name varchar(30), password varchar(20)) ")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db!!)
    }

    fun cleanDB(){
        val db = this.writableDatabase
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db!!)
    }

    fun insertUserData(name:String, password:String){
        val db= this.writableDatabase
        val values = ContentValues()
        values.put(COL_NAME, name)
        values.put(COL_PASSWORD, password)
        values.put(COL_POINTS, 0)
        Log.d("save ", values.toString())
        Log.d("saving ", "zapiszę:$values, $name, $password")
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

   /* fun editPointsInDB(points: Int){
        val db= this.writableDatabase
        val selectQuery = "SELECT * FROM ${TABLE_NAME} WHERE ${COL_NAME} = '$name' and ${COL_PASSWORD} = '$password'"
        val values = ContentValues()

        values.put(COL_NAME, name)
        values.put(COL_PASSWORD, password)
        values.put(COL_POINTS, 0)
        Log.d("save ", values.toString())
        Log.d("saving ", "zapiszę:$values, $name, $password")
        db.insert(TABLE_NAME, null, values)
        db.close()
    }*/

    @Dao
    interface UserDao {
        @Update
        fun updateUsers(vararg users: Person)
    }


    fun userPresent(name: String, password: String): Boolean {
        val selectQuery = "SELECT * FROM ${TABLE_NAME} WHERE ${COL_NAME} = '$name' and ${COL_PASSWORD} = '$password'"
        val db = this.writableDatabase
        val cursor = db.rawQuery(selectQuery, null)
        if(cursor.count<=0){
            cursor.close()
            return false
        }
        cursor.close()
        /*//cursor.count
        //val selectQuery2 =  "SELECT ${COL_ID} FROM ${TABLE_NAME} WHERE ${COL_NAME} = '$name' and ${COL_PASSWORD} = '$password'"
        //val ID =  db.rawQuery(selectQuery2, null)
        val id = cursor.getInt(cursor.getColumnIndexOrThrow(COL_ID))
        cursor.close()
        currentUserId = id.toString()*/
        return true
    }

    var currentUserId = "0"

    /*fun setCurrentId(login_name: String, login_password: String){
        val db = this.writableDatabase
        val selectQuery =  "SELECT ${COL_ID} FROM ${TABLE_NAME} WHERE ${COL_NAME} = ${login_name} and ${COL_PASSWORD} = ${login_password}"
        val id =  db.rawQuery(selectQuery, null)
        currentUserId = id.toString()
    }*/

    val allUsers:List<Person>
        get(){
            val listMessage = ArrayList<Person>()
            val selectQuery = "SELECT * FROM ${TABLE_NAME}"
            val db = this.writableDatabase
            val cursor =  db.rawQuery(selectQuery, null)
            if(cursor.moveToFirst()){
                do {
                    val person = Person()
                    person.id = cursor.getInt(cursor.getColumnIndexOrThrow(COL_ID))
                    person.name = cursor.getString(cursor.getColumnIndexOrThrow(COL_NAME))
                    person.points = cursor.getInt(cursor.getColumnIndexOrThrow(COL_POINTS))
                    //person.password = cursor.getString(cursor.getColumnIndex(COL_PASSWORD))

                    listMessage.add(person)
                } while (cursor.moveToNext())
            }
            db.close()
            return listMessage
        }

    /*fun deleteCourse(courseName: String) {

        // on below line we are creating
        // a variable to write our database.
        val db = this.writableDatabase

        // on below line we are calling a method to delete our
        // course and we are comparing it with our course name.
        db.delete(TABLE_NAME, "name=?", arrayOf(courseName))
        db.close()
    }*/
}