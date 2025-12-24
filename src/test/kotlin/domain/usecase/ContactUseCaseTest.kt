package domain.usecase

import me.jereme.domain.model.Contact
import me.jereme.domain.repository.ContactRepository
import me.jereme.domain.usecase.AddContact
import me.jereme.domain.usecase.GetContacts
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

class ContactUseCaseTest {

    private lateinit var repository: ContactRepository

    private lateinit var addContact: AddContact
    private lateinit var getContacts: GetContacts

    @BeforeEach
    fun setUp(){

        repository = mock()

        addContact = AddContact(repository)
        getContacts = GetContacts(repository)
    }

    @Test
    fun `should add contact when data is valid`(){
        val newContact = Contact(name = "John Doe", phoneNumber = "123456")

        addContact(newContact)

        verify(repository, times(1)).add(newContact)
    }
}