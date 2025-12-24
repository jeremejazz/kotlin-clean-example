package me.jereme.presentation

import me.jereme.domain.model.Contact
import me.jereme.domain.usecase.AddContact
import me.jereme.domain.usecase.DeleteContact
import me.jereme.domain.usecase.GetContacts
import me.jereme.domain.usecase.UpdateContact
import java.util.Scanner

class ConsoleUI(
    private val addContact: AddContact,
    private val getContacts: GetContacts,
    private val updateContact: UpdateContact,
    private val deleteContact: DeleteContact
) {

    private val scanner = Scanner(System.`in`)

    fun start(){
        while(true) {
            println("\n--- Kotlin Phonebook (SQLite) ---")
            println("1. List Contacts")
            println("2. Add Contact")
            println("3. Update Contact")
            println("4. Delete Contact")
            println("5. Exit")
            print("Choose an option: ")

            when (scanner.nextLine()) {
                "1" -> listContacts()
                "2" -> createContact()
                "3" -> editContact()
                "4" -> removeContact()
                "5" -> return
                else -> println("Invalid Option")
            }
        }
    }

    private fun listContacts() {
        val contacts = getContacts()
        if (contacts.isEmpty()){
            println("No contacts found.")
        } else {
            println("\nID | Name | Phone | Email")
            println("---|------|-------|------")
            contacts.forEach {
                println("${it.id} | ${it.name} | ${it.phoneNumber} | ${it.email ?: "N/A"}")
            }
        }
    }

    private fun createContact() {
        print("Enter Name: ")
        val name = scanner.nextLine()
        print("Enter Phone: ")
        val phone = scanner.nextLine()
        print("Enter Email (optional): ")
        val email = scanner.nextLine()

        try{
            val contact = Contact(
                name =  name,
                phoneNumber = phone,
                email = email.ifBlank { null }
            )
            addContact(contact)
            println("Contact addedd successfully.")
        }catch (e: Exception){
            println("Error: ${e.message}")
        }

    }

    private fun editContact() {
        listContacts()
        print("Enter ID to update: ")
        val id = scanner.nextLine().toIntOrNull()
        if (id == null){
            println("Invalid ID.")
            return
        }

        print("Enter New Name: ")
        val name = scanner.nextLine()
        print("Enter New Phone: ")
        val phone = scanner.nextLine()
        print("Enter New Email: ")
        val email = scanner.nextLine()

        updateContact(Contact(id = id, name = name, phoneNumber = phone, email = email.ifBlank { null }))
        println("Contact updated.")
    }

    private fun removeContact() {
        listContacts()
        print("Enter ID to delete: ")
        val id = scanner.nextLine().toIntOrNull()
        if (id != null) {
            deleteContact(id)
            println("Contact deleted.")
        } else {
            println("Invalid ID.")
        }
    }


}