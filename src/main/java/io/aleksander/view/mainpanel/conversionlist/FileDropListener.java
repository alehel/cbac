package io.aleksander.view.mainpanel.conversionlist;

import io.aleksander.model.Conversion;
import io.aleksander.model.queue.ConversionQueue;
import io.aleksander.utils.CollectionCastUtil;
import io.aleksander.utils.PdfVerifier;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileDropListener extends DropTargetAdapter {
  private static final Logger logger = LoggerFactory.getLogger(FileDropListener.class);
  private final PdfVerifier pdfVerifier = new PdfVerifier();
  private final ConversionQueue conversionQueue;

  public FileDropListener(ConversionQueue conversionQueue) {
    this.conversionQueue = conversionQueue;
  }

  @Override
  public void dragOver(DropTargetDragEvent event) {
    event.acceptDrag(DnDConstants.ACTION_COPY);
  }

  @Override
  public void drop(DropTargetDropEvent event) {
    if (!event.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
      return;
    }

    event.acceptDrop(DnDConstants.ACTION_COPY);
    Transferable transferable = event.getTransferable();

    try {
      Object files = transferable.getTransferData(DataFlavor.javaFileListFlavor);
      if (files instanceof List list) {
        List<File> fileList = CollectionCastUtil.castList(File.class, list);

        fileList.stream()
            .filter(pdfVerifier::fileIsPdf)
            .forEach(this::addFileToConversionQueue);
      }
    } catch (Exception e) {
      logger.error("Unable to handle dropped TransferData. Message was: {}", e.getMessage());
    }
  }

  private void addFileToConversionQueue(File file) {
    conversionQueue.addConversion(new Conversion(file));
  }
}
