/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LeoValdiviaSuasnabar.ExamenFinal.Services;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import LeoValdiviaSuasnabar.ExamenFinal.Model.mascotas;
import LeoValdiviaSuasnabar.ExamenFinal.Repository.mascotasRepository;

@Service
public class mascotasServices {
    
    @Autowired
    private mascotasRepository mascotasRepository;

    public List<mascotas> listarMascotas(){
        return mascotasRepository.findAll();
    }
    public mascotas guardarMascota(mascotas mascota){
        return mascotasRepository.save(mascota);
    }
    public Optional <mascotas> obtenerMascostaPorId (Long id)
    {
        return mascotasRepository.findById(id);
    }

    public void elimarMascota (Long id)
    {
        mascotasRepository.deleteById(id);
    }
}
