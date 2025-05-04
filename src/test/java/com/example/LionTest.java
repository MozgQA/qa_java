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
    private static final List<String> EXPECTED_FOOD = List.of("Животные", "Птицы", "Рыба");
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
    public void testGetKittens_returnValue() throws Exception {
        when(feline.getKittens()).thenReturn(1);
        Lion lion = new Lion("Самка", feline);
        assertEquals(1, lion.getKittens());
    }

    @Test
    public void testGetKittens_methodCall() throws Exception {
        when(feline.getKittens()).thenReturn(1);
        Lion lion = new Lion("Самка", feline);
        lion.getKittens();
        verify(feline, times(1)).getKittens();
    }

    @Test
    public void testGetFood_returnValue() throws Exception {
        when(feline.getFood("Хищник")).thenReturn(EXPECTED_FOOD);
        Lion lion = new Lion("Самец", feline);
        List<String> actualFood = lion.getFood();
        assertEquals(EXPECTED_FOOD, actualFood);
    }

    @Test
    public void testGetFood_methodCall() throws Exception {
        when(feline.getFood("Хищник")).thenReturn(EXPECTED_FOOD);
        Lion lion = new Lion("Самец", feline);
        lion.getFood();
        verify(feline, times(1)).getFood("Хищник");
    }
}
