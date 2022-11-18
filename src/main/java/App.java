import SeminarService.ApiAdapters.ConsoleSeminarManager;
import SeminarService.Controllers.SeminarControllerException;

import java.util.ArrayList;
import java.util.Arrays;

public class App {
    public static void main(String[] args) throws SeminarControllerException {
        new ConsoleSeminarManager().startListening(new ArrayList<>(Arrays.asList(args)));
    }
}
