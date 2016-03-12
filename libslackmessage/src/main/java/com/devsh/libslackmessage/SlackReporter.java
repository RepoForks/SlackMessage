/*
 * Copyright (C) 2015 Suhan Lee
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.devsh.libslackmessage;

import android.util.Log;

import com.devsh.libslackmessage.network.SlackMessageReportService;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class SlackReporter {
    private final static String TAG = "ReportManager";

    private static SlackMessageReportService sService;
    private static boolean mIsLogEnabled;
    private final String mApiKey;

    public static SlackReporter create(String apiKey) {
        return new SlackReporter(apiKey, false);
    }

    public static SlackReporter create(String apiKey, boolean logEnabled) {
        return new SlackReporter(apiKey, logEnabled);
    }

    private SlackReporter(String apiKey, boolean logEnabled) {
        mIsLogEnabled = logEnabled;
        mApiKey = apiKey;

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl("https://hooks.slack.com")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());

        if (mIsLogEnabled) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
            builder.client(client);
        }

        Retrofit sRetrofit = builder.build();
        sService = sRetrofit.create(SlackMessageReportService.class);
    }

    public void report(SlackMessage slackMessage) {
        Call<String> report = sService.sendReport(mApiKey, slackMessage);
        report.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (mIsLogEnabled) {
                    Log.d(TAG, "onSuccess");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                if (mIsLogEnabled) {
                    Log.d(TAG, "onFailure");
                }
            }
        });
    }

    public void report(SlackMessage slackMessage, Callback<String> response) {
        Call<String> report = sService.sendReport(mApiKey, slackMessage);
        report.enqueue(response);
    }

    public String reportSync(SlackMessage slackMessage) throws IOException {
        Call<String> report = sService.sendReport(mApiKey, slackMessage);
        return report.execute().body();
    }
}