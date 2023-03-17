package com.helmo.simonsays.Classement;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import com.helmo.simonsays.MainActivity;
import com.helmo.simonsays.R;
import com.helmo.simonsays.database.ScoreDatabase;
import com.helmo.simonsays.model.Score;
import com.helmo.simonsays.utils.RecyclerViewItemCountAssertion;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ClassementTest {
    public static final String DIFFICULTY = "test";
    public static final String PSEUDO = "test";
    public static final int POINT = 20;
    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule
            = new ActivityScenarioRule<>(MainActivity.class);

    @BeforeClass
    public static void beforeClass() {
        ApplicationProvider.getApplicationContext().deleteDatabase("score_database");
        ScoreDatabase.initDatabase(ApplicationProvider.getApplicationContext());
        Score score = new Score();
        score.setDifficulty(DIFFICULTY);
        score.setPseudo(PSEUDO);
        score.setPoint(POINT);

        ScoreDatabase.getInstance().scoreDao().insert(score);
    }

    @Test
    public void testElementInRecyclerView() {
        onView(withId(R.id.scoreButton)).perform(click());
        onView(withId(R.id.classement)).check(new RecyclerViewItemCountAssertion(1));
        onView(withId(R.id.difficulty_score_item)).check(matches(withText(DIFFICULTY+" | "+POINT)));
        onView(withId(R.id.pseudo_item)).check(matches(withText(PSEUDO)));

    }
}
