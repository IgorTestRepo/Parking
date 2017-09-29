package start;

import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jesus-my-lord
 */
@ApplicationPath("parking")
public class InitApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return super.getClasses();
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(action.Park.class);
        resources.add(action.Result.class);
    }

}
