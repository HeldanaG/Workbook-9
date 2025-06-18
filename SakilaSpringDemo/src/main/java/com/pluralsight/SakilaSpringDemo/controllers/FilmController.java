package com.pluralsight.SakilaSpringDemo.controllers;

import com.pluralsight.SakilaSpringDemo.Dao.FilmDao;
import com.pluralsight.SakilaSpringDemo.models.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// @RequestMapping("/api/films") can use this to simplify mappings that use same path
public class FilmController {

    @Autowired
    @Qualifier("jdbcFilmDao")
    private FilmDao filmDao;

    @GetMapping("/api/films")
    public List<Film> getAllFilms(){
        return filmDao.getAll();
    }
    @GetMapping("/api/films/{id}")
    public Film getAllFilms(@PathVariable int id) {
        return filmDao.findById(id);
    }


    @PostMapping("/api/films")
    @ResponseStatus (value = HttpStatus.CREATED)
    public Film addFilm(@RequestBody Film film) {
        return filmDao.add(film);
    }

    // would work for update if we have update method in jdbc
//    @PostMapping("/api/films/{id}")
//    @ResponseStatus (value = HttpStatus.CREATED)
//    public void updateFilm(@PathVariable int id, @RequestBody Film film) {
//        filmDao.updateFilm(id, film);
//    }

//    @PostMapping("/api/films/{id}")
//    @ResponseStatus (value = HttpStatus.OK)
//    public void deleteFilm(@PathVariable int id) {
//        filmDao.deleteFilm(id);
//    }
}
