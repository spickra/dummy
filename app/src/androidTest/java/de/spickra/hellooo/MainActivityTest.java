package de.spickra.hellooo;

import android.os.Bundle;

import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

public class MainActivityTest {

    @Test
    public void testOnCreate() throws Exception {
        // Given
        MainActivity mainActivity = new MainActivity();
        assertNull(mainActivity.mainButton);

        // When
        mainActivity.onCreate(Bundle.EMPTY);

        // Then
        assertNotNull(mainActivity.mainButton);
    }
}