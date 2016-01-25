package edu.galileo.android.twitterclient.lib;

/**
 * Created by ykro.
 */
public class EventBus {
    de.greenrobot.event.EventBus eventBus;

    public EventBus(){
        eventBus = de.greenrobot.event.EventBus.getDefault();
    }

    public void register(Object subscriber){
        eventBus.register(subscriber);
    }

    public void unregister(Object subscriber){
        eventBus.unregister(subscriber);
    }

    public void post(Object event){
        eventBus.post(event);
    }
}
