package com.example.jaf50.survey.response;

import com.example.jaf50.survey.ui.ISurveyComponent;

import java.util.List;

public class ResponseAggregator {

  public String collectResponses(List<ISurveyComponent> surveyComponents) {
    StringBuilder builder = new StringBuilder();
    for (ISurveyComponent surveyComponent : surveyComponents) {
      if (surveyComponent.acceptsResponse()) {
        Response response = surveyComponent.getResponse();
        for (Object value : response.getValues()) {
          builder.append(value).append(",");
        }
      }
    }
    return builder.toString();
  }
}