package com.krakdev.peliculas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.krakdev.peliculas.entidades.Pelicula;
import com.krakdev.peliculas.services.ServicioPelicula;

@RestController
@RequestMapping("/peliculas")
public class PeliculaController {

    @Autowired
    private ServicioPelicula servicioPelicula;

    @PostMapping
    public ResponseEntity<Pelicula> crear(@RequestBody Pelicula pelicula) {
        Pelicula nuevaPelicula = servicioPelicula.crear(pelicula);
        return new ResponseEntity<>(nuevaPelicula, HttpStatus.CREATED);
    }
    

    @GetMapping
    public ResponseEntity<List<Pelicula>> listar() {
        List<Pelicula> peliculas = servicioPelicula.listar();
        return new ResponseEntity<>(peliculas, HttpStatus.OK);
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<Pelicula> buscarPorId(@PathVariable Long id) {
        Pelicula pelicula = servicioPelicula.buscarPorId(id);
        if (pelicula != null) {
            return new ResponseEntity<>(pelicula, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pelicula> actualizar(@PathVariable Long id, @RequestBody Pelicula pelicula) {
        Pelicula peliculaActualizada = servicioPelicula.actualizar(id, pelicula);
        if (peliculaActualizada != null) {
            return new ResponseEntity<>(peliculaActualizada, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        boolean eliminado = servicioPelicula.eliminar(id);
        if (eliminado) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/genero")
    public ResponseEntity<List<Pelicula>> buscarPorGenero(@RequestParam String genero) {
        List<Pelicula> peliculas = servicioPelicula.buscarPorGenero(genero);
        return new ResponseEntity<>(peliculas, HttpStatus.OK);
    }

    @GetMapping("/disponible")
    public ResponseEntity<List<Pelicula>> buscarPorDisponible(@RequestParam boolean disponible) {
        List<Pelicula> peliculas = servicioPelicula.buscarPorDisponible(disponible);
        return new ResponseEntity<>(peliculas, HttpStatus.OK);
    }
}