package by.gurinovich.travelcompanionsearch.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;


public record ResponseBody(Object body, Map<String, String> headers){

}