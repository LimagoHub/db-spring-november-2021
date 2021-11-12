package de.db.webapp.services.impl;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Service
public class WeitererService {

    @Async
    public Future<Integer> longRunner1() {
        return new AsyncResult<Integer>(42);
    }
    @Async
    public Future<Integer> longRunner2() {
        return new AsyncResult<Integer>(42);
    }
}
