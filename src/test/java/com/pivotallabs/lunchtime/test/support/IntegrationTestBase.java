package com.pivotallabs.lunchtime.test.support;

import com.pivotallabs.lunchtime.Application;
import org.fluentlenium.adapter.FluentTest;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public abstract class IntegrationTestBase extends FluentTest {
    private static ConfigurableApplicationContext context;

    @BeforeClass
    public static void start() throws Exception {
        final Future<ConfigurableApplicationContext> future = Executors
                .newSingleThreadExecutor().submit(
                        new Callable<ConfigurableApplicationContext>() {
                            @Override
                            public ConfigurableApplicationContext call() throws Exception {
                                return SpringApplication.run(Application.class);
                            }
                        });
        context = future.get(60, TimeUnit.SECONDS);
    }

    @AfterClass
    public static void stop() {
        if (context != null) {
            context.close();
        }
    }

    protected ApplicationContext getContext() {
        return context;
    }

    protected String getServerUrl() {
        final Environment environment = getContext().getEnvironment();
//        final Integer port = environment.getProperty("server.port", Integer.class);
        final Integer port = 8080;
        final String contextPath = environment.getProperty("server.contextPath", String.class, "");

        return "http://localhost:" + port + contextPath;
    }
}
