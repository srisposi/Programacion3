package com.API_Rest.demo.Business

import com.API_Rest.demo.Model.Persona

interface IPersonaBusiness {
    fun list():List<Persona>
    fun load(idPersona:Long):Persona
    fun save(persona:Persona):Persona
    fun remove(idPersona:Long)
}