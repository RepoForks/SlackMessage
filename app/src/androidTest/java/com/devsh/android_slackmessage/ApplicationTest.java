package com.devsh.android_slackmessage;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.test.suitebuilder.annotation.MediumTest;

import com.devsh.libslackmessage.SlackAttachment;
import com.devsh.libslackmessage.SlackMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }
}