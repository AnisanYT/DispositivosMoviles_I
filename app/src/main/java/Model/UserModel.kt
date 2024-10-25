package Model

import Entities.User
import Interfaces.CrudOperations


class UserModel : CrudOperations<User> {
    private val users: MutableList<User> = mutableListOf()

    override fun add(item: User) {
        users.add(item)
    }

    override fun getById(id: String): User? {
        return users.find { it.id == id }
    }

    override fun getAll(): List<User> {
        return users
    }

    override fun update(item: User) {
        val index = users.indexOfFirst { it.id == item.id }
        if (index != -1) {
            users[index] = item
        }
    }

    override fun remove(id: String) {
        users.removeIf { it.id == id }
    }
}
