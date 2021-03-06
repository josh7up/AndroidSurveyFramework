package com.askonthego.parser;

import com.askonthego.ui.PickerStyle;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ComponentModelDeserializer implements JsonDeserializer<ComponentModel> {

    @Override
    public ComponentModel deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        JsonElement jsonType = jsonObject.get("type");
        String type = jsonType.getAsString();

        if ("text".equals(type)) {
            TextModel model = new TextModel();
            model.setLabel(jsonObject.get("label").getAsString());
            return model;
        } else if ("slider".equals(type)) {
            SliderModel model = new SliderModel();
            model.setResponseId(jsonObject.get("responseId").getAsString());
            model.setLeftLabel(jsonObject.get("leftLabel").getAsString());
            model.setRightLabel(jsonObject.get("rightLabel").getAsString());
            return model;
        } else if ("datePicker".equals(type)) {
            DatePickerModel model = new DatePickerModel();
            model.setResponseId(jsonObject.get("responseId").getAsString());
            if (jsonObject.get("label") != null) {
                model.setLabel(jsonObject.get("label").getAsString());
            }
            if (jsonObject.get("style") != null) {
                String inputType = jsonObject.get("style").getAsString();
                for (PickerStyle pickerStyle : PickerStyle.values()) {
                    if (pickerStyle.getDescription().equals(inputType)) {
                        model.setPickerStyle(pickerStyle);
                        break;
                    }
                }
            }
            return model;
        } else if ("timePicker".equals(type)) {
            TimePickerModel model = new TimePickerModel();
            model.setResponseId(jsonObject.get("responseId").getAsString());
            if (jsonObject.get("label") != null) {
                model.setLabel(jsonObject.get("label").getAsString());
            }
            if (jsonObject.get("style") != null) {
                String inputType = jsonObject.get("style").getAsString();
                for (PickerStyle pickerStyle : PickerStyle.values()) {
                    if (pickerStyle.getDescription().equals(inputType)) {
                        model.setPickerStyle(pickerStyle);
                        break;
                    }
                }
            }
            return model;
        } else if ("radioGroup".equals(type)) {
            RadioGroupModel model = new RadioGroupModel();
            model.setResponseId(jsonObject.get("responseId").getAsString());
            model.setInputs(getRadioInputs(jsonObject.getAsJsonArray("inputs")));
            return model;
        } else if ("checkboxGroup".equals(type)) {
            CheckboxGroupModel model = new CheckboxGroupModel();
            model.setResponseId(jsonObject.get("responseId").getAsString());
            model.setInputs(getCheckboxInputs(jsonObject.getAsJsonArray("inputs")));
            return model;
        } else if ("spacer".equals(type)) {
            SpacerModel model = new SpacerModel();
            model.setHeight(jsonObject.get("height").getAsInt());
            return model;
        } else if ("open-ended".equals(type)) {
            OpenEndedModel model = new OpenEndedModel();
            model.setResponseId(jsonObject.get("responseId").getAsString());
            return model;
        }

        return null;
    }

    private List<InputModel> getRadioInputs(JsonArray inputsArray) {
        List<InputModel> inputs = new ArrayList<>();
        for (int i = 0; i < inputsArray.size(); i++) {
            JsonObject inputObject = inputsArray.get(i).getAsJsonObject();
            InputModel input = new InputModel();
            input.setLabel(inputObject.get("label").getAsString());
            input.setValue(inputObject.get("value").getAsString());
            inputs.add(input);
        }
        return inputs;
    }

    private List<CheckboxInputModel> getCheckboxInputs(JsonArray inputsArray) {
        List<CheckboxInputModel> inputs = new ArrayList<>();
        for (int i = 0; i < inputsArray.size(); i++) {
            JsonObject inputObject = inputsArray.get(i).getAsJsonObject();
            CheckboxInputModel input = new CheckboxInputModel();
            input.setLabel(inputObject.get("label").getAsString());
            input.setValue(inputObject.get("value").getAsString());
            if (inputObject.get("mutuallyExclusive") != null) {
                input.setMutuallyExclusive(inputObject.get("mutuallyExclusive").getAsBoolean());
            }
            inputs.add(input);
        }
        return inputs;
    }
}
