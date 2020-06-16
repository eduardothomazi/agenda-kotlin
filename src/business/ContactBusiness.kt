package business

import entities.ContactEntity
import repository.ContactRepository
import java.lang.Exception;

class ContactBusiness {
    private fun validate(name: String, phone: String) {
        if (name == "") {
            throw Exception("O nome é obrigatório!")
        }
        if (phone == "") {
            throw Exception("O telefone é obrigatório")
        }
    }
    private fun validateDelete(name: String, phone: String) {
        if (name == "" || phone == "") {
            throw Exception("É necessário selecionar um contato")
        }

    }
    fun getContactCountDescription(): String {
        val list = getList().size;
        if (getList().isEmpty()) {
            return "0 Contatos"
        }
        if (list == 1) {
            return "1 Contato"
        } else
            return "$list Contatos"

    }
    fun save(name: String, phone: String) {
        validate(name, phone)
        val contact = ContactEntity(name, phone)
        ContactRepository.save(contact)
    }
    fun delete(name: String, phone: String) {
        validateDelete(name, phone)
        val contact = ContactEntity(name, phone)
        ContactRepository.delete(contact)
    }
    fun getList(): List<ContactEntity> {
        return ContactRepository.getList()
    }
}
