package net.kosen10s.example.datasource

import net.kosen10s.example.entity.Person

class PersonDataSource {

    fun generatePerson(): Person {
        return Person("Yoshihiro", "Wada", "e10dokup")
    }

}