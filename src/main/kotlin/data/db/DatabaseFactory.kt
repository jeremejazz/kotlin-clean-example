package me.jereme.data.db

import java.sql.Connection
import java.sql.DriverManager
import java.sql.Statement

object DatabaseFactory {

    private val URL = "jdbc:sqlite:phonebook.db"

    fun connect(): Connection{
        return DriverManager.getConnection(URL)
    }

    fun init(){
        connect().use{
            conn ->
            val statement: Statement = conn.createStatement()
            val sql = """
                CREATE TABLE IF NOT EXISTS contacts(
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL,
                    phone_number TEXT NOT NULL,
                    email TEXT
                );
            """.trimIndent()
            statement.execute(sql)
        }
    }
}

