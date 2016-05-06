package cl.monsoon.s1next.base;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.google.common.base.Preconditions;

import cl.monsoon.s1next.R;

public abstract class BaseActivity extends AppCompatActivity {
    private Toolbar toolbar;

    final void setUpToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        Preconditions.checkState(toolbar != null);

        setSupportActionBar(toolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Nullable
    final Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toolbar != null && item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
