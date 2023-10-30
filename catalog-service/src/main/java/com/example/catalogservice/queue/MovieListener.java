package com.example.catalogservice.queue;

import com.example.catalogservice.models.Movie;
import com.example.catalogservice.service.IMovieService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class MovieListener {

    private final IMovieService iMovieService;

    public MovieListener(IMovieService iMovieService) {
        this.iMovieService = iMovieService;
    }

    @RabbitListener(queues = {"${queue.movie.name}"})
    public void receive(@Payload Movie movie){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        iMovieService.saveMovie(movie);
    }
}
