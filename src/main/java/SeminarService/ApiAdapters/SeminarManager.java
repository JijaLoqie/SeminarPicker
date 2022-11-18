package SeminarService.ApiAdapters;

import SeminarService.Controllers.SeminarControllerException;

import java.util.ArrayList;

public abstract class SeminarManager {
    public abstract void startListening(ArrayList<String> persons) throws SeminarControllerException;
    protected abstract void printHelp();

}
