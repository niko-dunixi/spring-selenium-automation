package io.paulbaker.ssa.framework;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by paul on 9/24/15.
 */
public class TestCaseScope implements Scope {

  private Map<String, Object> objectMap;
  private Map<String, List<Runnable>> destructorMap;

  public TestCaseScope() {
    objectMap = new ConcurrentHashMap<>();
    destructorMap = new ConcurrentHashMap<>();
  }

  @Override
  public Object get(String beanName, ObjectFactory<?> objectFactory) {
    if (!objectMap.containsKey(beanName)) {
      objectMap.put(beanName, objectFactory.getObject());
    }
    return objectMap.get(beanName);
  }

  @Override
  public Object remove(String beanName) {
    Object remove = objectMap.remove(beanName);
    if (destructorMap.containsKey(beanName))
      destructorMap.remove(beanName).forEach(Runnable::run);
    return remove;
  }

  @Override
  public void registerDestructionCallback(String beanName, Runnable runnable) {
    if (destructorMap.containsKey(beanName)) {
      destructorMap.put(beanName, new ArrayList<>());
    }
    List<Runnable> runnables = destructorMap.get(beanName);
    synchronized (runnables) {
      runnables.add(runnable);
    }
  }

  @Override
  public Object resolveContextualObject(String beanName) {
    return null;
  }

  @Override
  public String getConversationId() {
    return this.getClass().getSimpleName();
  }

  public void clear() {
    objectMap.keySet().forEach(this::remove);
  }
}
