package com.API_Rest.demo.Business

import com.API_Rest.demo.Dao.PersonaRepository
import com.API_Rest.demo.Exception.BusinessException
import com.API_Rest.demo.Exception.NotFoundException
import com.API_Rest.demo.Model.Persona
import org.aspectj.weaver.ast.Not
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class PersonaBusiness: IPersonaBusiness {

    @Autowired
    val personaRepository:PersonaRepository?=null

    @Throws(BusinessException::class)
    override fun list(): List<Persona> {
        try{
            return personaRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class,NotFoundException::class)
    override fun load(idPersona: Long): Persona {
        val op: Optional<Persona>
        try{
            op=personaRepository!!.findById(idPersona)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!op.isPresent){
            throw NotFoundException("No se encontró la persona con id $idPersona")
        }
        return op.get()

    }

    @Throws(BusinessException::class)
    override fun save(persona: Persona): Persona {
        try{
            return personaRepository!!.save(persona)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @Throws(BusinessException::class,NotFoundException::class)
    override fun remove(idPersona: Long) {
        val op: Optional<Persona>
        try{
            op=personaRepository!!.findById(idPersona)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if (!op.isPresent){
            throw NotFoundException("No se encontro la persona con el id $idPersona")
        }else{
            try{
                personaRepository!!.deleteById(idPersona)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
    }
}