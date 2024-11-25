package com.example.Soap1.service;

import com.example.Soap1.entity.Movie;
import com.example.Soap1.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor // Lombok generará un constructor para los campos finales
public class MovieService {

    private final MovieRepository movieRepository; // Inyectamos el repositorio de Movie

    // Guardar una nueva película
    public Movie guardarPelicula(Movie movie) {
        return movieRepository.save(movie);
    }

    // Obtener una película por su ID
    public Movie obtenerPeliculaPorId(int id) {
        return movieRepository.findById(id).orElse(null); // Si no existe, retorna null
    }

    // Obtener todas las películas
    public List<Movie> obtenerTodasLasPeliculas() {
        return movieRepository.findAll(); // Retorna todas las películas
    }

    // Eliminar una película por su ID
    public void eliminarPelicula(int id) {
        movieRepository.deleteById(id); // Elimina la película por ID
    }
}