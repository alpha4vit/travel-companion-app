package by.gurinovich.travelcompanionsearch.service;

import by.gurinovich.travelcompanionsearch.props.GeocoderProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class GeocoderService {
    private final RestTemplate restTemplate;
    private final GeocoderProperties geocoderProperties;

    public String getCoordinatesByAddress(String address) {
        String urlString = geocoderProperties.getUrl() + address +
                "&" +
                geocoderProperties.getFormat();
        ResponseEntity<String> response = restTemplate.getForEntity(urlString, String.class);
        return parseResponseToCoordinates(response);
    }

    public String getAddressByCoordinates(String longitude, String latitude){
        String urlString = geocoderProperties.getUrl() + String.format("%s,%s", latitude, longitude) +
                "&lang=ru_RU&" +
                geocoderProperties.getFormat();
        ResponseEntity<String> response = restTemplate.getForEntity(urlString, String.class);
        System.out.println(response.getBody());
        return parseResponseToAddress(response);
    }

    private String parseResponseToCoordinates(ResponseEntity<String> response){
        String coordinates = "";
        try {
            JSONObject json  = new JSONObject(response.getBody());
            JSONObject featureMember = new JSONObject(json
                    .getJSONObject("response")
                    .getJSONObject("GeoObjectCollection")
                    .getJSONArray("featureMember")
                    .get(0).toString());
            coordinates = featureMember
                    .getJSONObject("GeoObject")
                    .getJSONObject("Point").getString("pos");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return coordinates;
    }

    private String parseResponseToAddress(ResponseEntity<String> response){
        String address = "";
        try {
            JSONObject json  = new JSONObject(response.getBody());
            JSONObject featureMember = new JSONObject(json
                    .getJSONObject("response")
                    .getJSONObject("GeoObjectCollection")
                    .getJSONArray("featureMember")
                    .get(0).toString());
            address = featureMember
                    .getJSONObject("GeoObject")
                    .getJSONObject("metaDataProperty")
                    .getJSONObject("GeocoderMetaData")
                    .getString("text");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return address;
    }
}
