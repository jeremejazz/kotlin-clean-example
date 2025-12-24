package me.jereme.domain.repository

import me.jereme.domain.model.Contact

interface ContactRepository {
    fun add(contact: Contact)
    fun getAll(): List<Contact>
    fun getById(id: Int): Contact?
    fun update(contact: Contact)
    fun delete(id: Int)
}