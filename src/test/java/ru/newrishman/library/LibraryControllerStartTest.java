package ru.newrishman.library;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.newrishman.library.controller.LibraryController;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LibraryControllerStartTest {

    @Autowired
    private LibraryController controller;

    @Test
    public void libraryControllerSimpleTest() {
        assertThat(controller).isNotNull();
    }
}