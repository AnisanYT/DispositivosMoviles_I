package Interfaces

interface CrudOperations<T> {
    fun add(item: T)
    fun getById(id: String): T?
    fun getAll(): List<T>
    fun update(item: T)
    fun remove(id: String)
}
