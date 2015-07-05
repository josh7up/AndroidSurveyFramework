package com.example.jaf50.survey.parser;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class RadioGroupModel extends ComponentModel {

  private String responseId;
  private List<InputModel> inputs;
}
