package org.lessons.java.best_of_the_year.controller;
import java.util.ArrayList;
import java.util.List;

import org.lessons.java.best_of_the_year.model.Movie;
import org.lessons.java.best_of_the_year.model.Song;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class HomeController {
    
    @GetMapping
    public String home(Model model){
        model.addAttribute("name", "Alessandro");
        return "home";  // ! ritorna il file home.html, passandogli anche il model, che si trova in resources/templates/
    }

    @GetMapping("/movies") // ! nel momento in cui l'utente naviga nella nostra applicazione all'indirizzo /movies allora invoca questo metodo
    public String moviesList(Model model){
        List<Movie> movies = getBestMovies();
        model.addAttribute("moviesList", movies.toString());
        return "movies/index";
    }

    @GetMapping("/songs")
    public String songsList(Model model){
        List<Song> songs = getBestSongs();
        model.addAttribute("songsList", songs.toString());
        return "songs/index";
    }

    @GetMapping("/movies/{id}") // ! localhost:8080/movies/2
    public String singleMovie(Model model, @PathVariable("id") Integer movieId){
        Boolean isMovieFound = false;
        Movie movie = null;

        for (Movie currentMovie : getBestMovies()) {
            if (currentMovie.getId() == movieId){
                isMovieFound = true;
                movie = currentMovie;
                break;
            }
        }

        model.addAttribute("isFound", isMovieFound);
        model.addAttribute("movie", movie);

        return "movies/show";
    }

    @GetMapping("/songs/{id}")
    public String singleSong(Model model, @PathVariable("id") Integer songId){
        Boolean isSongFound = false;
        Song song = null;

        for (Song currentSong : getBestSongs()) {
            if (currentSong.getId() == songId){
                isSongFound = true;
                song = currentSong;
                break;
            }
        }

        model.addAttribute("isFound", isSongFound);
        model.addAttribute("song", song);

        return "songs/show";
    }

    private List<Movie> getBestMovies(){
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie(1, "Interstellar"));
        movies.add(new Movie(2, "Basketball Diaries"));
        movies.add(new Movie(3, "Ex Machina"));
        movies.add(new Movie(4, "25a ora"));
        return movies;
    }

    private List<Song> getBestSongs(){
        ArrayList<Song> songs = new ArrayList<>();
        songs.add(new Song(1, "Memories"));
        songs.add(new Song(2, "No Love"));
        songs.add(new Song(3, "Lose Yourself"));
        songs.add(new Song(4, "If i had"));
        return songs;
    }
}