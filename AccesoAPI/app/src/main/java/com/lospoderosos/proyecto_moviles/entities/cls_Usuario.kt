package com.lospoderosos.proyecto_moviles.entities

class cls_Usuario {
    var token: String = ""
    var nombre: String = ""
    var email: String = ""
    var contra: String = ""
    var grado: String = ""

    constructor() {}

    constructor(id: String, nombre: String, email: String, contra: String, grado: String) {
        this.token = id
        this.nombre = nombre
        this.email = email
        this.contra = contra
        this.grado = grado
    }

}