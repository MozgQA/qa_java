package com.example;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LionTest {

    @Mock
    private Feline feline;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testConstructorInvalidSex() {
        Exception exception = assertThrows(Exception.class, () -> {
            new Lion("Неизвестно", feline);
        });
        assertEquals("используйте допустимые значения пола животного - самец или самка.", exception.getMessage());
    }

    @Test
    public void testGetKittens() throws Exception {
        when(feline.getKittens()).thenReturn(1);
        Lion lion = new Lion("Самка", feline);
        assertEquals(1, lion.getKittens());
        verify(feline, times(1)).getKittens();
    }

    @Test
    public void testGetFood() throws Exception {
        List<String> expectedFood = List.of("Мясо", "Рыба");
        when(feline.getFood("Хищник")).thenReturn(expectedFood);
        Lion lion = new Lion("Самец", feline);

        List<String> actualFood = lion.getFood();

        assertEquals(expectedFood, actualFood);
        verify(feline, times(1)).getFood("Хищник");
    }
}
