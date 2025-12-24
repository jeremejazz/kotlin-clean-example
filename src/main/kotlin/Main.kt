package me.jereme

import me.jereme.data.db.DatabaseFactory
import me.jereme.data.repository.ContactRepositoryImpl
import me.jereme.domain.usecase.AddContact
import me.jereme.domain.usecase.DeleteContact
import me.jereme.domain.usecase.GetContacts
import me.jereme.domain.usecase.UpdateContact
import me.jereme.presentation.ConsoleUI

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    DatabaseFactory.init()

    val contactRepository = ContactRepositoryImpl()

    val addContact = AddContact(contactRepository)
    val getContacts = GetContacts(contactRepository)
    val updateContact = UpdateContact(contactRepository)
    val deleteContact = DeleteContact(contactRepository)

    val consoleUI = ConsoleUI(
        addContact,
        getContacts,
        updateContact,
        deleteContact
    )

    consoleUI.start()

}