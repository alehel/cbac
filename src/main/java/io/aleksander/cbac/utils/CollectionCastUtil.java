package io.aleksander.cbac.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Helper class for avoiding unchecked cast warnings and to force early crashes if an element is of
 * the wrong class type. Code sourced from
 * <a href="https://www.baeldung.com/java-unchecked-conversion#2-checking-type-conversion-before-using-the-raw-type-collection">Baeldung</a>
 */
public class CollectionCastUtil {
  private static final Logger logger = LoggerFactory.getLogger(CollectionCastUtil.class);

  public static <T> List<T> castList(Class<? extends T> clazz, Collection<?> rawCollection) {
    List<T> result = new ArrayList<>(rawCollection.size());
    for (Object object : rawCollection) {
      try {
        result.add(clazz.cast(object));
      } catch (ClassCastException e) {
        logger.error(
            "ClassCastException: Expected element in List to be of type {}, but was of type {}",
            clazz.getCanonicalName(),
            object.getClass().getCanonicalName());
        throw e;
      }
    }
    return result;
  }
}
