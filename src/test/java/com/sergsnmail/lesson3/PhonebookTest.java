package com.sergsnmail.lesson3;

import org.junit.Test;

public class PhonebookTest {

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalArgumentException() {
        Phonebook phonebook = new Phonebook();
        phonebook.add("","");
    }

}
