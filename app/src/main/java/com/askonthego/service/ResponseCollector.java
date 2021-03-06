package com.askonthego.service;

import com.askonthego.domain.AssessmentResponse;
import com.askonthego.response.Response;
import com.askonthego.ui.ISurveyComponent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ResponseCollector {

    public List<AssessmentResponse> collectResponses(List<ISurveyComponent> surveyComponents) {
        List<AssessmentResponse> responses = new ArrayList<>();
        Date responseDate = new Date();
        for (ISurveyComponent surveyComponent : surveyComponents) {
            if (surveyComponent.acceptsResponse()) {
                AssessmentResponse assessmentResponse = new AssessmentResponse();
                Response response = surveyComponent.getResponse();
                assessmentResponse.setResponseDate(responseDate);
                assessmentResponse.setResponseId(response.getId());
                assessmentResponse.setValues(response.getValues());

                responses.add(assessmentResponse);
            }
        }

        return responses;
    }
}
