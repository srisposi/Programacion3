package com.API_Rest.demo.Dao

import com.API_Rest.demo.Model.Persona
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonaRepository:JpaRepository <Persona,Long>{
}