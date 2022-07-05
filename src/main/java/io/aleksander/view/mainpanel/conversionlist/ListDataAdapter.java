package io.aleksander.view.mainpanel.conversionlist;

import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.util.function.Consumer;

/**
 * A {@link ListDataListener} where all methods trigger the same function, which is passed to
 * the constructor in the form of a {@link Consumer}. Useful for scenarios where you want to perform a
 * specific action when the data model changes, regardless of what that change is.
 */
public class ListDataAdapter implements ListDataListener {
  private final Consumer<ListDataEvent> eventHandler;

  /**
   * @param eventHandler the function you wish to perform when a List data event occurs, passed in
   *     the form of a {@link Consumer}.
   */
  public ListDataAdapter(Consumer<ListDataEvent> eventHandler) {
    this.eventHandler = eventHandler;
  }

  @Override
  public void intervalAdded(ListDataEvent e) {
    eventHandler.accept(e);
  }

  @Override
  public void intervalRemoved(ListDataEvent e) {
    eventHandler.accept(e);
  }

  @Override
  public void contentsChanged(ListDataEvent e) {
    eventHandler.accept(e);
  }
}
