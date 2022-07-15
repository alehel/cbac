package io.aleksander.cbac.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CollectionCastUtilTest {

  @Test
  void castList_listIsTypeString_castToStringWithoutException() {
    String string1 = "String1";
    String string2 = "String2";

    List<Object> uncastList = List.of(string1, string2);
    List<String> castList = CollectionCastUtil.castList(String.class, uncastList);

    // number of elements should be unchanged.
    assertEquals(castList.size(), 2);

    // order should be unchanged.
    assertEquals(castList.get(0), string1);
    assertEquals(castList.get(1), string2);
  }

  @Test
  void castList_listIsTypeString_castToFileTriggersClassCastException() {
    String string1 = "String1";
    String string2 = "String2";

    List<Object> uncastList = List.of(string1, string2);

    Assertions.assertThrows(ClassCastException.class, () -> {
      List<File> castList = CollectionCastUtil.castList(File.class, uncastList);
    });
  }
}