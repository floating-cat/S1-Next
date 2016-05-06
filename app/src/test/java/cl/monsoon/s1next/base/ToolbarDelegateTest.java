package cl.monsoon.s1next.base;

import android.support.v7.app.AppCompatActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ToolbarDelegateTest {

    private ToolbarDelegate toolbarDelegate;

    @Mock
    private AppCompatActivity

    @Before
    public void setUp() {
        toolbarDelegate = new ToolbarDelegate();
    }

    @Before


    @Test
    public void canSetUpToolbar() {
        toolbarDelegate.setUpToolbar();
    }
}
