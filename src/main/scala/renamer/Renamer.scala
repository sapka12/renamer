package renamer

object Rename {

  def main(args: Array[String]): Unit = {
    val folder = args(0)

    getListOfFiles(folder).foreach { file =>

      val newPath = decoratePath(
        file.getAbsolutePath,
        month(file.lastModified),
        date(file.lastModified)
      )

      copy(file, newPath)
    }
  }
}
