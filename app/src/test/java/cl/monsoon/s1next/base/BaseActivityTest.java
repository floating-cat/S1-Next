package cl.monsoon.s1next.base;

import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import cl.monsoon.s1next.BuildConfig;
import cl.monsoon.s1next.R;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class BaseActivityTest {
    private static final int TOOLBAR_ID = R.id.toolbar;

    private static final int HOME_MENU_ITEM_ID = android.R.id.home;

    private BaseActivity activityWithToolbar = Robolectric.setupActivity(BaseActivityImpl.class);

    private BaseActivity activityWithoutToolbar = Robolectric.setupActivity(BaseActivityImpl.class);

    @Before
    public void setUpContentView() {
        Toolbar toolbar = new Toolbar(activityWithoutToolbar);
        toolbar.setId(TOOLBAR_ID);
        activityWithToolbar.setContentView(toolbar);

        activityWithoutToolbar.setContentView(new View(activityWithoutToolbar));
    }

    @Test
    public void canSetToolbarAsActivityActionbar() {
        assertThat(activityWithToolbar.getSupportActionBar()).isNull();

        activityWithToolbar.setUpToolbar();

        assertThat(activityWithToolbar.getSupportActionBar()).isNotNull();
    }

    @Test(expected = IllegalStateException.class)
    public void failToSetUpToolbarIfNoToolbarInLayout() {
        activityWithoutToolbar.setUpToolbar();
    }

    @Test
    public void canGetToolbarIfToolbarSetUp() {
        activityWithToolbar.setUpToolbar();

        assertThat(activityWithToolbar.getToolbar()).isNotNull();
        assertThat(activityWithToolbar.getToolbar().getId()).isEqualTo(TOOLBAR_ID);
    }

    @Test
    public void canNotGetToolbarIfToolbarNotSetUp() {
        assertThat(activityWithToolbar.getToolbar()).isNull();
        assertThat(activityWithoutToolbar.getToolbar()).isNull();
    }

    @Test
    public void finishActivityIfToolbarSetUpAndHomeMenuItemSelected() {
        MenuItem homeMenuItem = mock(MenuItem.class);
        when(homeMenuItem.getItemId()).thenReturn(HOME_MENU_ITEM_ID);

        activityWithToolbar.setUpToolbar();
        activityWithToolbar.onOptionsItemSelected(homeMenuItem);

        assertThat(activityWithToolbar.isFinishing()).isTrue();
    }

    @Test
    public void doNotFinishActivityIfToolbarNotSetUpAndHomeMenuItemSelected() {
        MenuItem homeMenuItem = mock(MenuItem.class);
        when(homeMenuItem.getItemId()).thenReturn(HOME_MENU_ITEM_ID);

        activityWithoutToolbar.onOptionsItemSelected(homeMenuItem);

        assertThat(activityWithoutToolbar.isFinishing()).isFalse();
    }

    private static class BaseActivityImpl extends BaseActivity {

    }
}
