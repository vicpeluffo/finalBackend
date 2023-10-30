package com.example.catalogservice.queue;

import com.example.catalogservice.models.Serie;
import com.example.catalogservice.service.ISerieService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class SerieListener {

    private final ISerieService iSerieService;

    public SerieListener(ISerieService iSerieService) {
        this.iSerieService = iSerieService;
    }

    @RabbitListener(queues = {"${queue.serie.name}"})
    public void receive(@Payload Serie serie){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        iSerieService.saveSerie(serie);
    }

}
