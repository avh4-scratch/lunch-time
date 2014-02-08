package com.pivotallabs.lunchtime.test.integration;

import com.pivotallabs.lunchtime.test.support.IntegrationTestBase;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PersonIntegrationTest extends IntegrationTestBase {

    @Test
    public void testAddPerson() throws Exception {
        goTo(getServerUrl() + "/");

        fill("input").with("luke@lightside.com");
        click("button[type=submit]");

        assertThat(find("input").getValue()).isEmpty();
    }
}
