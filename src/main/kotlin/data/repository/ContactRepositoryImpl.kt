package me.jereme.data.repository

import me.jereme.data.db.DatabaseFactory
import me.jereme.domain.model.Contact
import me.jereme.domain.repository.ContactRepository

class ContactRepositoryImpl : ContactRepository {
    override fun add(contact: Contact) {
        val sql = "INSERT INTO contacts (name, phone_number, email) VALUES (?, ?, ?)"
        DatabaseFactory.connect().use { conn ->
            val pstmt = conn.prepareStatement(sql)
            pstmt.setString(1, contact.name)
            pstmt.setString(2, contact.phoneNumber)
            pstmt.setString(3, contact.email)
            pstmt.executeUpdate()
        }

    }

    override fun getAll(): List<Contact> {
        val sql = "SELECT * FROM contacts"
        val list = mutableListOf<Contact>()

        DatabaseFactory.connect().use { conn ->
            val rs = conn.createStatement().executeQuery(sql)
            while (rs.next()) {
                val contact = Contact(
                    id = rs.getInt("id"),
                    name = rs.getString("name"),
                    phoneNumber = rs.getString("phone_number"),
                    email = rs.getString("email")
                )
                list.add(
                    contact
                )
            }
        }
        return list
    }

    override fun getById(id: Int): Contact? {
        val sql = "SELECT * FROM contacts WHERE id = ?"


        DatabaseFactory.connect().use { conn ->

            val pstmt = conn.prepareStatement(sql)
            pstmt.setInt(1, id)
            val rs = pstmt.executeQuery()
            if (rs.next()) {
                val contact = Contact(
                    id = rs.getInt("id"),
                    name = rs.getString("name"),
                    phoneNumber = rs.getString("phone_number"),
                    email = rs.getString("email")
                )
                return contact
            }
        }
        return null;
    }

    override fun update(contact: Contact) {
        TODO("Not yet implemented")
    }

    override fun delete(id: Int) {
        TODO("Not yet implemented")
    }

}