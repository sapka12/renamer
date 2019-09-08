package renamer

import java.io.File
import cats.data.Kleisli

object Rename {

  def main(args: Array[String]): Unit = args.headOption.foreach{folder =>
    val listFilesInFolder = Kleisli(getListOfFiles(_: String))
    val createRenamedCopy = Kleisli(copyFiles(_: List[File]))
    listFilesInFolder andThen createRenamedCopy apply folder unsafeRunSync()
  }
}
