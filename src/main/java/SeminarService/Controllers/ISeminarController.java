package SeminarService.Controllers;

import java.util.Map;

public interface ISeminarController {
    String getRandomPerson();

    Map<String, Double> getResult();

    void setNewGrade(String person, Double grade) throws SeminarControllerException;
}
