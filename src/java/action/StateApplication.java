package action;

import bean.Auto;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
public class StateApplication {

    private volatile  int maxPakringPlace = 3;

    private final ConcurrentHashMap<String, Auto> collectData = new ConcurrentHashMap<>(maxPakringPlace);

    public boolean parkingNewCar(Auto auto) {
        if (collectData.values().stream().filter(a -> a.getEndTime() == null).count() < maxPakringPlace) {
            collectData.put(auto.getNumber(), auto);
            return true;
        } else {
            return false;
        }
    }

    public void leaveParking(String number, int payment, String end) {
        Auto auto = collectData.get(number);
        auto.setPayment(payment);
        auto.setEndTime(end);
    }

    public boolean isParked(String number) {
        return collectData.containsKey(number);
    }

    public int money(String currentTime) {
        return collectData.values().stream()
                .filter((t) -> {
                    return convertDate(currentTime).compareTo(convertDate(t.getEndTime())) == 0; 
                })
                .collect(Collectors.summingInt(a -> a.getPayment()));
    }

    public int freePlace() {
        return maxPakringPlace - (int) collectData.values().stream().filter(a -> a.getEndTime() == null).count();
    }

    public List<Auto> listParked() {
        return collectData.values()
                .stream()
                .filter(a -> a.getEndTime() == null)
                .collect(Collectors.toList());
    }

    public Date convertDate(String plainDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return format.parse(plainDate);
        } catch (ParseException ex) {
            Logger.getLogger(StateApplication.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

}
