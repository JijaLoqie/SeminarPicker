package SeminarService.Controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SeminarController implements ISeminarController {
    private final ArrayList<String> persons;
    private final Map<String, Double> grades = new HashMap<>();
    private final Map<String, Boolean> visited = new HashMap<>();
    public SeminarController(ArrayList<String> persons) throws SeminarControllerException {
        if (persons == null) {
            throw new SeminarControllerException("List of persons is not initialized");
        }
        if (persons.isEmpty()) {
            throw new SeminarControllerException("List of persons is empty");
        }
        this.persons = persons;
    }

    @Override
    public String getRandomPerson() {
        return persons.get(new Random().nextInt(persons.size()));
    }

    @Override
    public void setNewGrade(String person, Double grade) throws SeminarControllerException {
        if (!persons.contains(person)) {
            throw new SeminarControllerException("There is no such person!");
        }
        if (grades.containsKey(person)) {
            throw new SeminarControllerException("This person already has been graded");
        }
        grades.put(person, grade);
    }

    @Override
    public Map<String, Double> getResult() {
        return grades;
    }

    public boolean wasAsked(String person) {
        return visited.containsKey(person);
    }

    public void setVisited(String person, boolean b) {
        visited.put(person, b);
    }
    public void getVisited(String person) {
        visited.get(person);
    }
    public boolean allChecked() {
        return visited.size() == persons.size();
    }
}
