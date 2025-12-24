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
import org.mockito.kotlin.whenever
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.mockito.Mockito.never
import org.mockito.kotlin.any

class ContactUseCasesTest {

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
    @Test
    fun `should throw exception when name is empty`() {
        // Arrange
        val invalidContact = Contact(name = "", phoneNumber = "123456")

        // Act & Assert
        val exception = assertThrows(IllegalArgumentException::class.java) {
            addContact(invalidContact)
        }

        assertEquals("Name cannot be empty", exception.message)

        // Verify that the repository was NEVER touched (database safety)
        verify(repository, never()).add(any())
    }

    @Test
    fun `should return list of contacts from repository`() {
        // Arrange
        val expectedList = listOf(
            Contact(1, "Alice", "111"),
            Contact(2, "Bob", "222")
        )
        // Teach the mock what to return when .getAll() is called
        whenever(repository.getAll()).thenReturn(expectedList)

        // Act
        val result = getContacts()

        // Assert
        assertEquals(2, result.size)
        assertEquals("Alice", result[0].name)
        verify(repository, times(1)).getAll()
    }

}