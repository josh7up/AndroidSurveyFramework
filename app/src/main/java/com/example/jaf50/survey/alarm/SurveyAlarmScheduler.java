package com.example.jaf50.survey.alarm;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.buzzbox.mob.android.scheduler.SchedulerManager;
import com.example.jaf50.survey.parser.alarm.AlarmModel;
import com.example.jaf50.survey.parser.alarm.ScheduleModel;
import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;

public class SurveyAlarmScheduler {

  private static Gson gson = new Gson();

  public void scheduleAll(Context context, InputStream alarmInputStream) {
    ScheduleModel scheduleModel = gson.fromJson(new InputStreamReader(alarmInputStream), ScheduleModel.class);
    if (scheduleModel.getAlarms().size() > LaunchSurveyTask.getMaxAlarmCount()) {
      throw new IllegalStateException("Max available alarms exceeded. Up to " + LaunchSurveyTask.getMaxAlarmCount() + " alarms are allowed but " +
                                      scheduleModel.getAlarms().size() + " are specified.");
    }

    SchedulerManager.getInstance().stopAll(context);

    int taskNumber = 1;
    SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
    for (AlarmModel alarmModel : scheduleModel.getAlarms()) {
      Class<? extends LaunchSurveyTask> taskClass = LaunchSurveyTask.getTask(taskNumber++);
      editor.putString(taskClass.getSimpleName(), alarmModel.getSurveyName());
      SchedulerManager.getInstance().saveTask(context, alarmModel.getScheduleExpression(), taskClass);
      SchedulerManager.getInstance().restart(context, taskClass);

      Log.d(getClass().getName(), "Scheduled alarm " + alarmModel.getScheduleExpression() + " for survey " + alarmModel.getSurveyName());
    }
    editor.commit();
  }
}
