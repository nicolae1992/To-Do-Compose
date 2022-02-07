package md.todo.compouse.data

import androidx.room.Database
import androidx.room.RoomDatabase
import md.todo.compouse.data.models.ToDoTask

@Database(
    entities = [ToDoTask::class],
    version = 1,
    exportSchema = false
)
abstract class ToDoDatabase : RoomDatabase() {


    abstract fun toDoDao(): ToDoDao
}