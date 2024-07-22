package io.kestra.plugin.hubspot.tickets;

import com.google.common.base.Strings;
import io.kestra.core.junit.annotations.KestraTest;
import io.kestra.core.runners.RunContext;
import io.kestra.core.runners.RunContextFactory;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIf;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@KestraTest
@DisabledIf(
    value = "isApiKeyNull",
    disabledReason = "For CI/CD as requires secret apiKet or oauthToken"
)
class CreateTest {
    @Inject
    private RunContextFactory runContextFactory;

    @Test
    void run() throws Exception {
        RunContext runContext = runContextFactory.of();

        Create task = Create.builder()
            .apiKey(getApiKey())
            .subject("This is a test")
            .content("This is a test from kestra unit tests")
            .stage(4)
            .build();

        Create.Output runOutput = task.run(runContext);

        assertThat(runOutput.getId(), is(notNullValue()));
    }

    private static boolean isApiKeyNull() {
        return Strings.isNullOrEmpty(getApiKey());
    }

    private static String getApiKey() {
        return "";
    }

}
