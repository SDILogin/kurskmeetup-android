package mobi.mpk.kurskmeetup.ioc;

import java.lang.reflect.Constructor;
import java.util.ServiceLoader;

import mobi.mpk.kurskmeetup.application.ApiMeetupsService;
import mobi.mpk.kurskmeetup.data.AsyncRepository;
import mobi.mpk.kurskmeetup.data.Urls;
import mobi.mpk.kurskmeetup.data.retrofit.UrlRepository;

public class ComponentFactory {
    private static ComponentFactory instance;

    private ComponentFactory() {}

    public static ComponentFactory getInstance() {
        if (instance == null) {
            instance = new ComponentFactory();
        }
        return instance;
    }

    public Object createComponent(Class componentClass) {
        try {
            if (componentClass.isInterface()) {
                // Maybe use ServiceLoader loader = ServiceLoader.load(componentClass);
                return null;
            }
            if (componentClass.equals(UrlRepository.class)) {
                Constructor c = componentClass.getConstructor(String.class);
                c.setAccessible(true);
                return c.newInstance(Urls.APIARY);
            }
            if (componentClass.equals(ApiMeetupsService.class)) {
                AsyncRepository repository = (AsyncRepository) createComponent(UrlRepository.class);
                Constructor c = componentClass.getConstructor(AsyncRepository.class);
                return c.newInstance(repository);
            }
            return componentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }
}
