package me.jereme.domain.usecase

import me.jereme.domain.model.Contact
import me.jereme.domain.repository.ContactRepository

class AddContact (private val repository: ContactRepository) {
    operator fun invoke(contact: Contact){
        if (contact.name.isBlank()) throw IllegalArgumentException("Name cannot be empty")
        repository.add(contact)
    }
}

class GetContacts(private val repository: ContactRepository){
    operator fun invoke(): List<Contact> = repository.getAll()
}

class GetContact(private val repository: ContactRepository){
    operator fun invoke(id: Int): Contact = repository.getById(id)
}

class UpdateContact(private val repository: ContactRepository){
    operator fun invoke(contact: Contact) = repository.update(contact)
}

class DeleteContact(private val repository: ContactRepository){
    operator fun invoke(id: Int) = repository.delete(id)
}
