package com.centaury.cataloguemovie.utils;

import java.util.concurrent.Executor;

/**
 * Created by Centaury on 11/25/2019.
 */
public class InstantAppExecutors extends AppExecutors {
    private static Executor instant = Runnable::run;

    public InstantAppExecutors() {
        super(instant, instant, instant);
    }
}
