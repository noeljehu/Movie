package com.example.Soap1.service;



import com.example.Soap1.entity.Movie;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@WebService
@Component
@RequiredArgsConstructor
public class MovieEndpoint {

    private final MovieService movieService;

    // Crear una nueva película
    @WebMethod
    public String crearPelicula(
            @WebParam(name = "name") String name,
            @WebParam(name = "category") String category,
            @WebParam(name = "year") String year,
            @WebParam(name = "originCountry") String originCountry) {

        // Crear la película
        Movie movie = new Movie();
        movie.setName(name);
        movie.setCategory(category);
        movie.setYear(year);
        movie.setOriginCountry(originCountry);

        // Guardar la película y devolver mensaje
        movieService.guardarPelicula(movie);
        return "Película '" + name + "' creada con éxito.";
    }

    // Obtener película por ID
    @WebMethod
    public String obtenerPeliculaPorId(@WebParam(name = "id") int id) {
        Movie movie = movieService.obtenerPeliculaPorId(id);

        if (movie == null) {
            return "No se encontró la película con ID: " + id;
        }

        return "Película encontrada: " + movie.getName() + ", " + movie.getCategory() + ", " + movie.getYear();
    }

    // Listar todas las películas
    @WebMethod
    public String listarPeliculas() {
        List<Movie> peliculas = movieService.obtenerTodasLasPeliculas();

        if (peliculas.isEmpty()) {
            return "No hay películas disponibles en la base de datos.";
        }

        StringBuilder response = new StringBuilder("Películas disponibles:\n");
        for (Movie movie : peliculas) {
            response.append(movie.getName()).append(" (").append(movie.getYear()).append(")\n");
        }

        return response.toString();
    }

    // Eliminar película por ID
    @WebMethod
    public String eliminarPelicula(@WebParam(name = "id") int id) {
        Movie movie = movieService.obtenerPeliculaPorId(id);

        if (movie == null) {
            return "No se encontró la película con ID: " + id;
        }

        movieService.eliminarPelicula(id);
        return "Película '" + movie.getName() + "' eliminada con éxito.";
    }
}