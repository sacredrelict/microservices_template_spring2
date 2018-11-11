package com.github.sacredrelict.api.common;

import com.github.sacredrelict.api.ApplicationTest;
import com.github.sacredrelict.api.common.component.message.Messages;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for Messages.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationTest.class)
public class MessagesTest {

    @Autowired
    @InjectMocks
    private Messages messages;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getTest_start_noException() throws Exception {
        String message = messages.get("file.error");
        assertThat(message).isEqualTo("Error working with file.");
    }

}
