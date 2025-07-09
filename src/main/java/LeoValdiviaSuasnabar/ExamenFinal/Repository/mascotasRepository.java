/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LeoValdiviaSuasnabar.ExamenFinal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import LeoValdiviaSuasnabar.ExamenFinal.Model.mascotas;

@Repository
public interface mascotasRepository extends JpaRepository <mascotas, Long> {

    
}
    

